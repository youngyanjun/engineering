<template>
  <div class="list-wrapper">
    <div class="list-top">
      <Button class="list-primary-btn" @click="$emit('addWeek')">新建周报</Button>
      <!-- <Button @click="$emit('addMonth')">新建月报</Button> -->
    </div>
    <div class="list">
      <section class="list-hd">
        <div class="list-operates">
          <label class="list-operates-label">名称</label>
          <Input
            class="list-operates-filter"
            :value="searchKeyword"
            :suffix="true"
            @suffixClick="$emit('search', arguments[0])"
            placeholder="请输入..."
          />
          <label class="list-operates-label">报表类型</label>
          <Select class="list-operates-filter" :value="searchType" @input="$emit('searchTypeChange', arguments[0])">
            <Option value="-1" label="全部" />
            <Option value="0" label="周报" />
            <Option value="1" label="月报" />
          </Select>
        </div>
      </section>
      <section class="list-bd">
        <Table
          :thead="[{
            name: '名称',
            key: 'reportName',
            width: '20%',
          }, {
            name: '月份',
            key: 'month',
            filter: v => v ? v + '月份' : '',
          }, {
            name: '报表类型',
            key: 'reportType',
            width: '14%',
            filter: v => config.typeMap[v],
          }, {
            name: '最近修改时间',
            key: 'updateDate',
            align: 'center',
            width: '20%',
          }, {
            name: '状态',
            key: 'status',
            width: '10%',
            slot: true,
          }, {
            name: '操作',
            key: 'operate',
            align: 'center',
            slot: true,
            width: '20%',
          }]"
          :tbody="tableData"
          :selected="false"
          @selectionChange="$emit('selectionChange', arguments[0])"
        >
          <template slot="status" slot-scope="scope">
            <span :class="[$style.status, { 'is-gray': scope.data.status === '1' }]">
              {{ config.statusMap[scope.data.status] || config.statusMap.default }}
            </span>
          </template>
          <template slot="operate" slot-scope="scope">
            <span class="list-operate">
              <Button @click="$emit('edit', scope)" :disabled="scope.data.status === '1'">编辑</Button>
            </span>
          </template>
        </Table>
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
  </div>
</template>

<script>
import { Table, Input, Pagination, Button, Divider, Select, Option } from '@/components/ls';

export default {
  name: 'PersonListUI',
  components: { Button, Table, Divider, Input, Pagination, Select, Option },
  props: {
    tableData: {
      type: Array,
      default: () => [],
    },
    searchKeyword: {
      type: String,
      default: '',
    },
    searchType: {
      type: [String, Number],
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
    config: {
      type: Object,
      default: () => {},
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
