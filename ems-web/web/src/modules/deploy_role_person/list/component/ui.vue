<template>
  <div class="list">
    <section class="list-hd">
      <div class="list-operates">
        <Button type="danger" plain @click="$emit('batchRemove')">成员删除</Button>
        <Button @click="$emit('add')">添加成员</Button>
      </div>
    </section>
    <section class="list-bd">
      <Table
        :thead="[{
          name: '姓名',
          key: 'userName',
          width: '8%',
        }, {
          name: '邮箱',
          key: 'email',
          width: '14%',
        }, {
          name: '手机',
          key: 'phone',
          width: '14%',
        }, {
          name: '角色',
          key: 'roles',
          width: '50%',
        }]"
        :tbody="tableData"
        :selected="true"
        @selectionChange="$emit('selectionChange', arguments[0])"
      >
      </Table>
    </section>
  </div>
</template>

<script>
import { Table, Input, Pagination, Button, Divider } from '@/components/ls';

export default {
  name: 'PersonListUI',
  components: { Button, Table, Divider, Input, Pagination },
  props: {
    tableData: {
      type: Array,
      default: () => [],
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
