<template>
  <div class="ls-pagination">
    <span>当前共 {{total}} 条</span>
    <label class="ls-pagination-pagesize">
      <span>{{ pageSize }}条/页</span>
      <span class="ls-pagination-pagesize-btn">
        <i class="ls-icon-up" @click="handlePageSizeChange.call(this, 'increase')"/>
        <i class="ls-icon-down" @click="handlePageSizeChange.call(this, 'minus')" />
      </span>
    </label>
    <span class="ls-pagination-prev ls-pagination-item" @click="handlePrevClick"><i class="ls-icon-left" /></span>
    <!-- start页码 -->
    <span v-for="item in pageNum" :class="['ls-pagination-item', { 'is-active': current === item}]"
      @click="handleCurrentChange.call(this, item)" v-show="
        item >= current - 2 && item <= current + 2 ||
        item >= pageNum - 1 ||
        item <= 2">{{
        item >= current - 2 && item <= current + 2 ||
        item === pageNum ||
        item === 1
        ? item : '...'
      }}</span>
    <!-- end页码 -->
    <span class="ls-pagination-next ls-pagination-item" @click="handleNextClick"><i class="ls-icon-right" /></span>
    <label>
      跳至
      <input class="ls-pagination-jump" v-model="jumpPage" @blur="handleCurrentChange.call(this, jumpPage)" />
      页
    </label>
  </div>
</template>

<script>
export default {
  name: 'LsPagination',
  props: {
    total: {
      type: Number,
      default: 0,
    },
    pageSize: {
      type: Number,
      default: 10,
    },
    currentPage: {
      type: Number,
      default: 1,
    },
    pageSizes: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      current: 4,
      jumpPage: '',
      innerPageSize: 0,
    };
  },
  created() {
    this.innerPageSize = this.pageSize || this.pageSizes[0];
    this.current = this.currentPage;
  },
  computed: {
    pageNum() {
      return Math.ceil(this.total / this.pageSize);
    },
  },
  methods: {
    handlePrevClick() {
      this.current = Math.max(1, this.current - 1);
      this.$emit('prevClick', this.current);
    },
    handleNextClick() {
      this.current = Math.min(this.pageNum, this.current + 1);
      this.$emit('nextClick', this.current);
    },
    handleCurrentChange(page) {
      const newPage = Math.max(1, Math.min(page, this.pageNum));
      if (newPage > 0) {
        this.current = newPage;
        this.$emit('currentChange', newPage);
      }
    },
    handlePageSizeChange(type) {
      let isOver = false;
      // 增加
      if (type !== 'minus') {
        this.pageSizes.forEach(size => {
          if (isOver === false && size > this.pageSize) {
            this.$emit('pageSizeChange', size);
            isOver = true;
          }
        });
      } else {
        this.pageSizes.forEach((size, index) => {
          if (isOver === false && size >= this.pageSize && this.pageSizes[index - 1]) {
            this.$emit('pageSizeChange', this.pageSizes[index - 1]);
            isOver = true;
          }
        });
      }

    },
  },
};
</script>

<style lang="scss">
  @import '../style/pagination.scss';
</style>
