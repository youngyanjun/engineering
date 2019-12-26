<template>
  <div :class="[
    isSub ? 'ls-tree-subitem' : 'ls-tree-item',
    `${source ? 'is-level-' + source.level : ''}`,
    { 'is-active': isActive },
    { 'is-parent': children && children.length > 0 },
  ]">
    <div
      class="ls-tree-label"
      v-if="label"
      @click="isSub ? $emit('subitemClick', source) : $emit('itemClick', source)"
      :style="{ 'padding-left': source.level * 32 + 'px' }"
    >
      <i class="ls-tree-prefix" />
      <i
        :class="[
          'ls-tree-checkbox', {
          'is-checked': isChecked,
          'is-subchecked': isSubchecked,
        }]"
        @click="handleSelectionChange"
        v-show="selected"
      />
      <span>{{ label }}</span>
      <span>
        <slot :data="source" />
      </span>
    </div>
    <LsTreeItem
      :isSub="!!item.parentId"
      :id="item.id"
      :label="item.label"
      :children="item.children"
      :isActive="item.id === currentKey"
      :currentKey="currentKey"
      :currentSelection="currentSelection"
      :source="item"
      :selected="selected"
      :isChecked="item.isChecked"
      :isSubchecked="item.isSubchecked"
      v-for="item in children"
      :key="item.id"
      @selectionChange="$emit('selectionChange', arguments[0])"
      @subitemClick="$emit('subitemClick', arguments[0])"
      @itemClick="$emit('itemClick', arguments[0])"
    >
      <slot slot-scope="scope" :data="scope.data" />
    </LsTreeItem>
  </div>
</template>

<script>

export default {
  name: 'LsTreeItem',
  props: {
    id: {
      type: [String, Number],
    },
    label: {
      type: String,
      default: '',
    },
    children: {
      type: Array,
      default: () => [],
    },
    isSub: {
      type: Boolean,
      default: true,
    },
    isChecked: {
      type: Boolean,
      default: false,
    },
    isActive: {
      type: Boolean,
      default: false,
    },
    isSubchecked: {
      type: Boolean,
      default: false,
    },
    selected: {
      type: Boolean,
      default: false,
    },
    currentKey: {
      type: [String, Number],
    },
    currentSelection: {
      type: Array,
      default: () => [],
    },
    source: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {

    };
  },
  methods: {
    handleSelectionChange() {
      const tick = (arr, parent = null) => {
        const childrens = [];
        const tickChildren = function (arr, parent) {
          (arr || []).forEach(item => {
            childrens.push(item.id);
            tickChildren(item.children, item);
          });
          return arr;
        };
        tickChildren(arr, null);
        return childrens;
      };
      const checkedChildren = tick([this.source]);
      let tempParent = this.source.parent;
      while (tempParent) {
        if (this.isChecked === false // 选中时当前节点时选中父节点
          || (this.isChecked === true && !tempParent.children.find(child => child.id !== this.source.id && child.isChecked === true)) // 全部取消时取消父节点
        ) {
          checkedChildren.push(tempParent.id);
        }
        tempParent = tempParent.parent;
      }
      this.$emit('selectionChange', {
        data: checkedChildren,
        checked: !this.isChecked,
      });
    },
  },
};
</script>
