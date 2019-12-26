<template>
  <UI
    ref="ui"
    :form="form"
    :config="config"
    @submit="handleSubmit"
    @back="handleBack"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  props: {
    reportId: {
      type: String,
    },
  },
  data() {
    return {
      forms: {},
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'baseInfo',
      'field',
      'weekConfig',
    ]),
    form() {
      return this.forms;
    },
    config() {
      return {
        ...this.weekConfig.siteRectification,
        problemTypes: this.field.problemTypes,
        baseInfo: this.baseInfo,
      };
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'addSiteRectification',
      'getSiteRectification',
      'getField',
    ]),
    init() {
      this.getSiteRectification(this.reportId).then(data => {
        data = data || {};
        this.forms = Object.assign({}, {
          ...data,
          rectification: JSON.parse(data.rectification || '[]'),
        });
      });
      this.getField('problemTypes');
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    handleSubmit({ type, data }) {
      console.log('submit', data);
      data.id = this.reportId;
      this.addSiteRectification(data);
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
