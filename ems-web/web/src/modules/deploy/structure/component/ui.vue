<template>
  <div :class="$style.content">
    <div :class="$style.aside">
      <div :class="$style.tree">
        <div :class="$style['tree-hd']">
          <span :class="$style['tree-title']">组织名称</span>
          <Button type="default" @click="$emit('add')">新增</Button>
        </div>
        <div :class="$style['tree-bd']">
          <Tree
            :currentKey="treeCurrentKey"
            :data="treeData"
            @itemClick="$emit('itemClick', arguments[0])"
            @subitemClick="$emit('subitemClick', arguments[0])"
          >
            <Button
              :class="$style.edit"
              type="text"
              @click="$emit('edit', scope.data)"
              slot-scope="scope"
            >编辑</Button>
          </Tree>
        </div>
      </div>
    </div>
    <div :class="$style.main">
      <slot />
    </div>
  </div>
</template>

<script>
import { Tree, Button, Divider } from '@/components/ls';

export default {
  name: '',
  components: { Button, Tree, Divider },
  props: {
    treeData: {
      type: Array,
      default: () => [],
    },
    treeCurrentKey: {
      type: [String, Number],
    },
    listData: {
      type: Array,
      default: () => [],
    },
    listCurrentKey: {
      type: [String, Number],
    },
    listCurrent: {
      type: Object,
      default: () => {},
    },
    listCurrentSelection: {
      type: Array,
      default: () => [],
    },
  },
};
</script>

<style lang="scss" module>
@import '../../component/tree.scss';
@import './ui.scss';
</style>
