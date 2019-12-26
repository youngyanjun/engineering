<template>
  <div :class="$style.content">
    <div :class="$style.aside">
      <div :class="$style.tree">
        <div :class="$style['tree-hd']">
          <span :class="$style['tree-title']" @click.self="$emit('treeBlur')">角色名称</span>
          <Button
            :disabled="!addVisible"
            type="default"
            @click="$emit('add')"
          >新增{{ isRoleGroup ? '角色组' : '角色' }}</Button>
        </div>
        <div :class="$style['tree-bd']">
          <Tree
            :currentKey="treeCurrentKey"
            :data="treeData"
            @itemClick="$emit('itemClick', arguments[0])"
            @subitemClick="$emit('subitemClick', arguments[0])"
            @currentChange="$emit('currentChange', arguments[0])"
          >
            <Button
              :class="$style.edit"
              type="text"
              @click="$emit('edit', scope.data)"
              slot-scope="scope"
            ><i :class="$style['edit-icon']" />编辑</Button>
          </Tree>
        </div>
      </div>
    </div>
    <div :class="$style.main">
      <Tab v-model="currentTab">
        <TabItem label="成员列表" value="1">
          <slot name="person" />
        </TabItem>
        <TabItem label="权限设置" value="2" v-if="permissionVisible">
          <slot name="permission" />
        </TabItem>
      </Tab>
    </div>
  </div>
</template>

<script>
import { Tree, Button, Divider, Tab, TabItem } from '@/components/ls';

export default {
  name: '',
  components: { Button, Tree, Divider, Tab, TabItem },
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
    permissionVisible: {
      type: Boolean,
      default: false,
    },
  },
  watch: {
    permissionVisible(val) {
      if (val === false) {
        this.currentTab = '1';
      }
    },
  },
  data() {
    return {
      currentTab: '1',
    };
  },
};
</script>

<style lang="scss" module>
@import '../../component/tree.scss';
@import './ui.scss';
</style>
