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
    structureId: String,
    isAll: Boolean,
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
    ...mapState('deployStructurePerson', [
      'list',
      'total',
    ]),
    selectionIds() {
      return this.selection.map(index => (this.list[index] ? this.list[index].userId : ''));
    },
  },
  methods: {
    ...mapActions('deployStructurePerson', [
      'getList',
      'getListAll',
      'removeUserByStructure',
      'removeUserByOrg',
    ]),
    init() {
      if (this.isAll) {
        this.getListAll(this.orgId);
      } else {
        this.getList(this.structureId);
      }
    },
    /**
     * 添加
     */
    handleAdd() {
      console.log('add', this.structureId);
      this.$refs.add.open({ id: this.structureId });
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
      if (this.isAll) {
        this.removeUserByOrg({
          orgId: this.orgId,
          userIds: this.selectionIds,
        }).then(() => {
          window.$Message.success('删除成功');
          this.init();
        });
      } else {
        this.removeUserByStructure({
          structureId: this.structureId,
          userIds: this.selectionIds,
        }).then(() => {
          window.$Message.success('删除成功');
          this.init();
        });
      }
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
