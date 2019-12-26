<template>
  <UI
    :tableData="tableData"
    :filterKeyword="filterKeyword"
    :filterType="filterType"
    :filterSource="filterSource"
    :filterMonth="filterMonth"
    :filterSourceOptions="filterSourceOptions"
    :filterTypeOptions="filterTypeOptions"
    :pageNum="pageNum"
    :pageRecordTotal="baseRecordTotal"
    :pageSize="pageSize"
    @filterKeywordChange="handleFilterChange.call(this, 'filterKeyword', arguments[0])"
    @filterTypeChange="handleFilterChange.call(this, 'filterType', arguments[0])"
    @filterSourceChange="handleFilterChange.call(this, 'filterSource', arguments[0])"
    @filterMonthChange="handleFilterChange.call(this, 'filterMonth', arguments[0])"
    @pageNumChange="handleFilterChange.call(this, 'pageNum', arguments[0])"
    @pageSizeChange="handleFilterChange.call(this, 'pageSize', arguments[0])"
    @detailClick="handleDetailClick"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  props: {},
  data() {
    return {
      filterKeyword: '',
      filterType: '',
      filterSource: '',
      filterMonth: '',
      filterTypeOptions: [{
        label: '全部',
        value: '',
      }, {
        label: '周报',
        value: '0',
      }],
      filterSourceOptions: [{
        label: '全部',
        value: '',
      }, {
        label: '项目部1',
        value: '0001',
      }, {
        label: '项目部2',
        value: '0002',
      }, {
        label: '项目部3',
        value: '0003',
      }, {
        label: '设计院',
        value: '0004',
      }, {
        label: '数字中心',
        value: '0005',
      }, {
        label: '制造',
        value: '0006',
      }],
      pageNum: 1,
      pageSize: 50,
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('reportSummary', [
      'baseList',
      'baseRecordTotal',
    ]),
    tableData() {
      console.log(this.baseList);
      return this.baseList.map(item => ({
        ...item,
        id: item.id,
        name: item.reportName,
        type: item.reportType,
        source: item.orgName,
        updateAt: item.updateDate,
      }));
    },
  },
  methods: {
    ...mapActions('reportSummary', [
      'getBaseList',
    ]),
    init() {
      console.log('base => init');
      this.getBaseList({
        reportName: this.filterKeyword,
        reportType: this.filterType,
        orgId: this.filterSource,
        reportMonth: this.filterMonth,
        pageNum: this.pageNum,
        pageSize: this.pageSize,
      });
    },
    handleFilterChange(type, data) {
      console.log(type, JSON.stringify(data));
      this[type] = data;
      this.init();
    },
    handleDetailClick({ data }) {
      if (data.orgType === '8') {
        this.$router.push({
          name: 'EngineeringReportWeekPreview',
          params: {
            engineeringId: data.orgId,
            orgType: '8',
            reportId: data.id,
            viewType: 'other',
          },
        });
      } else {
        this.$router.push({
          name: 'DepartmentReportWeekPreview',
          params: {
            engineeringId: data.orgId,
            orgType: data.orgType,
            reportId: data.id,
            viewType: 'other',
          },
        });
      }
    },
  },
};
</script>
