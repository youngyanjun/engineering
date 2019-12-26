<template>
  <div>
    <UI
      :tableData="list"
      :searchKeyword="keyword"
      @edit="handleEdit"
      @search="handleSearch"
      @add="handleAdd"
    />
    <Add ref="add" @refresh="handleRefresh" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import Add from '../add';
import UI from './component/ui';

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
    ...mapState('subpackage', [
      'list',
    ]),
  },
  methods: {
    ...mapActions('subpackage', [
      'getRecords',
    ]),
    init() {
      const { keyword } = this;
      this.getRecords({ keyword, pageSize: 500 });
    },
    handleEdit({ data }) {
      console.log('edit', data);
      this.$refs.add.open(data.id);
    },
    handleSearch(keyword) {
      this.keyword = keyword;
      this.init();
    },
    handleAdd() {
      this.$refs.add.open();
    },
    handleRefresh() {
      this.init();
    },
  },
};
</script>
