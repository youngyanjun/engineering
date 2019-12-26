<template>
  <UI
    :visible="visible"
    :form="form"
    :isCreate="isCreate"
    :isGroup="isGroup"
    @close="handleClose"
    @delete="handleDelete"
    @submit="handleSubmit"
  >
    <slot />
  </UI>
</template>

<script>
import { mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  data() {
    return {
      visible: false,
      form: {},
      id: 0,
      isCreate: false,
      isGroup: false,
    };
  },
  methods: {
    ...mapActions('department', [
      'getDetail',
      'updateRole',
      'insertRole',
      'deleteRole',
    ]),
    open(option, action = 'insert', type = 'group') {
      Object.keys(this.form).forEach(k => {
        this.form[k] = '';
      });
      this.id = 0;
      this.visible = true;
      this.isGroup = type === 'group';
      if (action === 'insert' && typeof option === 'object') {
        this.form = Object.assign(this.form, option);
        this.isCreate = true;
      } else if (typeof option === 'object') {
        this.form = Object.assign(this.form, option);
        this.isCreate = false;
        this.id = option.id;
      }
    },
    close() {
      this.visible = false;
    },
    handleClose() {
      this.close();
    },
    handleDelete() {
      console.log('delete', this.id);
      const { id } = this;
      if (id) {
        this.deleteRole(id).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('删除成功');
        });
      }
    },
    handleSubmit(data) {
      console.log('submit', data);
      if (!this.isCreate) {
        this.updateRole(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('更新成功');
        });
      } else {
        this.insertRole(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('添加成功');
        });
      }
    },
  },
};
</script>
