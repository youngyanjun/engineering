<template>
  <UI
    :visible="visible"
    :list="list"
    :form="form"
    @close="handleClose"
    @add="handleAdd"
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
      list: [],
      id: 0,
      form: {},
    };
  },
  methods: {
    ...mapActions('field', [
      'updateDetail',
    ]),
    open(data) {
      this.list = [];
      this.id = 0;
      this.form = {};
      try {
        this.list = JSON.parse(decodeURIComponent(data.dictionaryChild)) || [];
      } catch (err) {
        this.list = [];
      }
      this.id = data.id;
      this.form = Object.assign(this.form, data);
      this.visible = true;
    },
    close() {
      this.visible = false;
    },
    handleClose() {
      this.close();
    },
    handleAdd() {
      this.list.push('');
    },
    handleDelete(index) {
      this.list.splice(index, 1);
    },
    handleSubmit(data) {
      console.log('submit', data);
      this.updateDetail({
        ...data.form,
        dictionaryChild: encodeURIComponent(JSON.stringify(data.list)),
      }).then(() => {
        this.$emit('refresh');
        this.close();
      });
    },
  },
};
</script>
