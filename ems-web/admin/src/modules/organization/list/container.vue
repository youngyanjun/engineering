<template>
  <div>
    <UI
      :tableData="list"
      :searchKeyword="keyword"
      :typeMap="rangesMap"
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
    ...mapState('organization', [
      'list',
    ]),
    ...mapState('project', [
      'ranges',
    ]),
    rangesMap() {
      const result = {};
      this.ranges.forEach(item => {
        result[item.value] = item;
      });
      return result;
    },
  },
  methods: {
    ...mapActions('organization', [
      'getList',
    ]),
    init() {
      const { keyword } = this;
      this.getList({ keyword });
    },
    handleEdit({ data }) {
      console.log('edit', data);
      this.$refs.add.open(data);
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
