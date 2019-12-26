<template>
  <UI
    :visible="visible"
    :form="form"
    :error="error"
    :isCreate="isCreate"
    :subpackageList="subpackageList"
    :managerSuggestions="suggestions"
    :ranges="ranges"
    @close="handleClose"
    @delete="handleDelete"
    @submit="handleSubmit"
    @suggestionChange="handleSuggestionChange"
  >
    <slot />
  </UI>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  data() {
    return {
      visible: false, // 测试
      form: {
        rangeListStatus: {},
        rangeListIds: [],
        subcontractList: [],
      },
      error: {
        managerName: {
          msg: '',
          verify: [{
            msg: '不能为空',
            test: v => v === '' || v === undefined,
          }],
        },
      },
      id: 0,
      currentSuggestion: {},
    };
  },
  computed: {
    ...mapState('project', [
      'subpackageList',
      'managerList',
      'ranges',
    ]),
    isCreate() {
      return !this.id;
    },
    suggestions() {
      return this.managerList.map(item => ({
        ...item,
        value: item.userName,
      }));
    },
  },
  methods: {
    ...mapActions('project', [
      'getDetail',
      'updateDetail',
      'insertDetail',
      'getManagerList',
      'getSubpackageList',
    ]),
    open(option) {
      Object.keys(this.form).forEach(k => {
        this.form[k] = '';
      });
      const rangeListStatus = {};
      Array.from({ length: 8 }).forEach((k, i) => {
        rangeListStatus[i + 1] = '1';
      });
      this.form = Object.assign(this.form, {
        rangeListStatus,
        rangeListIds: [],
        subcontractList: [],
      });
      this.FormVerifyClear();
      this.id = 0;
      this.visible = true;
      if (typeof option === 'object') {
        this.getDetail(option.id).then(data => {
          const status = {};
          data.rangeList.forEach(item => {
            status[item.ranageType] = item.engineeringStage;
          });
          this.form = Object.assign(this.form, {
            ...option,
            ...data,
            engineeringName: option.name,
            rangeListIds: data.rangeList.map(item => item.ranageType),
            rangeListStatus: Object.assign({}, rangeListStatus, status),
          });
          console.log('add', this.form);
        });
        this.id = option.id;
      }
      this.getManagerList();
      this.getSubpackageList();
    },
    close() {
      this.visible = false;
    },
    handleClose() {
      this.close();
    },
    handleDelete() {
      console.log('delete', this.id);
    },
    handleSubmit(data) {
      console.log('submit', data);
      if (this.FormVerify(data) !== true) {
        return;
      }

      let rangeList;

      data.flag = '';
      if (data.managerName === this.currentSuggestion.userName) {
        data.managerId = this.currentSuggestion.id;
      }
      rangeList = data.rangeListIds.map(item => {
        if (item === '8') {
          data.flag = '8';
        }
        return {
          engineeringId: data.id || data.engineeringCode,
          engineeringStage: data.rangeListStatus[item],
          ranageType: item,
        };
      });
      data.rangeList = rangeList;
      data.subcontractList = data.subcontractList.map(item => ({
        ...item,
        engineeringId: data.id || data.engineeringCode,
      }));
      if (data.id) {
        this.updateDetail(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('提交成功');
        });
      } else {
        this.insertDetail(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('提交成功');
        });
      }
    },
    handleSuggestionChange(data) {
      this.currentSuggestion = data;
    },
  },
};
</script>
