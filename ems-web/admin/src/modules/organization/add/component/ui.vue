<template>
  <div class="dialog">
    <Dialog
      :title="isCreate ? '创建组织' : '编辑组织'"
      :value="visible"
      @input="$emit('close')"
      @submit="$emit('submit', innerForm)"
    >
      <p class="dialog-group">
        <span class="dialog-field">组织名称</span><Input class="dialog-input" v-model="innerForm.name" /></p>
      <p class="dialog-group">
        <span class="dialog-field">组织类别</span><Input class="dialog-input" v-model="innerForm.type" readonly /></p>
      <p class="dialog-group">
        <span class="dialog-field">管理员</span>
        <Input
          class="dialog-input"
          v-model="innerForm.manager"
          :suggestions="suggestions"
          @suggestionClick="$emit('suggestionChange', arguments[0])"
        />
      </p>
      <p class="dialog-group">
        <span class="dialog-field">备注</span>
        <Input class="dialog-textarea" type="textarea" v-model="innerForm.remark" /></p>
    </Dialog>
  </div>
</template>

<script>
import { Dialog, Input, Select, Option, Button } from '@/components/ls';

export default {
  name: '',
  components: { Dialog, Input, Button, Select, Option },
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
    suggestions: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      innerForm: {
        remark: '',
      },
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
