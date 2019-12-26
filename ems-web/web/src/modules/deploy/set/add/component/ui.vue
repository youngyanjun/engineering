<template>
  <div :class="$style.content">
    <Dialog
      class="dialog"
      title="编辑项目"
      :value="visible"
      @submit="$emit('submit', innerForm)"
      @input="$emit('cancel')"
    >
      <div class="dialog-group">
        <label class="dialog-field">项目名称</label>
        <span class="dialog-value">{{ innerForm.name }}</span>
      </div>
      <div class="dialog-group">
        <label class="dialog-field">项目状态</label>
        <span class="dialog-input">
          <Radio
            :label="option.value"
            :key="option.value"
            v-for="option in statusOptions"
            v-model="innerForm.status"
          >&nbsp;{{ option.label }}</Radio>
        </span>
      </div>
      <div class="dialog-group">
        <label class="dialog-field">责任人</label>
        <Select class="dialog-input" v-model="innerForm.leaderId">
          <Option v-for="user in leaderOptions" :key="user.id" :value="user.id" :label="user.name">
            <span>{{ user.name }}</span><span>{{ user.phone }}</span>
          </Option>
        </Select>
      </div>
    </Dialog>
  </div>
</template>

<script>
import { Dialog, Input, Select, Option, Radio } from '@/components/ls';

export default {
  name: '',
  components: { Dialog, Input, Select, Option, Radio },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    form: {
      type: Object,
      default: () => {},
    },
    statusOptions: {
      type: Array,
      default: () => [],
    },
    leaderOptions: {
      type: Array,
      default: () => [],
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
    form(val) {
      this.innerForm = val;
    },
  },
  computed: {
    statusMap() {
      const result = {};
      this.statusOptions.forEach(item => {
        result[item.value] = item.label;
      });
      return result;
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
