<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <div :class="$style.title">填写辅助</div>
      <div :class="$style.subtitle">填写辅助说明填写辅助说明填写辅助说明</div>
    </div>
    <div :class="$style.bd">
      <slot />
      <div :class="$style.empty" v-if="!$slots.default">
        <div>
          <!-- <i :class="$style.icon" /> -->
        </div>
        <div :class="$style.group">暂无辅助信息</div>
        <div :class="$style.group">您可通过网页顶端的反馈功能将您的想法反馈给开发人员</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: '',
  data() {
    return {

    };
  },
};
</script>

<style lang="scss" module>
@import './help.scss';
</style>
