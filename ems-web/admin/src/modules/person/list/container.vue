<template>
  <div>
    <UI
      :tableData="list"
      :searchKeyword="keyword"
      @add="handleAdd"
      @freeze="handleFreeze"
      @unfreeze="handleUnfreeze"
      @freezes="handleFreezes"
      @resetPassword="handleResetPassword"
      @edit="handleEdit"
      @delete="handleDelete"
      @deletes="handleDeletes"
      @search="handleSearch"
      @selectionChange="handleSelectionChange"
      :pageSize="pageSize"
      :recordTotal="total"
      :currentPage="currentPage"
      @pageChange="handlePageChange"
      @pageSizeChange="handlePageSizeChange"
    />
      <Add ref="add" @refresh="handleRefresh" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import Add from '@/modules/person/add';

export default {
  name: '',
  components: { UI, Add },
  data() {
    return {
      keyword: '',
      currentPage: 1,
      pageSize: 50,
      selection: [],
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('person', [
      'list',
      'total',
    ]),
    selectionIds() {
      return this.selection.map(index => (this.list[index] ? this.list[index].id : ''));
    },
  },
  methods: {
    ...mapActions('person', [
      'getList',
      'deleteDetail',
      'resetPassword',
      'freezeDetail',
      'unfreezeDetail',
    ]),
    init() {
      this.getList({
        keyword: this.keyword,
        pageSize: this.pageSize,
        pageNum: this.currentPage,
      });
    },
    /**
     * 编辑
     */
    handleEdit({ data }) {
      console.log('edit', data);
      this.$refs.add.open(data);
    },
    /**
     * 创建
     */
    handleAdd() {
      console.log('add');
      this.$refs.add.open();
    },
    /**
     * 删除
     */
    handleDelete({ data }) {
      console.log('delete', data);
      this.deleteDetail([data.id]).then(() => {
        window.$Message.success('删除成功');
        this.init();
      });
    },
    /**
     * 冻结
     */
    handleFreeze({ data }) {
      console.log('freeze', data);
      this.freezeDetail([data.id]).then(() => {
        window.$Message.success('冻结成功');
        this.init();
      });
    },
    /**
     * 解冻
     */
    handleUnfreeze({ data }) {
      console.log('unfreeze', data);
      this.unfreezeDetail([data.id]).then(() => {
        window.$Message.success('解冻成功');
        this.init();
      });
    },
    handleDeletes() {
      console.log('deletes');
      if (this.selectionIds.length <= 0) {
        window.$Message.warning('至少选择一个');
        return;
      }
      this.deleteDetail(this.selectionIds).then(() => {
        window.$Message.success('删除成功');
        this.init();
      });
    },
    handleResetPassword() {
      console.log('resetPassword');
      if (this.selectionIds.length <= 0) {
        window.$Message.warning('至少选择一个');
        return;
      }
      this.resetPassword(this.selectionIds).then(() => {
        window.$Message.success('重置成功');
      });
    },
    handleFreezes() {
      if (this.selectionIds.length <= 0) {
        window.$Message.warning('至少选择一个');
        return;
      }
      this.freezeDetail(this.selectionIds).then(() => {
        window.$Message.success('冻结成功');
        this.init();
      });
    },
    handleSelectionChange(data) {
      this.selection = data;
    },
    /**
     * 查询
     */
    handleSearch(keyword) {
      console.log('keyword', keyword);
      this.keyword = keyword;
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
    handleRefresh() {
      this.init();
    },
  },
};
</script>
