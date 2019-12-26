<template>
  <UI
    :projects="projects"
    :singleBodys="singleBodys"
    :currentProjectId="currentProjectId"
    @searchSubmit="handleSearchSubmit"
    @projectChange="handleProjectChange"
    @remove="handleRemove"
    @set="handleSet"
    @checkedChange="handleCheckedChange"
    @orderSet="handleOrderSet"
    @add="handleAdd"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  data() {
    return {
      projects: [],
      singleBodys: [],
      currentProjectId: '',
    };
  },
  created() {
    this.init();
  },
  methods: {
    ...mapActions('reportSummary', [
      'getProjects',
      'getSingleBodys',
      'setSingleBody',
      'addSingleBody',
      'removeSingleBodys',
      'setSingleBodysOrder',
    ]),
    init(keyword = '') {
      this.getProjects(keyword).then(data => {
        this.projects = (data || []).map(item => ({
          id: item.id,
          name: item.engineeringName,
        }));
        // 默认选中第一个项目
        if (this.projects[0]) {
          this.currentProjectId = this.projects[0].id;
        }
      }).then(() => {
        this.refresh();
      });
    },
    refresh() {
      this.getSingleBodys(this.currentProjectId).then(data => {
        this.singleBodys = (data || []).map(item => ({
          id: item.id,
          label: item.singleName,
          checked: item.mandatory,
        }));
      });
    },
    /**
     * 查询项目列表
     */
    handleSearchSubmit(data) {
      console.log('singlebody => searchSubmit', JSON.stringify(data));
      this.init(data);
    },
    /**
     * 选中的项目发生改变
     */
    handleProjectChange(data) {
      console.log('singlebody => projectChange', JSON.stringify(data));
      this.currentProjectId = data.id;
      this.refresh();
    },
    handleRemove(data) {
      console.log('singlebody => remove', JSON.stringify(data));
      this.removeSingleBodys([data.id]).then(() => {
        this.refresh();
      });
    },
    handleCheckedChange(data) {
      console.log('singlebody => checkedChange', JSON.stringify(data));
      this.setSingleBody({
        id: data.id,
        singleName: data.label,
        mandatory: data.checked,
        engineeringId: this.currentProjectId,
      }).then(() => {
        this.refresh();
      });
    },
    handleSet(data) {
      console.log('singlebody => set', JSON.stringify(data));
      this.setSingleBody({
        id: data.id,
        singleName: data.label,
        mandatory: data.checked,
        engineeringId: this.currentProjectId,
      }).then(() => {
        this.refresh();
      });
    },
    handleOrderSet(data) {
      console.log('singlebody => orderSet', JSON.stringify(data));
      this.setSingleBodysOrder(data.map(item => item.id)).then(() => {
        this.refresh();
      });
    },
    handleAdd(data) {
      console.log('singlebody => add', JSON.stringify(data));
      this.addSingleBody({
        singleName: data.label,
        mandatory: data.checked,
        engineeringId: this.currentProjectId,
      }).then(() => {
        this.refresh();
      });
    },
  },
};
</script>
