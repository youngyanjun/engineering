<template>
  <div>
    <UI
      :tableData="list"
      @add="handleAdd"
      @selectionChange="handleSelectionChange"
      @batchRemove="handleBatchRemove"
    />
    <Add ref="add" @refresh="handleRefresh" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import Add from '../add';

export default {
  name: '',
  components: { UI, Add },
  props: {
    orgId: String,
    roleId: String,
  },
  data() {
    return {
      keyword: '',
      currentPage: 1,
      pageSize: 50,
      selection: [],
    };
  },
  created() {
  },
  computed: {
    ...mapState('deployRolePerson', [
      'list',
      'total',
    ]),
    selectionIds() {
      return this.selection.map(index => (this.list[index] ? this.list[index].userId : ''));
    },
  },
  methods: {
    ...mapActions('deployRolePerson', [
      'getList',
      'getListAll',
      'removeUser',
    ]),
    init() {
      this.getList({ orgId: this.orgId, roleId: this.roleId });
    },
    /**
     * 添加
     */
    handleAdd() {
      console.log('add', this.roleId);
      if (!this.roleId) {
        window.$Message.warning('请先选中一个角色或者角色组');
        return;
      }
      this.$refs.add.open({ id: this.roleId });
    },
    /**
     * 多选
     */
    handleSelectionChange(data) {
      this.selection = data;
    },
    /**
     * 批量移除
     */
    handleBatchRemove() {
      console.log('remove', this.selection, this.selectionIds);
      if (this.selectionIds.length <= 0) {
        window.$Message.warning('至少选择一个');
        return;
      }
      this.removeUser({
        roleId: this.roleId,
        userIds: this.selectionIds,
      }).then(() => {
        window.$Message.success('删除成功');
        this.init();
      });
    },
    /**
     * 刷新列表
     */
    handleRefresh() {
      this.init();
    },
    handlePageChange(page) {
      console.log('pageChange', page);
      this.currentPage = page;
      this.init();
    },
    handlePageSizeChange(pageSize) {
      console.log('pageSizeChange', pageSize);
      this.pageSize = pageSize;
      this.currentPage = 1;
      this.init();
    },
  },
};
</script>
