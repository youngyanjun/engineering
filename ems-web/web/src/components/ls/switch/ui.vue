<template>
  <span
    :class="[
      'ls-switch', {
        'is-checked': value,
        'is-disabled': disabled
      }
    ]"
    @click="handleClick"
  >
    <span v-if="$slots.default"><slot /></span>
  </span>
</template>

<script>
export default {
  name: 'LSSwitch',
  props: {
    label: {
      type: [String, Number],
      default: '',
    },
    value: {
      type: [Array, Boolean],
      default: () => [],
    },
    disabled: {
      type: Boolean,
      default: false,
    },
  },
  watch: {
    value(val) {
      this.$emit('change', val);
    },
  },
  methods: {
    handleClick() {
      if (this.disabled === true) {
        return;
      }
      this.$emit('input', !this.value);
    },
  },
};
</script>

<style lang="scss">
@import '../style/switch.scss';
</style>
