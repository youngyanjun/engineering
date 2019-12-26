<template>
  <div :class="$style.content">
    <section :class="$style.hd">
      <div :class="$style.logo">
        <i :class="$style['icon-logo']" />
        <!-- <span>之云工程管理后台</span> -->
      </div>
      <div :class="$style.menu">
        <Radio v-model="current" label="PersonList">人员管理</Radio>
        <Radio v-model="current" label="OrganizationList">组织管理</Radio>
        <Radio v-model="current" label="ProjectList"> 项目管理</Radio>
        <Radio v-model="current" label="SubpackageList">分包管理</Radio>
        <Radio v-model="current" label="Department">默认项目部配置</Radio>
        <Radio v-model="current" label="LogList">系统日志</Radio>
        <Radio v-model="current" label="FieldList">自定义字段配置</Radio>
        <!-- <Button type="text" @click="$emit('goto', 'System')">系统设置 -->
      </div>
      <div :class="$style.other">
        <i :class="$style.help" /><!--
     --><i :class="$style.feedback" />
        <img :class="$style.portrait" src="~@/assets/images/header_default.png" alt="头像" @click="$emit('portraitClick')" />
        <div :class="$style['user-pop']" v-show="userPoped">
          <Button type="text" @click="$emit('logout')">退出登录</Button>
        </div>
      </div>
    </section>
    <section :class="$style.bd">
      <slot />
    </section>
  </div>
</template>

<script>
import { Radio, Button } from '@/components/ls';

export default {
  name: 'LayoutUI',
  components: { Button, Radio },
  props: {
    routeName: String,
    userPoped: Boolean,
  },
  data() {
    return {
      current: this.routeName,
    };
  },
  watch: {
    routeName(newVal) {
      this.current = newVal;
    },
    current(newVal) {
      this.$emit('goto', newVal);
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
