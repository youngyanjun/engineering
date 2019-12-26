<template>
  <div class="ls-table">
    <div class="ls-table-hd">
      <div class="ls-table-tr">
        <div class="ls-table-th ls-table-select" v-if="selected">
          <i
            :class="[
              'ls-table-checkbox', {
              'is-checked': selection.length === tbody.length
            }]"
            @click="handleSelectAll" />
        </div>
        <div
          :class="['ls-table-th', { 'is-center': item.align }]"
          v-for="item in thead"
          :style="{ 'max-width': item.width }"
        >{{ item.name }}</div>
      </div>
    </div>
    <div class="ls-table-bd">
      <template v-if="tbody.length">
        <div class="ls-table-tr" v-for="(item, index) in tbody">
          <div class="ls-table-td ls-table-select" v-if="selected">
            <i
              :class="[
                'ls-table-checkbox', {
                'is-checked': selection.includes(index)
              }]"
              @click="handleSelectSingle.call(this, index)"
            />
          </div>
          <div
            :class="['ls-table-td', { 'is-center': align }]"
            v-for="{ key, slot, align, width, filter } in thead"
            :style="{ 'max-width': width }"
          >
            <span v-if="!slot">{{ filter ? filter(item[key], item) : item[key] }}</span>
            <slot v-else :name="key" :data="item"></slot>
          </div>
        </div>
      </template>
      <template v-else>
        <div class="ls-table-empty">
          暂无数据~
        </div>
      </template>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LSTable',
  props: {
    thead: {
      type: Array,
      default: () => [],
    },
    tbody: {
      type: Array,
      default: () => [],
    },
    selected: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      selection: [],
    };
  },
  watch: {
    tbody() {
      this.selection = [];
    },
    selection(newVal) {
      this.$emit('selectionChange', newVal);
    },
  },
  methods: {
    handleSelectAll() {
      const { tbody } = this;
      if (this.selection.length !== tbody.length) {
        this.selection = tbody.map((item, index) => index);
      } else {
        this.selection = [];
      }
    },
    handleSelectSingle(index) {
      const { selection } = this;
      let isExist = false;
      selection.forEach((item, i) => {
        if (item === index) {
          isExist = true;
          selection.splice(i, 1);
        }
      });
      if (!isExist) {
        selection.push(index);
      }
    },
  },
};
</script>

<style lang="scss">
@import '../style/table.scss';
</style>
