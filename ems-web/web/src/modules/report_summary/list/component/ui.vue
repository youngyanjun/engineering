<template>
  <div class="list-wrapper">
    <div class="list">
      <section class="list-hd">
        <div class="list-operates">
          <label class="list-operates-label">名称</label>
          <Input
            class="list-operates-filter"
            :value="filterKeyword"
            :suffix="true"
            @suffixClick="$emit('filterKeywordChange', arguments[0])"
            placeholder="请输入..."
          />
          <label class="list-operates-label">类型</label>
          <Select class="list-operates-filter" :value="filterType" @input="$emit('filterTypeChange', arguments[0])">
            <Option
              :value="option.value"
              :label="option.label"
              v-for="option in filterTypeOptions"
              :key="option.value"
            />
          </Select>
        </div>
      </section>
      <section class="list-bd">
        <Table
          :thead="[{
            name: '名称',
            key: 'name',
            width: '16%',
          }, {
            name: '报表类型',
            key: 'type',
            width: '8%',
            align: 'center',
            filter: v => filterTypeMap[v].label,
          }, {
            name: '最近修改时间',
            key: 'updateAt',
            width: '16%',
            align: 'center',
            filter: v => DateFormat(v, 'yyyy-MM-dd hh:mm:ss'),
          }, {
            name: '状态',
            key: 'state',
            width: '8%',
            align: 'center',
            slot: true,
          }, {
            name: '收集完成情况（实收/应收）',
            key: 'officialReceipts',
            width: '16%',
            align: 'center',
            slot: true,
          }, {
            name: '操作',
            key: 'operate',
            width: '8%',
            align: 'center',
            slot: true,
          }]"
          :tbody="tableData"
          :selected="false"
          @selectionChange="$emit('selectionChange', arguments[0])"
        >
          <template slot="officialReceipts" slot-scope="scope">
            <span :class="$style.state">
              {{ scope.data.officialReceipts }} / {{ scope.data.receivable }}
            </span>
          </template>
          <template slot="state" slot-scope="scope">
            <span :class="[$style.state, { 'is-gray': scope.data.state === '1' }]">
              {{ fieldStateMap[scope.data.state].label }}
            </span>
          </template>
          <template slot="operate" slot-scope="scope">
            <span class="list-operate">
              <Button @click="$emit('detailClick', scope)">审查</Button>
            </span>
          </template>
        </Table>
      </section>
      <section class="list-ft">
        <Pagination :total="pageRecordTotal" :pageSize="pageSize" :currentPage="pageNum" :pageSizes="[50, 100, 200]"
          @currentChange="$emit('pageNumChange', arguments[0])"
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
    fieldStateOptions: {
      type: Array,
      default: () => [],
    },
    filterKeyword: String,
    filterType: [String, Number],
    filterTypeOptions: {
      type: Array,
      default: () => [],
    },
    pageSize: {
      type: Number,
      default: 0,
    },
    pageNum: {
      type: Number,
      default: 0,
    },
    pageRecordTotal: {
      type: Number,
      default: 0,
    },
  },
  computed: {
    filterTypeMap() {
      return this.filterTypeOptions.reduce((map, item) => (map[item.value] = item, map), {});
    },
    fieldStateMap() {
      return this.fieldStateOptions.reduce((map, item) => (map[item.value] = item, map), {});
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
