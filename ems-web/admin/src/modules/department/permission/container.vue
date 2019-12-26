<template>
  <div>
    <UI
      :isRoleGroup="isRoleGroup"
      :addVisible="addVisible"
      :treeData="roleTreeList"
      :treeCurrentKey="currentRole.id"
      :listData="permissionTreeList"
      :listCurrentKey="currentPermission.id"
      :listCurrent="currentPermission"
      :listCurrentSelection="currentSelection"
      @add="handleAdd"
      @itemClick="handleItemClick"
      @subitemClick="handleSubitemClick"
      @treeBlur="handleTreeBlur"
      @edit="handleEdit"
      @listItemClick="handleListItemClick"
      @listSubitemClick="handleListSubitemClick"
      @listSelectionChange="handleListSelectionChange"
      @listSubmit="handleListSubmit"
      @listClear="handleListClear"
      :listExpanded="expanded"
      @shrink="handleShrink"
      @expand="handleExpand"
    />
    <Add ref="add" @refresh="handleRefresh" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import Add from './add';

export default {
  name: '',
  components: { UI, Add },
  data() {
    return {
      currentRole: {
        parentId: '',
      },
      currentPermission: {
        parentId: '',
      },
      currentSelection: [],
      expanded: true,
    };
  },
  computed: {
    ...mapState('department', [
      'roleList',
      'permissionList',
    ]),
    roleTreeList() {
      const tick = (arr) => {
        const tickChildren = function (arr, level) {
          arr.forEach(item => {
            item.label = item.roleName;
            item.level = level;
            item.parentId === '0' && (item.parentId = '');
            if (item.child) {
              item.children = tickChildren(item.child, item.level + 1);
            } else {
              item.children = null;
            }
            delete item.child;
          });
          return arr;
        };
        tickChildren(arr, 0);
        return arr;
      };
      return tick(this.roleList);
    },
    permissionTreeList() {
      let result = [];
      const temp = [];
      const tick = function (arr) {
        const tickChildren = function (arr, level) {
          arr.forEach(item => {
            item.label = item.menuName;
            item.level = level;
            item.isChecked = !!item.selection;
            item.parentId === '0' && (item.parentId = '');
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
          return arr;
        };
        tickChildren(arr, 0);
        return arr;
      };
      result = tick(this.permissionList);
      this.currentSelection = temp;
      return result;
    },
    isRoleGroup() {
      return typeof this.currentRole.parentId === 'undefined';
    },
    addVisible() {
      return !this.currentRole.parentId;
    },
  },
  created() {
    this.init();
  },
  watch: {
    currentRole(val) {
      console.log('currentRole', val);
      if (val.id) {
        this.getPermissionList(val.id);
      }
    },
  },
  methods: {
    ...mapActions('department', [
      'getRoleList',
      'getPermissionList',
      'updatePermissionList',
    ]),
    init() {
      this.getRoleList();
    },
    handleItemClick(data) {
      this.currentRole = data;
    },
    handleSubitemClick(data) {
      this.currentRole = data;
    },
    handleTreeBlur() {
      this.currentRole = {};
    },
    handleRefresh() {
      this.init();
    },
    handleEdit(data) {
      console.log('edit', data);
      this.currentRole = data;
      const type = !this.currentRole.parentId ? 'group' : '';
      this.$refs.add.open({
        ...this.currentRole,
        name: this.currentRole.roleName,
        remark: decodeURIComponent(this.currentRole.remark),
      }, 'update', type);
    },
    handleAdd() {
      console.log('insert', this.currentRole);
      if (this.currentRole.level >= 1) {
        window.$Message.warning('无法再添加了');
        return;
      }
      const type = !this.currentRole.id ? 'group' : '';
      this.$refs.add.open({
        parentId: this.currentRole.id || '',
        name: '',
        remark: '',
      }, 'insert', type);
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
        roleId: this.currentRole.id,
      }));
      this.updatePermissionList(list);
    },
    handleListClear() {
      this.currentSelection = [];
    },
    handleExpand() {
      this.expanded = true;
    },
    handleShrink() {
      this.expanded = false;
    },
  },
};
</script>
