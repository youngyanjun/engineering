<template>
  <div class="dialog">
    <Dialog
      :value="visible"
      title="编辑字段组"
      @input="$emit('close')"
      @submit="$emit('submit', {
        form: innerForm,
        list: innerList,
      })"
    >
      <p :class="$style.title"><Input v-model="innerForm.name" /></p>
      <p :class="$style.detail"><span>字段内容</span></p>
      <div :class="$style['drag-stage']">
        <p
          :class="$style['drag-item']"
          v-for="(item, index) in innerList"
          v-drag="{
            draggend: handleDrag,
            data: index,
          }"
        >
          <i :class="$style['drag-prex']" />
          <Input :value="item" @input="innerList[index] = arguments[0]" />
          <i :class="$style['drag-suffix']" @click="$emit('delete', index)" />
        </p>
        <p>
          <Button :class="$style.add" @click="$emit('add')">新增字段</Button>
        </p>
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
    list: {
      type: Array,
      default: () => [],
    },
    form: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      innerList: [],
      innerForm: {},
    };
  },
  created() {
    this.innerForm = this.form;
    this.innerList = this.list;
  },
  watch: {
    form(newVal) {
      this.innerForm = Object.assign(this.innerForm, newVal);
    },
    list(newVal) {
      this.innerList = newVal;
    },
  },
  methods: {
    handleDrag(oldIndex, newIndex) {
      console.log('drag', oldIndex, newIndex);
      const temp = this.innerList[oldIndex];
      newIndex = Math.max(0, Math.min(newIndex, this.innerList.length));
      if (newIndex > oldIndex) {
        this.innerList.splice(newIndex, 0, temp);
        this.innerList.splice(oldIndex, 1);
      } else {
        this.innerList.splice(oldIndex, 1);
        this.innerList.splice(newIndex, 0, temp);
      }
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
