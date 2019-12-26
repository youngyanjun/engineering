<template>
  <div class="list">
    <section class="list-hd">
      <div class="list-operates">
        <Button class="list-primary-btn" type="default" @click="$emit('add')">创建项目</Button>
      </div>
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
          name: '项目名称',
          key: 'name',
          width: '20%',
        }, {
          name: '范围',
          key: 'gather',
          filter: data => (data || '').split(';').map(k => rangesMap[k]).join(';'),
        }, {
          name: '管理员',
          key: 'managerName',
          width: '10%',
        }, {
          name: '管理员账户名',
          key: 'managerPhone',
          width: '14%',
        }, {
          name: '创建时间',
          key: 'createDate',
          width: '20%',
          filter: data => DateFormat(data, 'yyyy-MM-dd'),
        }, {
          name: '操作',
          key: 'operate',
          align: 'center',
          width: '8%',
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
    rangesMap: {
      type: Object,
      default: () => {},
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
