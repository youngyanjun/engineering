<template>
  <UI
    ref="ui"
    :thumbList="thumbList"
    :config="config"
    @batchedDelete="handleBatchedDelete"
    @upload="handleUpload"
    @submit="handleSubmit"
  >
    <slot />
  </UI>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  props: {
    reportId: String,
  },
  data() {
    return {
      thumbList: [],
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'baseInfo',
    ]),
    config() {
      return {
        baseInfo: this.baseInfo,
      };
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'uploadImage',
      'addSchedule',
      'getSchedule',
    ]),
    init() {
      this.getSchedule(this.reportId).then(data => {
        this.thumbList = (data || []).map(item => ({
          id: item.id,
          url: item.fileName,
          description: item.remark,
        }));
      });
    },
    handleBatchedDelete(data) {
      console.log('batchedDelete', data);
      data.forEach(id => {
        this.thumbList.splice(this.thumbList.findIndex(item => item.id === id), 1);
      });
    },
    handleUpload(file) {
      console.log('upload', file);
      this.uploadImage(file).then(data => {
        console.log(data);
        this.thumbList.push({
          id: String(Math.random()).slice(2, 10),
          url: data[0].fileName,
          description: '',
        });
      });
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    handleSubmit({ type, data }) {
      console.log('submit', data);
      this.addSchedule({
        id: this.reportId,
        list: this.thumbList.map(item => ({
          fileName: item.url,
          remark: item.description,
        })),
      });
      if (type === 'next') {
        this.$emit('next');
      } else if (type === 'preview') {
        this.$emit('preview');
      }
    },
    handleBack() {
      this.$emit('prev');
    },
  },
};
</script>
