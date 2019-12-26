<template>
  <div class="list">
    <section class="list-hd">
      <div class="list-operates"></div>
      <Input
        class="list-filter"
        :value="searchKeyword"
        @suffixClick="$emit('search', arguments[0])"
        placeholder="请输入组织名称"
      />
    </section>
    <section class="list-bd">
      <Table
        :thead="[{
          name: '名称',
          key: 'name',
        }, {
          name: '类型',
          key: 'type',
          filter: data => typeMap[data].label,
        }, {
          name: '管理员',
          key: 'manager',
        }, {
          name: '管理员账户名',
          key: 'phone',
        }, {
          name: '创建时间',
          key: 'createDate',
          filter: data => DateFormat(data, 'yyyy-MM-dd'),
        }, {
          name: '成员数',
          key: 'groupSum',
          align: 'center',
          filter: data => data || 0,
        }, {
          name: '操作',
          key: 'operate',
          align: 'center',
          slot: true,
        }]"
        :tbody="tableData"
      >
        <template slot="operate" slot-scope="scope">
          <Button class="list-operate" type="text" @click="$emit('edit', scope)">编辑</Button>
        </template>
      </Table>
    </section>
  </div>
</template>

<script>
import { Table, Input, Button, Divider } from '@/components/ls';

export default {
  name: 'PersonListUI',
  components: { Button, Table, Divider, Input },
  props: {
    tableData: {
      type: Array,
      default: () => [],
    },
    searchKeyword: {
      type: String,
      default: '',
    },
    typeMap: {
      type: Object,
      default: () => {},
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
