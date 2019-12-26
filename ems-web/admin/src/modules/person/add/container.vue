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
    ...mapActions('person', [
      'getDetail',
      'updateDetail',
      'insertDetail',
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
  },
};
</script>
