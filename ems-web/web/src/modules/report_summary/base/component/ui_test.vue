<template>
  <UI
    :tableData="test[0].data.tableData"
    :filterKeyword="test[0].data.filterKeyword"
    :filterType="test[0].data.filterType"
    :filterSource="test[0].data.filterSource"
    :filterMonth="test[0].data.filterMonth"
    :filterSourceOptions="test[0].data.filterSourceOptions"
    :filterTypeOptions="test[0].data.filterTypeOptions"
    :pageNum="test[0].data.pageNum"
    :pageRecordTotal="test[0].data.pageRecordTotal"
    :pageSize="test[0].data.pageSize"
    @filterKeywordChange="handleEvent.call(this, 'filterKeywordChange', arguments[0])"
    @filterTypeChange="handleEvent.call(this, 'filterTypeChange', arguments[0])"
    @filterSourceChange="handleEvent.call(this, 'filterSourceChange', arguments[0])"
    @filterMonthChange="handleEvent.call(this, 'filterMonthChange', arguments[0])"
    @pageNumChange="handleEvent.call(this, 'pageNumChange', arguments[0])"
    @pageSizeChange="handleEvent.call(this, 'pageSizeChange', arguments[0])"
  />
</template>

<script>
import UI from './ui';

export default {
  name: '',
  components: { UI },
  props: {},
  data() {
    return {
      test: [{
        scene: '正常数据',
        data: {
          tableData: [{
            name: 'xxxx',
            type: '0',
            updateAt: '2018-12-01',
          }],
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
          pageRecordTotal: 2,
          pageSize: 1,
        },
      }],
    };
  },
  methods: {
    handleEvent(type, data) {
      console.log(type, JSON.stringify(data));
    },
  },
};
</script>
