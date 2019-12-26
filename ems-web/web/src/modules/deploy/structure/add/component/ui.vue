<template>
  <div class="dialog">
    <Dialog
      :title="isCreate ? '创建组织' : '编辑组织'"
      :value="visible"
      @input="$emit('close')"
      @submit="$emit('submit', innerForm)"
    >
      <div class="dialog-group">
        <span class="dialog-field">组织名称</span><Input class="dialog-input" v-model="innerForm.structureName" />
      </div>
      <div class="dialog-group">
        <span class="dialog-field">备注</span><Input class="dialog-textarea" type="textarea" v-model="innerForm.remark" />
      </div>
      <div class="dialog-ft" slot="ft">
        <Button type="text" @click="$emit('delete')" v-show="!isCreate && deleteVisible"><i class="icon-delete" />&nbsp;删除组织</Button>
        <Button class="dialog-cancel" @click="$emit('close')">取消</Button>
        <Button class="dialog-submit" type="default" @click="$emit('submit', innerForm)">确认</Button>
      </div>
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
    deleteVisible: {
      type: Boolean,
      default: true,
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
