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
          <label class="list-operates-label">来源</label>
          <Select class="list-operates-filter" :value="filterSource" @input="$emit('filterSourceChange', arguments[0])">
            <Option
              :value="option.value"
              :label="option.label"
              v-for="option in filterSourceOptions"
              :key="option.value"
            />
          </Select>
          <label class="list-operates-label">月份</label>
          <Select class="list-operates-filter" :value="filterMonth" @input="$emit('filterMonthChange', arguments[0])">
            <Option value="" label="全部" />
            <Option
              :value="`${new Date().getFullYear()}-${String(13 - i).padStart(2, '0')}`"
              :label=" new Date().getMonth() + 1 === 13 - i ? `${13 - i}月份(当前)` : `${13 - i}月份`"
              v-for="i in 12"
              :key="i"
            />
          </Select>
        </div>
      </section>
      <section class="list-bd">
        <Table
          :thead="[{
            name: '名称',
            key: 'name',
            width: '20%',
          }, {
            name: '报表类型',
            key: 'type',
            width: '14%',
            filter: v => filterTypeMap[v].label,
          }, {
            name: '来源',
            key: 'source',
            width: '10%',
          }, {
            name: '最近修改时间',
            key: 'updateAt',
            align: 'center',
            width: '20%',
            filter: v => DateFormat(v, 'yyyy-MM-dd hh:mm:ss'),
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
          <template slot="operate" slot-scope="scope">
            <span class="list-operate">
              <Button @click="$emit('detailClick', scope)">查看</Button>
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
    filterKeyword: String,
    filterType: [String, Number],
    filterTypeOptions: {
      type: Array,
      default: () => [],
    },
    filterSource: String,
    filterSourceOptions: {
      type: Array,
      default: () => [],
    },
    filterMonth: String,
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
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
