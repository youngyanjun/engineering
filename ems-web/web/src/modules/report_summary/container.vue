<template>
  <UI
    :options="options"
    :routeName="$route.name"
    @change="handleChange"
  >
    <router-view></router-view>
  </UI>
</template>

<script>
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  data() {
    return {
      summary: {
        options: [
          {
            label: '原始数据',
            value: 'ReportSummaryBase',
          },
          {
            label: '周月报',
            value: 'ReportSummaryList',
          },
          {
            label: '月度施工计划',
            value: 'ReportSummaryConstructionPlan',
          },
          {
            label: '月度施工计划目标执行率',
            value: 'ReportSummaryConstructionPlanRate',
          },
          {
            label: '报表设置',
            value: 'ReportSummarySet',
          },
        ],
      },
    };
  },
  computed: {
    options() { // TODO 权限
      return this.summary.options;
    },
  },
  methods: {
    handleChange(name) {
      console.log('change', name);
      this.$router.push({ name });
    },
  },
};
</script>
