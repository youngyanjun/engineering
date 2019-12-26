<template>
  <div>
    <UI
      :treeData="roleTreeList"
      :treeCurrentKey="currentRole.id"
      @add="handleAdd"
      @itemClick="handleItemClick"
      @subitemClick="handleSubitemClick"
      @edit="handleEdit"
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
        id: '',
      },
      currentPermission: {
        parentId: '',
      },
      currentSelection: [],
    };
  },
  computed: {
    ...mapState('department', [
      'structureList',
    ]),
    roleTreeList() {
      const tick = (arr) => {
        const tickChildren = function (arr, level) {
          arr.forEach(item => {
            item.label = item.name;
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
      return tick(this.structureList);
    },
  },
  created() {
    this.init();
  },
  watch: {
    currentRole(newVal) {
      console.log('currentRole', newVal);
      this.getPermissionList(newVal.id);
    },
  },
  methods: {
    ...mapActions('department', [
      'getStructureList',
      'getPermissionList',
      'updatePermissionList',
    ]),
    init() {
      this.getStructureList().then(() => {
        this.currentRole.id = this.roleTreeList[0].id;
      });
    },
    handleItemClick(data) {
      this.currentRole = data;
    },
    handleSubitemClick(data) {
      this.currentRole = data;
    },
    handleRefresh() {
      this.init();
    },
    handleEdit(data) {
      this.currentRole = data;
      this.$refs.add.open({
        ...this.currentRole,
        name: this.currentRole.name,
        remark: decodeURIComponent(this.currentRole.remark),
      }, 'update');
    },
    handleAdd() {
      console.log('insert', this.currentRole);
      if (this.currentRole.level >= 2) {
        window.$Message.warning('无法再添加了');
        return;
      }
      this.$refs.add.open({
        parentId: this.currentRole.id || '',
        name: '',
        remark: '',
      }, 'insert');
    },
  },
};
</script>
