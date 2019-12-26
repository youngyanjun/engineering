<template>
  <label
    :class="['ls-radio', { 'is-checked': value === label, 'is-disabled': disabled }]"
    @click="disabled ? '' : $emit('input', label)"
  >
    <span class="ls-radio-prefix"></span><span @click="$emit('click')"><slot /></span>
  </label>
</template>

<script>
export default {
  name: '',
  props: {
    label: {
      type: String,
      default: '',
    },
    value: {
      type: [String, Number],
      default: '',
    },
    disabled: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {

    };
  },
};
</script>

<style lang="scss">
@import '../style/radio.scss';
</style>
