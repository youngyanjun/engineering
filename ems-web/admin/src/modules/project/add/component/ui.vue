<template>
  <div class="dialog">
    <Dialog
      :title="isCreate ? '创建项目' : '编辑项目'"
      :value="visible"
      :class="$style.bd"
      @input="$emit('close')"
      @submit="$emit('submit', innerForm)"
    >
      <div class="dialog-group">
        <span class="dialog-field">项目名称</span>
        <Input class="dialog-input" v-model="innerForm.engineeringName" />
      </div>
      <div class="dialog-group">
        <div class="dialog-cell">
          <span class="dialog-field">项目简介</span>
          <Input class="dialog-input" placeholder="最多十个字" v-model="innerForm.engineeringAbbreviation" />
        </div>
        <div class="dialog-cell">
          <span class="dialog-field">项目编码</span>
          <Input class="dialog-input" v-model="innerForm.engineeringCode" />
        </div>
      </div>
      <div class="dialog-group">
        <div class="dialog-cell">
          <span class="dialog-field">合同额</span>
          <Input class="dialog-input" v-model="innerForm.contentPrice">
            <template slot="append">元</template>
          </Input>
        </div>
        <div class="dialog-cell">
          <span class="dialog-field">管理员</span>
          <Input
            class="dialog-input"
            placeholder="请输入管理员姓名"
            v-model="innerForm.managerName"
            :suggestions="managerSuggestions"
            @suggestionClick="$emit('suggestionChange', arguments[0])"
            :error="error.managerName.msg"
          />
        </div>
      </div>
      <div class="dialog-group">
        <div class="dialog-cell">
          <div>
            <span class="dialog-field">项目范围</span>
            <ul :class="$style.range">
              <li :class="$style['range-item']" v-for="item in ranges">
                <Checkbox v-model="form.rangeListIds" :label="item.value">{{ item.label }}</Checkbox>
                <label :class="$style['range-radio']">
                  <Radio label="1" v-model="form.rangeListStatus[item.value]" :disabled="!form.rangeListIds.includes(item.value)">未开始</Radio>
                  <Radio label="2" v-model="form.rangeListStatus[item.value]" :disabled="!form.rangeListIds.includes(item.value)">进行中</Radio>
                  <Radio label="3" v-model="form.rangeListStatus[item.value]" :disabled="!form.rangeListIds.includes(item.value)">已完成</Radio>
                </label>
              </li>
            </ul>
          </div>
        </div>
        <div class="dialog-cell">
          <div>
            <div class="dialog-group">
              <span class="dialog-field">涉及分包</span>
            </div>
            <div class="dialog-group" :class="$style['subpackage']" v-for="(item, index) in innerForm.subcontractList" :key="item.subcontractorId">
              <div class="dialog-cell">
                <Select v-model="item.subcontractorId">
                  <Option :value="option.id" :label="option.unitName" v-for="option in subpackageList"></Option>
                </Select>
              </div>
              <div class="dialog-cell">
                <Input class="dialog-input" v-model="item.subcontractorPrice">
                  <template slot="append">元</template>
                </Input>
                <i :class="$style.delete" @click="handleDeleteSubpackage.call(this, index)" />
              </div>
            </div>
            <div class="dialog-group">
              <Button :class="$style.add" @click="handleAddSubpackage">新增分包</Button>
            </div>
          </div>
        </div>
      </div>
    </Dialog>
  </div>
</template>

<script>
import { Dialog, Input, Radio, Checkbox, Select, Option, Button } from '@/components/ls';

export default {
  name: '',
  components: { Dialog, Input, Button, Radio, Checkbox, Select, Option },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    form: {
      type: Object,
      default: () => {},
    },
    error: {
      type: Object,
      default: () => {},
    },
    isCreate: {
      type: Boolean,
      default: false,
    },
    subpackageList: {
      type: Array,
      default: () => [],
    },
    managerSuggestions: {
      type: Array,
      default: () => [],
    },
    ranges: {
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
    form(newVal) {
      this.innerForm = Object.assign(this.innerForm, newVal);
    },
  },
  methods: {
    handleAddSubpackage() {
      this.innerForm.subcontractList.push({});
    },
    handleDeleteSubpackage(index) {
      this.innerForm.subcontractList.splice(index, 1);
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
