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
    };
  },
  computed: {
    isCreate() {
      return !this.id;
    },
  },
  methods: {
    ...mapActions('subpackage', [
      'getRecord',
      'setRecord',
      'addRecord',
      'removeRecord',
    ]),
    open(id) {
      this.visible = true;
      if (id) {
        this.id = id;
        this.getRecord(id).then(data => {
          this.form = Object.assign(this.form, data);
        });
      }
    },
    close() {
      this.visible = false;
      Object.keys(this.form).forEach(k => {
        this.form[k] = '';
      });
      this.id = 0;
    },
    handleClose() {
      this.close();
    },
    handleDelete() {
      console.log('delete', this.id);
      this.removeRecord(this.id).then(() => {
        this.$emit('refresh');
        this.close();
        window.$Message.success('删除成功');
      });
    },
    handleSubmit(data) {
      console.log('submit', data);
      if (data.id) {
        this.setRecord(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('提交成功');
        });
      } else {
        this.addRecord(data).then(() => {
          this.$emit('refresh');
          this.close();
          window.$Message.success('提交成功');
        });
      }
    },
  },
};
</script>
