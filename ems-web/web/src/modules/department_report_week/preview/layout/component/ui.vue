<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <span :class="$style.title">
        <i :class="$style['title-icon']" />
        <label>周报</label>
        <!-- <span :class="$style['title-tips']">最近保存 16.11</span> -->
      </span>
      <span :class="$style.other">
        <Button v-show="editVisible" @click="$emit('edit')">编辑</Button>
        <Button v-show="submitVisible" @click="$emit('submit')" type="default">提交</Button>
        <slot name="user" />
      </span>
    </div>
    <div :class="$style.bd">
      <div :class="$style.main">
        <slot />
      </div>
    </div>
  </div>
</template>

<script>
import { Button } from '@/components/ls';

export default {
  name: '',
  components: { Button },
  props: {
    editVisible: Boolean,
    submitVisible: Boolean,
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
