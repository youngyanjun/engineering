<template>
  <div :class="['ls-option', { 'is-active': isActive }]" @click="handleClick">
    <slot />
    <span v-if="!$slots.default || $slots.default.length <= 0">{{ label }}</span>
  </div>
</template>

<script>
export default {
  name: 'LsOption',
  props: {
    value: {
      default: '',
    },
    label: {
      type: [String, Number],
      default: '',
    },
  },
  data() {
    return {
      isActive: false,
    };
  },
  methods: {
    handleClick() {
      const { value, label } = this;
      this.$parent.handleOptionClick({ value, label });
    },
  },
};
</script>

<style lang="scss">
@import '../style/option.scss';
</style>
