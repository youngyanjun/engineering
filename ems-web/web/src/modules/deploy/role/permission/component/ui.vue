<template>
  <div :class="$style.content">
    <div :class="$style.main">
      <div :class="$style['main-hd']">
        <span :class="$style.breadcrumb">
          {{ breadcrumb.join(' > ')}}
        </span>
        <span :class="$style.btns">
          <Button type="plain" @click="$emit('listClear')">清空</Button>
          <Button type="default" @click="$emit('listSubmit')">确定</Button>
        </span>
      </div>
      <div :class="$style['main-bd']">
        <div :class="$style['list']">
          <div :class="$style['list-hd']">
            <span :class="$style['list-title']">权限列表</span>
            <Button type="text" @click="$emit('shrink')">收缩</Button>
            <Divider direction="vertical" />
            <Button type="text" @click="$emit('expand')">展开</Button>
          </div>
          <div :class="$style['list-bd']">
            <Tree
              :selected="true"
              :expanded="true"
              :data="listData"
              :currentKey="listCurrentKey"
              :currentSelection="listCurrentSelection"
              :currentExpansion="listExpansion"
              @itemClick="handleListItemClick"
              @subitemClick="handleListSubitemClick"
              @selectionChange="handleListSelectionChange"
            />
          </div>
        </div>
        <div :class="$style['abstract']">
          <div :class="$style['abstract-hd']">权限描述</div>
          <div :class="$style['abstract-bd']">
            <div :class="$style['abstract-group']">
              <span :class="$style['abstract-label']">权限名称：</span><span>{{ listCurrent.menuName }}</span>
            </div>
            <div :class="$style['abstract-group']">
              <span :class="$style['abstract-label']">相关页面：</span><span>{{ listCurrent.relatePages}}</span>
            </div>
            <div :class="$style['abstract-group']">
              <span :class="$style['abstract-label']">功能释意：</span><span>{{ listCurrent.remark }}</span>
            </div>
          </div>
        </div>
      </div>
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
    isRoleGroup: {
      type: Boolean,
      default: false,
    },
    addVisible: {
      type: Boolean,
      default: false,
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
    listExpanded: {
      type: Boolean,
      default: false,
    },
    listExpansion: {
      type: Array,
      default: () => [],
    },
    breadcrumb: {
      type: Array,
      default: () => [],
    },
  },
  methods: {
    handleItemClick(data) {
      this.$emit('itemClick', data);
    },
    handleSubitemClick(data) {
      this.$emit('subitemClick', data);
    },
    handleEdit({ data }) {
      this.$emit('edit', data);
    },
    handleListItemClick(data) {
      this.$emit('listItemClick', data);
    },
    handleListSubitemClick(data) {
      this.$emit('listSubitemClick', data);
    },
    handleListSelectionChange(arg) {
      this.$emit('listSelectionChange', arg);
    },
  },
};
</script>

<style lang="scss" module>
@import './tree.scss';
@import './ui.scss';
</style>
