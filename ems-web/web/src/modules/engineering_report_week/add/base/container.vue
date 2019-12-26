<template>
  <UI
    ref="ui"
    :form="form"
    @submit="handleSubmit"
    :config="{
      weekOption,
      weekMap,
      baseInfo,
    }"
    :help="help"
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
    engineeringId: {
      type: String,
    },
  },
  data() {
    return {
      form: {},
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'week',
      'baseInfo',
      'help',
    ]),
    weekOption() {
      return this.week.map(item => ({ ...item, id: item.weekly })).sort((a, b) => {
        return a.id > b.id ? -1 : 1;
      });
    },
    weekMap() {
      const result = {};
      this.week.forEach(item => {
        result[item.weekly] = item;
      });
      return result;
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'getWeek',
      'addBaseInfo',
      'getBaseInfo',
      'getBaseInfoHelp',
    ]),
    init() {
      this.getBaseInfo(this.reportId).then(data => {
        this.form = Object.assign({}, this.form, data);
      });
      this.getBaseInfoHelp(this.engineeringId);
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    handleSubmit({ type, data }) {
      console.log('submit', type, data);
      data.id = this.reportId;
      data.organizationId = this.engineeringId;
      this.addBaseInfo(data);
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
