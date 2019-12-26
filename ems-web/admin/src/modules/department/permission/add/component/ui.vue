<template>
  <div class="dialog">
    <Dialog
      :title="isCreate ? `创建${titleLabel}` : `编辑${titleLabel}`"
      :value="visible"
      @input="$emit('close')"
      @submit="$emit('submit', innerForm)"
    >
      <p class="dialog-group">
        <span class="dialog-field">{{ titleLabel }}名称</span><Input class="dialog-input" v-model="innerForm.name" /></p>
      <p class="dialog-group">
        <span class="dialog-field">备注</span><Input class="dialog-textarea" type="textarea" v-model="innerForm.remark" />
      </p>
      <p class="dialog-ft" slot="ft">
        <Button type="text" @click="$emit('delete')" v-show="!isCreate"><i class="icon-delete" />&nbsp;删除项目</Button>
        <Button class="dialog-cancel" @click="$emit('close')">取消</Button>
        <Button class="dialog-submit" type="default" @click="$emit('submit', innerForm)">确认</Button>
      </p>
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
    isGroup: {
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
  computed: {
    titleLabel() {
      return this.isGroup ? '角色组' : '角色';
    },
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
