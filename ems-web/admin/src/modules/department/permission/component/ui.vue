<template>
  <div :class="$style.content">
    <div :class="$style.aside">
      <div :class="$style.tree">
        <div :class="$style['tree-hd']">
          <span :class="$style['tree-title']" @click.self="$emit('treeBlur')">角色名称</span>
          <Button :disabled="!addVisible" type="default" @click="$emit('add')">新增{{ isRoleGroup ? '角色组' : '角色' }}</Button>
        </div>
        <div :class="$style['tree-bd']">
          <Tree
            :currentKey="treeCurrentKey"
            :data="treeData"
            @itemClick="handleItemClick"
            @subitemClick="handleSubitemClick"
            @currentChange="$emit('currentChange', arguments[0])"
          >
            <Button
              :class="$style.edit"
              type="text"
              @click="handleEdit.call(this, scope)"
              slot-scope="scope"
            ><i :class="$style['edit-icon']" />编辑</Button>
          </Tree>
        </div>
      </div>
    </div>
    <div :class="$style.main">
      <div :class="$style['main-hd']">
        <span></span>
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
              :expanded="listExpanded"
              :data="listData"
              :currentKey="listCurrentKey"
              :currentSelection="listCurrentSelection"
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
  },
  data() {
    return {
      // treeData: [{
      //   id: 10,
      //   label: '角色组1',
      //   children: [{
      //     id: 11,
      //     label: '角色1',
      //   }, {
      //     id: 12,
      //     label: '角色2',
      //   }],
      // }, {
      //   id: 20,
      //   label: '角色组2',
      //   children: [{
      //     id: 21,
      //     label: '角色3',
      //   }, {
      //     id: 22,
      //     label: '角色4',
      //   }],
      // }],
    };
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
@import '../../component/tree.scss';
@import './ui.scss';
</style>
