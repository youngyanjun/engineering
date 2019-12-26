<template>
  <UI
    :listData="permissionTreeList"
    :listCurrentKey="currentPermission.id"
    :listCurrent="currentPermission"
    :listCurrentSelection="currentSelection"
    @listItemClick="handleListItemClick"
    @listSubitemClick="handleListSubitemClick"
    @listSelectionChange="handleListSelectionChange"
    @listSubmit="handleListSubmit"
    @listClear="handleListClear"
    :listExpansion="currentExpansion"
    @shrink="handleShrink"
    @expand="handleExpand"
    :breadcrumb="breadcrumb"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  props: {
    roleId: String,
    breadcrumb: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      currentRole: {
        parentId: '',
      },
      currentPermission: {
        parentId: '',
      },
      currentSelection: [],
      currentExpansion: [],
      roleIds: [],
    };
  },
  computed: {
    ...mapState('deploy', [
      'roleList',
      'permissionList',
    ]),
    permissionTreeList() {
      let result = [];
      const temp = [];
      this.roleIds = [];
      const tick = (arr) => {
        const tickChildren = (children, level) => {
          children.forEach(item => {
            item.label = item.menuName;
            item.level = level;
            item.isChecked = !!item.selection;
            item.parentId === '0' && (item.parentId = '');
            this.roleIds.push(item.id);
            if (item.child) {
              item.children = tickChildren(item.child, item.level + 1);
            } else {
              item.children = null;
            }
            if (item.selection) {
              temp.push(item.id);
            }
            delete item.child;
          });
          return children;
        };
        tickChildren(arr, 0);
        return arr;
      };
      result = tick(this.permissionList);
      this.currentSelection = temp;
      return result;
    },
  },
  methods: {
    ...mapActions('deploy', [
      'getRoleList',
      'getPermissionList',
      'updatePermissionList',
    ]),
    init() {
      this.getPermissionList(this.roleId);
    },
    handleListItemClick(data) {
      this.currentPermission = data;
    },
    handleListSubitemClick(data) {
      this.currentPermission = data;
    },
    handleListSelectionChange(data) {
      console.log('list => selectionChange', data);
      this.currentSelection = data;
    },
    handleListSubmit() {
      const list = this.currentSelection.map(item => ({
        menuId: item,
        roleId: this.roleId,
      }));
      this.updatePermissionList(list);
    },
    handleListClear() {
      this.currentSelection = [];
    },
    handleExpand() {
      this.currentExpansion = this.roleIds;
    },
    handleShrink() {
      this.currentExpansion = [];
    },
  },
};
</script>
