<template>
  <div class="list">
    <section class="list-hd">
      <div class="list-operates"></div>
      <Input
        class="list-filter"
        :value="searchKeyword"
        @suffixClick="$emit('search', arguments[0])"
        placeholder="请输入字段组..."
      />
    </section>
    <section class="list-bd">
      <Table
        :thead="[{
          name: '字段组名称',
          key: 'name',
          width: '20%',
        }, {
          name: '包含字段数',
          key: 'fieldsSum',
          align: 'center',
          width: '20%',
          filter: filterFieldsSum,
        }, {
          name: '内容',
          key: 'dictionaryChild',
          filter: filterDictionaryChild,
        }, {
          name: '操作',
          key: 'operate',
          align: 'center',
          slot: true,
          width: '10%',
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
  methods: {
    filterDictionaryChild(val) {
      let result = [];
      try {
        result = JSON.parse(val);
      } catch (e) {
        console.log(e);
      }
      return result.join(';');
    },
    filterFieldsSum(val, data) {
      let result = [];
      try {
        result = JSON.parse(data.dictionaryChild);
      } catch (e) {
        console.log(e);
      }
      return result.length;
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
