<template>
  <div class="list">
    <section class="list-hd">
      <div class="list-operates">
        <Button class="list-primary-btn" type="default">系统日志</Button>
      </div>
      <DatePicker
        type="datetimerange"
        :value="dateRange"
        @input="$emit('dateRangeChange', arguments[0])"
        value-format="yyyy-M-d HH:mm:ss"
      />
    </section>
    <section class="list-bd">
      <Table
        :thead="[{
          name: '操作时间',
          key: 'createDate',
        }, {
          name: '操作用户',
          key: 'userName',
        }, {
          name: '模块',
          key: 'module',
        }, {
          name: '操作行为',
          key: 'action',
        }]"
        :tbody="tableData"
      />
    </section>
    <section class="list-ft">
      <Pagination :total="recordTotal" :pageSize="pageSize" :currentPage="currentPage" :pageSizes="[50, 100, 200]"
        @currentChange="$emit('pageChange', arguments[0])"
        @pageSizeChange="$emit('pageSizeChange', arguments[0])"
        @prevClick="$emit('pageChange', arguments[0])"
        @nextClick="$emit('pageChange', arguments[0])"
      />
    </section>
  </div>
</template>

<script>
import { DatePicker } from 'element-ui';
import { Table, Input, Pagination, Button, Divider } from '@/components/ls';

export default {
  name: 'PersonListUI',
  components: { Button, Table, Divider, Input, Pagination, DatePicker },
  props: {
    tableData: {
      type: Array,
      default: () => [],
    },
    pageSize: {
      type: Number,
      default: 0,
    },
    recordTotal: {
      type: Number,
      default: 0,
    },
    currentPage: {
      type: Number,
      default: 0,
    },
    dateRange: {
      type: Array,
      default: () => [],
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
