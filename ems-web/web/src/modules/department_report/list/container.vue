<template>
  <div>
    <UI
      :config="config"
      :tableData="records"
      @addWeek="handleAdd.call(this, 'DepartmentReportWeekAdd')"
      @addMonth="handleAdd.call(this, 'EngineeringReportWeekAdd')"
      @addSchedule="handleAdd.call(this, 'EngineeringReportWeekAdd')"
      @edit="handleEdit"
      :searchKeyword="keyword"
      :searchType="type"
      @search="handleSearch"
      @searchTypeChange="handleSearchTypeChange"
      :pageSize="pageSize"
      :recordTotal="total"
      :currentPage="currentPage"
      @pageChange="handlePageChange"
      @pageSizeChange="handlePageSizeChange"
    />
    <Create ref="create" :engineeringId="engineeringId" :orgType="orgType" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import Create from '@/modules/department_report_week/create';

export default {
  name: '',
  components: { UI, Create },
  props: {
    engineeringId: String,
    orgType: String,
  },
  data() {
    return {
      keyword: '',
      type: '-1', // 默认全部
      currentPage: 1,
      pageSize: 50,
      config: {
        statusMap: {
          0: '待提交',
          1: '已提交',
          default: '待提交',
        },
        typeMap: {
          0: '周报',
          1: '月报',
          2: '形象进度表',
        },
      },
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('departmentReport', [
      'records',
      'total',
    ]),
  },
  methods: {
    ...mapActions('departmentReport', [
      'getRecords',
    ]),
    init() {
      this.getRecords({
        organizationId: this.engineeringId,
        type: this.type,
        keyword: this.keyword,
        pageSize: this.pageSize,
        pageNum: this.currentPage,
      });
    },
    /**
     * 编辑
     */
    handleEdit({ data }) {
      console.log('edit', data);
      this.$router.push({
        name: 'DepartmentReportWeekAdd',
        params: {
          engineeringId: this.engineeringId,
          reportId: data.id,
          orgType: this.orgType,
        },
      });
    },
    /**
     * 创建
     */
    handleAdd(name) {
      console.log('add');
      this.$refs.create.open();
    },
    /**
     * 查询
     */
    handleSearch(keyword) {
      console.log('keyword', keyword);
      this.keyword = keyword;
      this.init();
    },
    handleSearchTypeChange(type) {
      console.log('type', type);
      this.type = type;
      this.init();
    },
    handlePageChange(page) {
      console.log('pageChange', page);
      this.currentPage = page;
      this.init();
    },
    handlePageSizeChange(pageSize) {
      console.log('pageSizeChange', pageSize);
      this.pageSize = pageSize;
      this.currentPage = 1;
      this.init();
    },
    handleRefresh() {
      this.init();
    },
  },
};
</script>
