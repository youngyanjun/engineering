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
        ...this.weekConfig.affectFactors,
        baseInfo: this.baseInfo,
      };
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'getAffectFactors',
      'addAffectFactors',
    ]),
    init() {
      this.getAffectFactors(this.reportId).then(data => {
        data = data || {};
        this.form = Object.assign({}, this.form, {
          effect: JSON.parse(data.effect || '[]'),
        });
      });
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    handleSubmit({ type, data }) {
      console.log('submit', data);
      data.id = this.reportId;
      this.addAffectFactors(data);
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
