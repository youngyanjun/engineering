<template>
  <div class="list">
    <section class="list-hd">
      <div class="list-operates">
        <Button class="list-primary-btn" type="default" @click="$emit('add')">创建分包单位</Button>
      </div>
      <Input
        class="list-filter"
        placeholder="请输入分包名称..."
        :value="searchKeyword"
        @suffixClick="$emit('search', arguments[0])"
      />
    </section>
    <section class="list-bd">
      <Table
        :thead="[{
          name: '分包单位名称',
          key: 'unitName',
        }, {
          name: '分包联系人',
          key: 'contact',
        }, {
          name: '电话',
          key: 'phone',
        }, {
          name: '创建时间',
          key: 'createDate',
          filter: data => DateFormat(data, 'yyyy-MM-dd'),
        }, {
          name: '服务项目数',
          key: 'engineeringSum',
          align: 'center',
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
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
