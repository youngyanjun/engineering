<template>
  <div>
    <UI
      :statusOptions="test[1].data.statusOptions"
      :list="test[1].data.list"
      @itemClick="handleItemClick.call(this, arguments[0])"
    />
    <Add ref="add" @refresh="() => {}" />
  </div>
</template>

<script>
import UI from './ui';
import Add from '../add';

export default {
  name: '',
  components: { UI, Add },
  data() {
    return {
      test: [{
        scene: '正常数据',
        data: {
          statusOptions: [{
            label: '未开始',
            value: 1,
          }, {
            label: '进行中',
            value: 2,
          }, {
            label: '已完成',
            value: 3,
          }],
          list: [{
            id: '1',
            name: '项目名称1',
            leader: '张三',
            status: 1,
            createdDate: '2019-12-05',
          }, {
            id: '2',
            name: '项目名称2',
            leader: '张三',
            status: 2,
            createdDate: '2019-12-05',
          }, {
            id: '3',
            name: '项目名称3',
            leader: '张三',
            status: 3,
            createdDate: '2019-12-05',
          }],
        },
      }, {
        scene: '缺省字段',
        data: {
          statusOptions: [{
            label: '未开始',
            value: 1,
          }, {
            label: '进行中',
            value: 2,
          }, {
            label: '已完成',
            value: 3,
          }],
          list: [{
            id: '1',
            name: '项目名称1',
            leader: '张三',
            status: 1,
            createdDate: '2019-12-05',
          }, {
            id: '2',
            name: null,
            leader: '张三',
            status: 2,
            createdDate: '2019-12-05',
          }, {
            id: '3',
            name: '项目名称3',
            leader: null,
            status: 3,
            createdDate: '2019-12-05',
          }, {
            id: '4',
            name: '项目名称4',
            leader: '张三',
            status: null,
            createdDate: '2019-12-05',
          }, {
            id: '5',
            name: '项目名称5',
            leader: '张三',
            status: 2,
            createdDate: null,
          }],
        },
      }],
    };
  },
  methods: {
    handleItemClick(data) {
      this.$refs.add.open(data);
    },
  },
};
</script>
