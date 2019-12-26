<template>
  <UI
    :visible="visible"
    :form="form"
    :isCreate="isCreate"
    :suggestions="suggestions"
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
      visible: false,
      form: {},
      id: 0,
      currentSuggestion: {},
    };
  },
  computed: {
    ...mapState('project', [
      'managerList',
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
    ...mapActions('organization', [
      'getDetail',
      'updateDetail',
      'insertDetail',
    ]),
    ...mapActions('project', [
      'getManagerList',
    ]),
    open(option) {
      Object.keys(this.form).forEach(k => {
        this.form[k] = '';
      });
      this.id = 0;
      this.visible = true;
      if (typeof option === 'string' || typeof option === 'number') {
        this.id = option;
        this.getDetail(option).then(data => {
          this.form = Object.assign(this.form, data);
        });
      } else if (typeof option === 'object') {
        this.id = option.id;
        this.form = Object.assign(this.form, option);
      }
      this.getManagerList();
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
      if (data.manager === this.currentSuggestion.userName) {
        data.managerId = this.currentSuggestion.id;
      }
      if (data.id) {
        this.updateDetail(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('更新成功');
        });
      } else {
        this.insertDetail(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('添加成功');
        });
      }
    },
    handleSuggestionChange(data) {
      this.currentSuggestion = data;
    },
  },
};
</script>
