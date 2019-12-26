<template>
  <Layout
    :menuList="menuList"
    @preview="handlePreview"
  >
    <router-view
      ref="part"
      :reportId="reportId"
      :engineeringId="engineeringId"
      @next="handleNext"
      @prev="handlePrev"
      @preview="handlePreviewCallback"
    />
  </Layout>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import Layout from './layout';
import UI from './component/ui';

export default {
  name: '',
  components: { UI, Layout },
  props: {
    engineeringId: String,
    reportId: String,
  },
  data() {
    return {
      menuList: [
        {
          label: 'EngineeringReportWeekBase',
          value: '',
          title: '基本信息',
          incorrect: false,
        }, {
          label: 'EngineeringReportWeekProgress',
          value: '',
          title: '项目进展情况',
        }, {
          label: 'EngineeringReportWeekSchedule',
          value: '',
          title: '形象进度',
        }, {
          label: 'EngineeringReportWeekNextplan',
          value: '',
          title: '下周计划及安排',
        }, {
          label: 'EngineeringReportWeekOtherSiteRectification',
          value: '',
          title: '现场整改纪要',
        }, {
          label: 'EngineeringReportWeekOtherWorkSafety',
          value: '',
          title: '安全生产情况',
        }, {
          label: 'EngineeringReportWeekOtherDataSummary',
          value: '',
          title: '本周收发资料汇总',
        }, {
          label: 'EngineeringReportWeekOtherAffectFactors',
          value: '',
          title: '可能影响工程的因素',
        }, {
          label: 'EngineeringReportWeekOtherRequirements',
          value: '',
          title: '现场其他需要说明',
        }, {
          label: 'EngineeringReportWeekWeather',
          value: '',
          title: '天气情况',
        }, {
          label: 'EngineeringReportWeekRejection',
          value: '',
          title: '驳回意见',
        },
      ],
      intervalId: null,
    };
  },
  created() {
    this.intervalId = setInterval(() => {
      console.log('autosave');
      this.$refs.part.submit('submit');
    }, 60 * 1000);
    this.getRequire(this.reportId).then(data => {
      this.menuList[0].incorrect = data.baseInfo;
    });
    this.getBaseInfo(this.reportId);
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'getRequire',
      'getBaseInfo',
    ]),
    handleNext() {
      console.log('next');
      const { menuList, $route: { name: routeName } } = this;
      let currentMenuIndex = 0;
      currentMenuIndex = menuList.findIndex(item => item.label === routeName);
      if (menuList[currentMenuIndex + 1]) {
        this.$router.replace({ name: menuList[currentMenuIndex + 1].label });
      }
      this.getRequire(this.reportId);
    },
    handlePrev() {
      console.log('prev');
      const { menuList, $route: { name: routeName } } = this;
      let currentMenuIndex = 0;
      currentMenuIndex = menuList.findIndex(item => item.label === routeName);
      if (menuList[currentMenuIndex - 1]) {
        this.$router.replace({ name: menuList[currentMenuIndex - 1].label });
      }
    },
    handlePreview() {
      this.$refs.part.submit('preview');
    },
    handlePreviewCallback() {
      this.$router.push({ name: 'EngineeringReportWeekPreview', params: { viewType: 'self' } });
    },
  },
  destroyed() {
    window.clearInterval(this.intervalId);
  },
};
</script>
