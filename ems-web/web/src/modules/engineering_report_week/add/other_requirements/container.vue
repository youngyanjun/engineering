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
    reportId: String,
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
      'weekConfig',
    ]),
    config() {
      return {
        ...this.weekConfig.otherRequirements,
        baseInfo: this.baseInfo,
      };
    },
    form() {
      return this.forms;
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'getOtherRequirements',
      'addOtherRequirements',
    ]),
    init() {
      this.getOtherRequirements(this.reportId).then(data => {
        data = data || {};
        this.forms = Object.assign({}, this.forms, {
          otherSiteInfo: JSON.parse(data.otherSiteInfo || '[]'),
        });
      });
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    handleSubmit({ type, data }) {
      console.log('submit', data);
      data.id = this.reportId;
      this.addOtherRequirements(data);
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
