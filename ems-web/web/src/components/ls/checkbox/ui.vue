<template>
  <span
    :class="['ls-checkbox', { 'is-checked': typeof value === 'boolean' ? value : value.includes(label) }]"
    @click="handleClick"
  >
    <span class="ls-checkbox-prefix"></span><span v-if="$slots.default"><slot /></span>
  </span>
</template>

<script>
export default {
  name: 'LsCheckbox',
  props: {
    label: {
      type: [String, Number],
      default: '',
    },
    value: {
      type: [Array, Boolean],
      default: () => [],
    },
    single: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    handleClick() {
      if (this.single) {
        this.$emit('input', !this.value);
        return;
      }
      const value = [...this.value];
      console.log(value);
      const label = this.label;
      let isExist = false;
      value.forEach((item, i) => {
        console.log(item, label);
        if (item === label) {
          isExist = true;
          value.splice(i, 1);
        }
      });
      if (!isExist) {
        value.push(label);
      }
      this.$emit('input', value);
      // console.log(value, label);
    },
  },
};
</script>

<style lang="scss">
@import '../style/checkbox.scss';
</style>
