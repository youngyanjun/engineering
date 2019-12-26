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
      form: {},
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'baseInfo',
      'weekConfig',
    ]),
    config() {
      return {
        ...this.weekConfig.workSafety,
        baseInfo: this.baseInfo,
      };
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'addWorkSafety',
      'getWorkSafety',
    ]),
    init() {
      this.form.siteCheck = [...this.config.siteCheck];
      this.getWorkSafety(this.reportId).then(data => {
        data = data || {};
        this.form = Object.assign({}, this.form, {
          ...data,
          dangerSource: JSON.parse(data.dangerSource || '[]'),
          nextDangerSource: JSON.parse(data.nextDangerSource || '[]'),
          siteCheck: JSON.parse(data.siteCheck || '[]'),
        });
      });
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    handleSubmit({ type, data }) {
      console.log('submit', data);
      data.id = this.reportId;
      this.addWorkSafety(data);
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
