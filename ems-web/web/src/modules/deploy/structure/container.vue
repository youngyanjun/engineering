<template>
  <div>
    <UI
      :treeData="roleTreeList"
      :treeCurrentKey="currentRole.id"
      @add="handleAdd"
      @itemClick="handleItemClick"
      @subitemClick="handleSubitemClick"
      @edit="handleEdit"
    >
      <PersonList
        ref="personList"
        :structureId="currentRole.id"
        :orgId="engineeringId"
        :isAll="!currentRole.parentId"
      />
    </UI>
    <Add ref="add" @refresh="handleRefresh" :engineeringId="engineeringId" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import Add from './add';
import PersonList from '@/modules/deploy_structure_person/list';

export default {
  name: '',
  components: { UI, Add, PersonList },
  props: {
    engineeringId: String,
  },
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
    ...mapState('deploy', [
      'structureList',
    ]),
    roleTreeList() {
      const tick = (arr) => {
        const tickChildren = function (arr, level) {
          arr.forEach(item => {
            item.label = item.structureName;
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
      setTimeout(() => {
        this.$refs.personList.init();
      });
    },
  },
  methods: {
    ...mapActions('deploy', [
      'getStructureList',
      'updatePermissionList',
    ]),
    init() {
      this.getStructureList(this.engineeringId).then(() => {
        this.currentRole = Object.assign({}, this.currentRole, {
          id: this.roleTreeList[0].id,
        });
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
        window.$Message.warning('组织分级不能超过3级');
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
