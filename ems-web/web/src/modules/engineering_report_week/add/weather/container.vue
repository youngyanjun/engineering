<template>
  <UI
    ref="ui"
    :form="form"
    :tableHead="tableHead"
    :config="config"
    @submit="handleSubmit"
    @back="handleBack"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import { format as DateFormat } from '@/utils/date';

export default {
  name: '',
  components: { UI },
  props: {
    reportId: String,
    engineeringId: String,
  },
  data() {
    return {
      form: {},
      tableHead: {
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
        ...this.weekConfig.weather,
        baseInfo: this.baseInfo,
      };
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'getForecast',
      'addForecast',
    ]),
    init() {
      console.log(this.baseInfo);
      this.tableHead.forecast.content = [
        ...this.tableHead.forecast.content,
        ...this.createTableHead(this.baseInfo.constructionDateStart),
      ];
      this.getForecast({
        reportId: this.reportId,
        engineeringId: this.engineeringId,
        startDate: DateFormat(this.baseInfo.constructionDateStart, 'yyyy-MM-dd'),
        endDate: DateFormat(this.baseInfo.constructionDateFinis, 'yyyy-MM-dd'),
      }).then(data => {
        const forecast = this.config.forecastOption.map(k => {
          const temp = {};
          temp.title = k.label;
          temp.api = k.value;
          (data || []).forEach(item => {
            temp[item.predictDate] = item[k.value];
          });
          return temp;
        });

        this.form = Object.assign({}, this.form, {
          forecast,
        });
      });

      this.tableHead.nextForecast.content = [
        ...this.tableHead.nextForecast.content,
        ...this.createTableHead(this.baseInfo.constructionDateStart, -7),
      ];
      const startDate = new Date(this.baseInfo.constructionDateStart);
      this.getForecast({
        reportId: this.reportId,
        engineeringId: this.engineeringId,
        startDate: DateFormat(startDate.setDate(startDate.getDate() + 7), 'yyyy-MM-dd'),
        endDate: DateFormat(startDate.setDate(startDate.getDate() + 6), 'yyyy-MM-dd'),
      }).then(data => {
        const nextForecast = this.config.forecastOption.map(k => {
          const temp = {};
          temp.title = k.label;
          temp.api = k.value;
          (data || []).forEach(item => {
            temp[item.predictDate] = item[k.value];
          });
          return temp;
        });
      
        this.form = Object.assign({}, this.form, {
          nextForecast,
        });
      });
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    createTableHead(startDate, offsetDate = 0) {
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
    handleSubmit({ type, data }) {
      console.log('submit', data);
      data.reportId = this.reportId;
      data.projectId = this.engineeringId;
      // 整理forecast
      const forecastResult = [];
      this.tableHead.forecast.content.forEach(item => {
        if (item.key !== 'title') {
          const temp = {};
          temp.predictDate = item.key;
          data.forecast.forEach(v => {
            temp[v.api] = v[item.key] || null;
          });
          forecastResult.push(temp);
        }
      });
      data.reportForecastVOList = forecastResult;
      this.addForecast(data);
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
