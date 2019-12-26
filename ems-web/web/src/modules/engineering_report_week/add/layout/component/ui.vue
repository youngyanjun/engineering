<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <span :class="$style.title">
        <i :class="$style['title-icon']" />
        <label>新建周报</label>
        <span :class="$style['title-tips']">最近保存 16.11</span>
      </span>
      <span :class="$style.other">
        <Button @click="$emit('preview')">暂存并预览</Button>
        <Button @click="$emit('submit')" type="default">提交</Button>
        <slot name="user" />
      </span>
    </div>
    <div :class="$style.bd">
      <div :class="$style.aside">
        <div :class="$style.menu">
          <Radio
            v-for="item in menuList"
            :key="item.label"
            :label="item.label"
            v-model="currentMenu"
            :class="{ 'is-incorrect': item.incorrect }"
          >{{ item.title }}</Radio>
        </div>
      </div>
      <div :class="$style.main">
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
    routeName: String,
  },
  data() {
    return {
      currentMenu: '',
      userPoped: false,
    };
  },
  created() {
    this.currentMenu = this.routeName;
  },
  watch: {
    routeName(val) {
      this.currentMenu = val;
    },
    currentMenu(val) {
      this.$emit('menuItemClick', val);
    },
  },
  methods: {
    handleProtraitClick() {
      this.userPoped = !this.userPoped;
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
