<template>
  <div :class="['ls-option', { 'is-active': isActive }]" @click="handleClick">
    <slot />
    <span v-if="!$slots.default || $slots.default.length <= 0">{{ label }}</span>
  </div>
</template>

<script>
export default {
  name: 'LSOption',
  props: {
    value: {
      default: '',
    },
    label: {
      type: String,
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
