<template>
  <div class="list">
    <section class="list-hd">
      <div class="list-operates">
        <Button class="list-primary-btn" type="default" @click="$emit('add')">添加成员</Button><!--
      --><Button @click="$emit('resetPassword')">重置密码</Button><!--
      --><Button @click="$emit('freezes')">账号冻结</Button><!--
      --><Button type="danger" plain @click="$emit('deletes')">成员删除</Button>
      </div>
      <Input
        class="list-filter"
        :value="searchKeyword"
        @suffixClick="$emit('search', arguments[0])"
        placeholder="请输入..."
      >
        <template slot="prepend">姓名</template>
      </Input>
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
        }, {
          name: '手机',
          key: 'phone',
          width: '14%',
        }, {
          name: '所在项目数',
          key: 'fieldsSum',
          align: 'center',
          width: '20%',
        }, {
          name: '状态',
          key: 'statusLabel',
          width: '10%',
        }, {
          name: '操作',
          key: 'operate',
          align: 'center',
          slot: true,
          width: '20%',
        }]"
        :tbody="tableData"
        :selected="true"
        @selectionChange="$emit('selectionChange', arguments[0])"
      >
        <template slot="operate" slot-scope="scope">
          <Button class="list-operate" type="text" @click="$emit('freeze', scope)" v-if="scope.data.status !== '2'">冻结</Button><!--
       --><Button class="list-operate" type="text" @click="$emit('unfreeze', scope)" v-else>解冻</Button><!--
       --><Divider direction="vertical" /><!--
       --><Button class="list-operate" type="text" @click="$emit('edit', scope)">编辑</Button><!--
       --><Divider direction="vertical" /><!--
       --><Button class="list-operate" type="text" @click="$emit('delete', scope)">删除</Button>
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
    searchKeyword: {
      type: String,
      default: '',
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
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
