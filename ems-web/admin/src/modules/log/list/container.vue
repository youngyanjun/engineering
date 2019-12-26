<template>
  <UI
    :tableData="list"
    :dateRange="dateRange"
    @dateRangeChange="handleDateRangeChange"
    :pageSize="pageSize"
    :recordTotal="total"
    :currentPage="currentPage"
    @pageChange="handlePageChange"
    @pageSizeChange="handlePageSizeChange"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import { format as DateFormat } from '@/utils/date';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  data() {
    return {
      dateRange: [],
      currentPage: 1,
      pageSize: 50,
    };
  },
  created() {
    const startTime = new Date();
    const endTime = new Date();
    startTime.setDate(new Date().getDate() - 30);
    this.dateRange[0] = DateFormat(startTime, 'yyyy-MM-dd hh:mm:ss');
    this.dateRange[1] = DateFormat(endTime, 'yyyy-MM-dd hh:mm:ss');
    this.init();
  },
  computed: {
    ...mapState('log', [
      'list',
      'total',
    ]),
  },
  methods: {
    ...mapActions('log', [
      'getList',
    ]),
    init() {
      this.getList({
        startTime: this.dateRange[0],
        endTime: this.dateRange[1],
        pageSize: this.pageSize,
        pageNum: this.currentPage,
      });
    },
    handleDateRangeChange(range) {
      console.log('dateRangeChange', range);
      this.dateRange = range;
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
  },
};
</script>
