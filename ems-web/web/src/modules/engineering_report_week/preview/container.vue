<template>
  <Layout
    :selfView="viewType === 'self'"
    :menuList="menuList"
    @menuItemClick="handleMenuItemClick"
  >
    <UI
      ref="main"
      :config="config"
      :baseInfo="baseInfo"
      :progress="progress"
      :schedule="schedule"
      :nextplan="nextplan"
      :siteRectification="siteRectification"
      :otherRequirements="otherRequirements"
      :affectFactors="affectFactors"
      :dataSummary="dataSummary"
      :workSafety="workSafety"
      :weather="weather"
    />
  </Layout>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import Layout from './layout';
import UI from './component/ui';
import { format as DateFormat } from '@/utils/date';

export default {
  name: '',
  components: {
    UI,
    Layout,
  },
  props: {
    reportId: String,
    engineeringId: String,
    viewType: String,
  },
  data() {
    return {
      menuList: [
        {
          label: '基本信息',
          value: 'EngineeringReportWeekBase',
          anchor: 'base',
        }, {
          label: '项目进展情况',
          value: 'EngineeringReportWeekProgress',
          anchor: 'progress',
        }, {
          label: '形象进度',
          value: 'EngineeringReportWeekSchedule',
          anchor: 'schedule',
        }, {
          label: '下周计划及安排',
          value: 'EngineeringReportWeekNextplan',
          anchor: 'nextplan',
        }, {
          label: '现场整改纪要',
          value: 'EngineeringReportWeekOtherSiteRectification',
          anchor: 'siteRectification',
        }, {
          label: '安全生产情况',
          value: 'EngineeringReportWeekOtherWorkSafety',
          anchor: 'workSafety',
        }, {
          label: '本周收发资料汇总',
          value: 'EngineeringReportWeekOtherDataSummary',
          anchor: 'dataSummary',
        }, {
          label: '可能影响工程的因素',
          value: 'EngineeringReportWeekOtherAffectFactors',
          anchor: 'affectFactors',
        }, {
          label: '现场其他需要说明',
          value: 'EngineeringReportWeekOtherRequirements',
          anchor: 'otherRequirements',
        }, {
          label: '天气情况',
          value: 'EngineeringReportWeekWeather',
          anchor: 'weather',
        }, {
          label: '驳回意见',
          value: 'EngineeringReportWeekRejection',
          anchor: 'rejection',
        },
      ],
      baseInfo: {},
      progress: {},
      schedule: [],
      nextplan: {},
      siteRectification: {},
      otherRequirements: {},
      dataSummary: {},
      affectFactors: {},
      workSafety: {},
      weather: {},
      tableConfig: {
        personnel: [{
          name: '施工人员 (工日)',
          key: 'person',
          readonly: true,
        }],
        machine: [{
          name: '施工机械 (台班)',
          key: 'person',
          readonly: true,
        }],
        nextPersonnel: [{
          name: '施工人员 (工日)',
          key: 'person',
          readonly: true,
        }],
        nextMachine: [{
          name: '施工机械 (台班)',
          key: 'person',
          readonly: true,
        }],
        forecast: {
          content: [
            {
              name: '日期',
              key: 'title',
              readonly: true,
              align: true,
            },
          ],
        },
        nextForecast: {
          content: [
            {
              name: '日期',
              key: 'title',
              readonly: true,
              align: true,
            },
          ],
        },
      },
    };
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'weekConfig',
    ]),
    config() {
      return { ...this.weekConfig, ...this.tableConfig };
    },
  },
  async created() {
    const baseInfo = await this.getBaseInfo(this.reportId);
    console.log('baseInfo', baseInfo);
    this.baseInfo = Object.assign({}, baseInfo);
    this.createHead(this.baseInfo.constructionDateStart);
    this.createNextHead(this.baseInfo.constructionDateFinis);

    this.getProgress(this.reportId).then(data => {
      data = data || {};
      // 字段转对象
      Object.keys(data).forEach(k => {
        if (k !== 'id' && k !== 'machine' && k !== 'personnel' && k !== 'stageInfoRemark') {
          data[k] = JSON.parse(data[k] || '[]');
        }
      });
      delete data.machine;
      delete data.personnel;
      this.progress = Object.assign({}, this.progress, data);
      console.log(this.progress);
    });
    this.getProgressPersonnel({ id: this.reportId, type: 0 }).then(data => {
      const personnel = this.config.progress.personnelOption.map(k => {
        const temp = {};
        temp.person = k.label;
        temp.api = k.value;
        (data || []).forEach(item => {
          temp[item.dateAt.split(' ')[0]] = item[k.value];
        });
        return temp;
      });
      this.progress = Object.assign({}, this.progress, {
        personnel,
      });
    });
    this.getProgressMachine({ id: this.reportId, type: 0 }).then(data => {
      const machine = this.config.progress.machineOption.map(k => {
        const temp = {};
        temp.person = k.label;
        temp.api = k.value;
        (data || []).forEach(item => {
          temp[item.dateAt.split(' ')[0]] = item[k.value];
        });
        return temp;
      });
      this.progress = Object.assign({}, this.progress, {
        machine,
      });
    });
    this.getSchedule(this.reportId).then(data => {
      this.schedule = data;
    });
    this.getNextplan(this.reportId).then(data => {
      data = data || {};
      // 字段转对象
      Object.keys(data).forEach(k => {
        if (k !== 'id' && k !== 'machine' && k !== 'personnel' && k !== 'stageInfoRemark') {
          data[k] = JSON.parse(data[k] || '[]');
        }
      });
      delete data.machine;
      delete data.personnel;
      this.nextplan = Object.assign({}, this.nextplan, data);
    });
    this.getProgressPersonnel({ id: this.reportId, type: 1 }).then(data => {
      const personnel = this.config.nextplan.personnelOption.map(k => {
        const temp = {};
        temp.person = k.label;
        temp.api = k.value;
        (data || []).forEach(item => {
          temp[item.dateAt.split(' ')[0]] = item[k.value];
        });
        return temp;
      });
      this.nextplan = Object.assign({}, this.nextplan, {
        personnel,
      });
    });
    this.getProgressMachine({ id: this.reportId, type: 1 }).then(data => {
      const machine = this.config.nextplan.machineOption.map(k => {
        const temp = {};
        temp.person = k.label;
        temp.api = k.value;
        (data || []).forEach(item => {
          temp[item.dateAt.split(' ')[0]] = item[k.value];
        });
        return temp;
      });
      this.nextplan = Object.assign({}, this.nextplan, {
        machine,
      });
    });
    this.getSiteRectification(this.reportId).then(data => {
      data = data || {};
      this.siteRectification = Object.assign({}, {
        ...data,
        rectification: JSON.parse(data.rectification || '[]'),
      });
    });
    this.getWorkSafety(this.reportId).then(data => {
      data = data || {};
      this.workSafety = Object.assign({}, {
        ...data,
        safeInfo: data.safeInfo,
        dangerSource: JSON.parse(data.dangerSource || '[]'),
        nextDangerSource: JSON.parse(data.nextDangerSource || '[]'),
        siteCheck: JSON.parse(data.siteCheck || '[]'),
      });
    });
    this.getOtherRequirements(this.reportId).then(data => {
      data = data || {};
      this.otherRequirements = Object.assign({}, {
        ...data,
        otherRequirements: JSON.parse(data.otherSiteInfo || '[]'),
      });
    });
    this.getAffectFactors(this.reportId).then(data => {
      data = data || {};
      this.affectFactors = Object.assign({}, {
        ...data,
        effect: JSON.parse(data.effect || '[]'),
      });
    });
    this.getDataSummary(this.reportId).then(data => {
      data = data || {};
      this.dataSummary = Object.assign({}, {
        ...data,
        receiveFileIn: JSON.parse(data.receiveFileIn || '[]'),
        receiveFileOut: JSON.parse(data.receiveFileOut || '[]'),
      });
    });

    this.tableConfig.forecast.content = [
      ...this.tableConfig.forecast.content,
      ...this.createWeatherHead(this.baseInfo.constructionDateStart),
    ];
    this.getForecast({
      reportId: this.reportId,
      engineeringId: this.engineeringId,
      startDate: DateFormat(this.baseInfo.constructionDateStart, 'yyyy-MM-dd'),
      endDate: DateFormat(this.baseInfo.constructionDateFinis, 'yyyy-MM-dd'),
    }).then(data => {
      const forecast = this.config.weather.forecastOption.map(k => {
        const temp = {};
        temp.title = k.label;
        temp.api = k.value;
        (data || []).forEach(item => {
          temp[item.predictDate] = item[k.value];
        });
        return temp;
      });

      this.weather = Object.assign({}, this.weather, {
        forecast,
      });
    });

    this.tableConfig.nextForecast.content = [
      ...this.tableConfig.nextForecast.content,
      ...this.createWeatherHead(this.baseInfo.constructionDateStart, -7),
    ];
    const startDate = new Date(this.baseInfo.constructionDateStart);
    this.getForecast({
      reportId: this.reportId,
      engineeringId: this.engineeringId,
      startDate: DateFormat(startDate.setDate(startDate.getDate() + 7), 'yyyy-MM-dd'),
      endDate: DateFormat(startDate.setDate(startDate.getDate() + 6), 'yyyy-MM-dd'),
    }).then(data => {
      const nextForecast = this.config.weather.forecastOption.map(k => {
        const temp = {};
        temp.title = k.label;
        temp.api = k.value;
        (data || []).forEach(item => {
          temp[item.predictDate] = item[k.value];
        });
        return temp;
      });
    
      this.weather = Object.assign({}, this.weather, {
        nextForecast,
      });
    });
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'getBaseInfo',
      'getProgress',
      'getSchedule',
      'getNextplan',
      'getSiteRectification',
      'getOtherRequirements',
      'getAffectFactors',
      'getDataSummary',
      'getWorkSafety',
      'getProgressPersonnel',
      'getProgressMachine',
      'getForecast',
    ]),
    createHead(startDate) {
      const dates = {
        0: '日',
        1: '一',
        2: '二',
        3: '三',
        4: '四',
        5: '五',
        6: '六',
      };
      for (let i = 0; i <= 6; i++) {
        let tempDate = new Date(startDate);
        tempDate.setDate(tempDate.getDate() + i);
        let m = tempDate.getMonth() + 1;
        let d = String(tempDate.getDate()).padStart(2, '0');
        let w = dates[tempDate.getDay()];
        this.tableConfig.personnel.push({
          name: m + '月' + d + '日 星期' + w,
          key: tempDate.getFullYear() + '-' + m + '-' + d,
        });
        this.tableConfig.machine.push({
          name: m + '月' + d + '日 星期' + w,
          key: tempDate.getFullYear() + '-' + m + '-' + d,
        });
      }
    },
    createNextHead(startDate) {
      const dates = {
        0: '日',
        1: '一',
        2: '二',
        3: '三',
        4: '四',
        5: '五',
        6: '六',
      };
      for (let i = 0; i <= 6; i++) {
        let tempDate = new Date(startDate);
        tempDate.setDate(tempDate.getDate() + i);
        let m = tempDate.getMonth() + 1;
        let d = String(tempDate.getDate()).padStart(2, '0');
        let w = dates[tempDate.getDay()];
        this.tableConfig.nextPersonnel.push({
          name: m + '月' + d + '日 星期' + w,
          key: tempDate.getFullYear() + '-' + m + '-' + d,
        });
        this.tableConfig.nextMachine.push({
          name: m + '月' + d + '日 星期' + w,
          key: tempDate.getFullYear() + '-' + m + '-' + d,
        });
      }
    },
    createWeatherHead(startDate, offsetDate = 0) {
      const dates = ['日', '一', '二', '三', '四', '五', '六'];
      const result = [];
      const nowTime = new Date('2019-11-06').getTime();
      for (let i = 0; i <= 6; i++) {
        let tempDate = new Date(startDate);
        tempDate.setDate(tempDate.getDate() + i - offsetDate);
        let m = tempDate.getMonth() + 1;
        let d = String(tempDate.getDate()).padStart(2, '0');
        let w = dates[tempDate.getDay()];
        result.push({
          name: m + '月' + d + '日 星期' + w,
          key: tempDate.getFullYear() + '-' + m + '-' + d,
          align: true,
          readonly: tempDate.getTime() > nowTime,
        });
      }
      return result;
    },
    handleMenuItemClick(routeName) {
      const menu = this.menuList.find(item => item.value === routeName);
      this.$refs.main.scroll(menu.anchor || '');
    },
  },
};
</script>
