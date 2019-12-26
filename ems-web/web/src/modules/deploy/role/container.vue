<template>
  <div>
    <UI
      :isRoleGroup="isRoleGroup"
      :addVisible="addVisible"
      :permissionVisible="permissionVisible"
      :treeData="roleTreeList"
      :treeCurrentKey="currentRole.id"
      @add="handleAdd"
      @itemClick="handleItemClick"
      @subitemClick="handleSubitemClick"
      @treeBlur="handleTreeBlur"
      @edit="handleEdit"
    >
      <PersonList
        slot="person"
        ref="personList"
        :orgId="engineeringId"
        :roleId="currentRole.id"
      />
      <Permission
        slot="permission"
        ref="permission"
        :orgId="engineeringId"
        :roleId="currentRole.id"
        :breadcrumb="breadcrumb"
      />
    </UI>
    <Add ref="add" @refresh="handleRefresh" :orgId="engineeringId" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import Add from './add';
import Permission from './permission';
import PersonList from '@/modules/deploy_role_person/list';

export default {
  name: '',
  components: { UI, Add, PersonList, Permission },
  props: {
    engineeringId: String,
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
      expanded: true,
    };
  },
  computed: {
    ...mapState('deploy', [
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
    isRoleGroup() {
      return typeof this.currentRole.parentId === 'undefined';
    },
    addVisible() {
      return !this.currentRole.parentId;
    },
    permissionVisible() {
      return !!this.currentRole.parentId;
    },
    breadcrumb() {
      const result = [];
      let node = this.currentRole;
      while (node) {
        result.unshift(node.label);
        node = node.parent;
      }
      console.log('breadcrumb', result, this.currentRole);
      return result;
    },
  },
  created() {
    this.init();
  },
  watch: {
    currentRole(val) {
      console.log('currentRole', val);
      this.$nextTick(() => {
        this.$refs.personList.init();
        if (this.$refs.permission) {
          this.$refs.permission.init();
        }
      });
    },
  },
  methods: {
    ...mapActions('deploy', [
      'getRoleList',
    ]),
    init() {
      this.getRoleList(this.engineeringId);
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
        orgId: this.engineeringId,
        parentId: this.currentRole.id || '',
        remark: '',
      }, 'insert', type);
    },
  },
};
</script>
