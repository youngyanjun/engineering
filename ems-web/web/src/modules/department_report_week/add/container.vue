<template>
  <Layout
    @preview="handlePreview"
    @submit="handleSubmit"
  >
    <UI
      :thead="thead"
      :tbody="tbody"
      :msgOptions="msgOptions"
      @currentCellChange="handleCurrentCellChange"
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
    orgType: String,
  },
  data() {
    return {
      intervalId: null,
      tbody: [],
      currentEngineeringId: '',
      // msgOptions: [{
      //   label: '设计',
      //   value: '1',
      // }, {
      //   label: '施工',
      //   value: '2',
      // }, {
      //   label: 'BIM',
      //   value: '3',
      // }],
    };
  },
  created() {
    this.intervalId = setInterval(() => {
      console.log('autosave');
    }, 60 * 1000);
    this.init();
    this.getMsgUsers(this.orgType);
  },
  computed: {
    ...mapState([
      'projectRanges',
    ]),
    ...mapState('departmentReportWeek', [
      'thead1',
      'theadKey1',
      'thead2',
      'theadKey2',
      'msgUsers',
    ]),
    projectRangesMap() {
      const result = {};
      this.projectRanges.forEach(item => {
        result[item.value] = item;
      });
      return result;
    },
    thead() {
      return this.thead1;
    },
    theadKey() {
      return this.theadKey1;
    },
    msgOptions() {
      let result = [];
      let eng = {};
      eng = this.msgUsers.find((item) => item.engId === this.currentEngineeringId) || {};
      result = (eng.list || []).map(item => ({ label: this.projectRangesMap[item.orgName].label, value: item.userId }));
      console.log('msgOptions', result);
      return result;
    },
  },
  methods: {
    ...mapActions('departmentReportWeek', [
      'getRecord',
      'saveRecord',
      'submitReport',
      'getMsgUsers',
    ]),
    init() {
      this.getRecord(this.reportId).then(data => {
        this.tbody = [];
        data.forEach((record, no) => {
          this.tbody.push([
            {
              value: String(no + 1),
              style: {
                readonly: true,
              },
              source: {
                id: record.id,
                engineeringId: record.engineeringId,
              },
            },
            ...this.theadKey.map(k => {
              if (k === 'engName' || k === 'department') {
                return { value: record[k], style: { readonly: true } };
              } else if (k === 'coordination') {
                return { value: record[k], style: { msgDisabled: false } };
              } else {
                return { value: record[k], style: {} };
              }
            }),
          ]);
        });
      });
    },
    getData() {
      const result = [];
      this.tbody.forEach(data => {
        const record = {
          id: data[0].source.id,
          engineeringId: data[0].source.engineeringId,
          reportId: this.reportId,
        };
        this.theadKey.forEach((k, i) => {
          record[k] = data[i + 1].value;
        });
        result.push(record);
      });
      return result;
    },
    handlePreview() {
      const records = this.getData();
      this.saveRecord(records).then(() => {
        this.$router.push({ name: 'DepartmentReportWeekPreview', params: { viewType: 'self' } });
      });
    },
    handleSubmit() {
      const records = this.getData();
      this.saveRecord(records).then(() => {
        this.submitReport({
          reportId: this.reportId,
          smList: records.map(item => item.coordination),
        }).then(() => {
          window.$Message.success('提交成功');
          this.$router.push({
            name: 'DepartmentReportList',
          });
        });
      });
    },
    handleCurrentCellChange(data) {
      this.currentEngineeringId = this.tbody[data.row][0].source.engineeringId;
      console.log('currentEngineeringId', this.currentEngineeringId);
    },
  },
  destroyed() {
    window.clearInterval(this.intervalId);
  },
};
</script>
