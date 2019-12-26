<template>
  <UI
    ref="ui"
    :form="form"
    :config="config"
    @submit="handleSubmit"
    @back="handleBack"
    @backList="handleBackList"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
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
    ]),
    form() {
      return this.forms;
    },
    config() {
      return {
        baseInfo: this.baseInfo,
      };
    },
  },
  methods: {
    init() {
      setTimeout(() => {
        this.forms = {
          a: [{
            title: 'a',
            content: '1',
            date: '2',
          }, {
            title: 'b',
            content: '1',
            date: '2',
          }],
        };
      });
    },
    submit() {
      this.$refs.ui.submit();
    },
    handleSubmit(data) {
      console.log('submit', data);
      this.$emit('next');
    },
    handleBack() {
      this.$emit('prev');
    },
    handleBackList() {
      this.$router.push({ name: 'EngineeringReportList' });
    },
  },
};
</script>
