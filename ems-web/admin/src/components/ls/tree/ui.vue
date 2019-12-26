<template>
  <div :class="['ls-tree', { 'is-expanded': expanded }]">
    <TreeItem
      :selected="selected"
      :children="innerTree"
      :currentKey="currentKey"
      :currentSelection="currentSelection"
      @itemClick="handleItemClick"
      @subitemClick="handleSubitemClick"
      @selectionChange="handleSelectionChange"
    >
      <slot slot-scope="scope" :data="scope.data" />
    </TreeItem>
  </div>
</template>

<script>
import TreeItem from './tree_item';

export default {
  name: 'LSTree',
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
    selected: {
      type: Boolean,
      default: false,
    },
    expanded: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      selection: [],
      innerTree: [],
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
      this.innerTree = [...this.toSelectionTree(val, [])];
    },
    currentSelection(val) {
      this.selection = val;
      this.innerTree = [...this.toSelectionTree(this.data, val)];
    },
    selection(val) {
      this.$emit('selectionChange', val);
    },
  },
  methods: {
    toSelectionTree(tree, selection) {
      console.log(tree, selection);
      const innerTree = JSON.parse(JSON.stringify(tree));
      const tick = (arr, parent = null) => {
        const tickChildren = function (arr, parent) {
          let isSubchecked = false;
          let isSubUnchecked = false;
          (arr || []).forEach(item => {
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
            while(tempParent) {
              tempParent.isSubchecked = true;
              tempParent = tempParent.parent;
            }
          }
          return arr;
        };
        tickChildren(arr, null);
        return arr;
      };
      const result = tick(innerTree);
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
      data.forEach((id) => {
        console.log(id, checked);
        this.updateSelection(id, checked);
      });
      console.log('selectionChange => done', this.selection);
    },
    updateSelection(id, checked) {
      const { selection } = this;
      const temp = [];
      selection.forEach((item, i) => {
        if (item !== id) {
          temp.push(item);
        }
      });
      if (checked === true) {
        temp.push(id);
      }
      this.selection = temp;
    },
  },
};
</script>

<style lang="scss">
@import '../style/tree.scss';
</style>
