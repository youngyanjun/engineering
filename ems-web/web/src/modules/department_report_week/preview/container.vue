<template>
  <Layout
    :selfView="viewType === 'self'"
    @submit="handleSubmit"
  >
    <UI
      :thead="thead"
      :tbody="tbody"
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
    viewType: String,
  },
  data() {
    return {
      intervalId: null,
      tbody: [],
      currentEngineeringId: '',
    };
  },
  created() {
    this.init();
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
    ]),
    thead() {
      return this.thead1;
    },
    theadKey() {
      return this.theadKey1;
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
              return { value: record[k], style: { readonly: true } };
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
  },
  destroyed() {
    window.clearInterval(this.intervalId);
  },
};
</script>
