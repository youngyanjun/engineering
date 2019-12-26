<template>
  <div class="dialog">
    <Dialog
      :title="isCreate ? '创建用户信息' : '编辑用户信息'"
      :value="visible"
      @input="$emit('close')"
      @submit="$emit('submit', innerForm)"
    >
      <p class="dialog-group">
        <span class="dialog-field">姓名</span><Input class="dialog-input" v-model="innerForm.userName" /></p>
      <p class="dialog-group">
        <span class="dialog-field">邮箱</span><Input class="dialog-input" v-model="innerForm.email" /></p>
      <p class="dialog-group">
        <span class="dialog-field">手机</span><Input class="dialog-input" v-model="innerForm.phone" :readonly="isCreate ? false : true" />
      </p>
      <!-- <p class="dialog-ft" slot="ft">
        <Button type="text" @click="$emit('delete')" v-show="!isCreate"><i class="icon-delete" />&nbsp;删除项目</Button>
        <Button class="dialog-cancel" @click="$emit('close')">取消</Button>
        <Button class="dialog-submit" type="default" @click="$emit('submit', innerForm)">确认</Button>
      </p> -->
    </Dialog>
  </div>
</template>

<script>
import { Dialog, Input, Button } from '@/components/ls';

export default {
  name: '',
  components: { Dialog, Input, Button },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    form: {
      type: Object,
      default: () => {},
    },
    isCreate: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      innerForm: {},
    };
  },
  created() {
    this.innerForm = this.form;
  },
  watch: {
    form(newVal) {
      this.innerForm = Object.assign(this.innerForm, newVal);
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
