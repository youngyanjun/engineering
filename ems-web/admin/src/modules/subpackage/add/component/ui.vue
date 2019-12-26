<template>
  <div class="dialog">
    <Dialog
      :title="isCreate ? '创建分包单位' : '编辑分包单位'"
      :value="visible"
      @input="$emit('close')"
    >
      <p class="dialog-group">
        <span class="dialog-field">企业名称</span><Input class="dialog-input" v-model="innerForm.unitName" /></p>
      <p class="dialog-group">
        <span class="dialog-field">企业法人</span><Input class="dialog-input" v-model="innerForm.juridicalPerson" /></p>
      <p class="dialog-group">
        <span class="dialog-field">纳税人识别号</span><Input class="dialog-input" v-model="innerForm.taxCode" /></p>
      <p class="dialog-group">
        <span class="dialog-field">地址</span><Input class="dialog-input" v-model="innerForm.address" /></p>
      <p class="dialog-group">
        <span class="dialog-field">联系人</span><Input class="dialog-input" v-model="innerForm.contact" /></p>
      <p class="dialog-group">
        <span class="dialog-field">电话</span><Input class="dialog-input" v-model="innerForm.phone" /></p>
      <p class="dialog-group">
        <span class="dialog-field">备注</span>
        <Input class="dialog-textarea" type="textarea" v-model="innerForm.remark" /></p>
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
