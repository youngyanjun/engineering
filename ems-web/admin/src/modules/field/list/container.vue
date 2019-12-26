<template>
  <div>
    <UI
      :tableData="list"
      :searchKeyword="keyword"
      @edit="handleEdit"
      @search="handleSearch"
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
  data() {
    return {
      keyword: '',
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('field', [
      'list',
    ]),
  },
  methods: {
    ...mapActions('field', [
      'getList',
      'updateDetail',
    ]),
    init() {
      this.getList({
        keyword: this.keyword,
      });
    },
    /**
     * 编辑
     */
    handleEdit({ data }) {
      console.log(data);
      this.$refs.add.open(data);
    },
    /**
     * 查询
     */
    handleSearch(keyword) {
      console.log('keyword', keyword);
      this.keyword = keyword;
      this.init();
    },
    handleRefresh() {
      this.init();
    },
  },
};
</script>
