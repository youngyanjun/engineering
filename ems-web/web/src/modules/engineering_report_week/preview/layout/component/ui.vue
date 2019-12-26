<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <span :class="$style.title">
        <i :class="$style['title-icon']" />
        <label>周报</label>
      </span>
      <span :class="$style.other">
        <Button @click="$emit('print')">打印</Button>
        <Button v-show="editVisible" @click="$emit('edit')">编辑</Button>
        <Button v-show="submitVisible" @click="$emit('submit')" type="default">提交</Button>
        <slot name="user" />
      </span>
    </div>
    <div :class="$style.bd">
      <div :class="$style.aside">
        <div :class="$style.menu">
          <Radio
            :label="item.value"
            :key="item.value"
            v-for="item in menuList"
            v-model="currentMenu"
          >{{ item.label }}</Radio>
        </div>
      </div>
      <div :class="[$style.main, 'print-content']">
        <slot />
      </div>
    </div>
  </div>
</template>

<script>
import { Radio, Button } from '@/components/ls';

export default {
  name: '',
  components: { Radio, Button },
  props: {
    menuList: {
      type: Array,
      default: () => [],
    },
    editVisible: Boolean,
    submitVisible: Boolean,
  },
  data() {
    return {
      currentMenu: this.menuList[0].value,
      userPoped: false,
    };
  },
  watch: {
    currentMenu(val) {
      this.$emit('menuItemClick', val);
    },
  },
  methods: {
    handleProtraitClick() {
      this.userPoped = !this.userPoped;
    },
    scroll(val) {
      this.$refs.stage.scrollTop = val;
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
