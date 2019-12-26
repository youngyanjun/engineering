<template>
  <div :class="['ls-tree']">
    <TreeItem
      :isSub="false"
      :selected="selected"
      :children="innerTree"
      :currentKey="currentKey"
      :currentSelection="selection"
      :currentExpansion="expansion"
      @itemClick="handleItemClick"
      @subitemClick="handleSubitemClick"
      @selectionChange="handleSelectionChange"
      @expansionChange="handleExpansionChange"
    >
      <slot slot-scope="scope" :data="scope.data" />
    </TreeItem>
  </div>
</template>

<script>
import TreeItem from './tree_item';

/**
 * 并集
 */
const unionSet = (arr1, arr2) => {
  return [...new Set([...arr1, ...arr2])];
};

/**
 * 差集
 */
const differenceSet = (arr1, arr2) => {
  return [...new Set([...arr1].filter(item => !arr2.includes(item)))];
};

export default {
  name: 'LsTree',
  components: { TreeItem },
  props: {
    data: {
      type: Array,
      default: () => [],
    },
    currentKey: {
      type: [String, Number],
    },
    currentSelection: {
      type: Array,
      default: () => [],
    },
    currentExpansion: { // 当前展开的
      type: Array,
      default: () => [],
    },
    selected: {
      type: Boolean,
      default: false,
    },
    // 默认展开
    expanded: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      innerTree: [],
      selection: [],
      expansion: [],
      defaultExpandedDone: false,
    };
  },
  created() {
    this.selection = this.currentSelection;
    this.innerTree = [...this.toSelectionTree(this.data, this.selection)];
  },
  watch: {
    data(val) {
      console.log('tree', val);
      this.selection = [];
      this.defaultExpandedDone = false;
      this.innerTree = [...this.toSelectionTree(val, [])];
    },
    currentSelection(val) {
      this.selection = val;
      this.innerTree = [...this.toSelectionTree(this.data, val)];
    },
    currentExpansion(val) {
      this.expansion = val;
    },
    selection(val) {
      this.$emit('selectionChange', val);
    },
    expansion(val) {
      this.$emit('expansionChange', val);
    },
  },
  methods: {
    toSelectionTree(tree, selection) {
      console.log(tree, selection);
      if (this.expanded && !this.defaultExpandedDone) {
        this.expansion = [];
      }
      const innerTree = JSON.parse(JSON.stringify(tree));
      const tick = (arr) => {
        const tickChildren = (children, parent) => {
          let isSubchecked = false;
          let isSubUnchecked = false;
          (children || []).forEach(item => {
            if (this.expanded && !this.defaultExpandedDone) {
              this.expansion.push(item.id);
            }
            item.parent = parent;
            if (selection.includes(item.id)) {
              item.isChecked = true;
              isSubchecked = true;
            } else {
              item.isChecked = false;
              isSubUnchecked = true;
            }
            tickChildren(item.children, item);
          });
          if (isSubchecked === true && isSubUnchecked === true) {
            let tempParent = parent;
            while (tempParent) {
              tempParent.isSubchecked = true;
              tempParent = tempParent.parent;
            }
          }
          return children;
        };
        tickChildren(arr, null);
        console.log('expansion', this.expansion);
        return arr;
      };
      const result = tick(innerTree);
      this.defaultExpandedDone = true;
      console.log('innerTree', result);
      return result;
    },
    handleCheck(arg) {
      console.log('check', arg);
      this.$emit('check', arg);
    },
    handleItemClick(arg) {
      console.log('itemClick', arg);
      this.$emit('itemClick', arg);
    },
    handleSubitemClick(arg) {
      console.log('subitemClick', arg);
      this.$emit('subitemClick', arg);
    },
    handleSelectionChange({ data, checked }) {
      console.log('selectionChange', data);
      if (checked === true) {
        this.selection = unionSet(this.selection, data);
      } else {
        this.selection = differenceSet(this.selection, data);
      }
      console.log('selectionChange => done', this.selection);
    },
    handleExpansionChange({ data, expanded }) {
      console.log('expansionChange', data);
      if (expanded === true) {
        this.expansion = unionSet(this.expansion, data);
      } else {
        this.expansion = differenceSet(this.expansion, data);
      }
    },
  },
};
</script>

<style lang="scss">
@import '../style/tree.scss';
</style>
