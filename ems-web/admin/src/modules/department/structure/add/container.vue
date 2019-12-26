<template>
  <UI
    :visible="visible"
    :form="form"
    :isCreate="isCreate"
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
    };
  },
  methods: {
    ...mapActions('department', [
      'getDetail',
      'updateStructure',
      'insertStructure',
      'deleteStructure',
    ]),
    open(option, type = 'insert') {
      Object.keys(this.form).forEach(k => {
        this.form[k] = '';
      });
      this.id = 0;
      this.visible = true;
      if (type === 'insert' && typeof option === 'object') {
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
        this.deleteStructure(id).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('删除成功');
        });
      }
    },
    handleSubmit(data) {
      console.log('submit', data);
      if (!this.isCreate) {
        this.updateStructure(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('更新成功');
        });
      } else {
        this.insertStructure(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('添加成功');
        });
      }
    },
  },
};
</script>
