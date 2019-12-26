<template>
  <label :class="['ls-checkbox', { 'is-checked': value.includes(label) }]" @click="handleClick">
    <span class="ls-checkbox-prefix"></span><span><slot /></span>
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
      type: Array,
      default: () => [],
    },
  },
  methods: {
    handleClick() {
      const value = [...this.value];
      const label = this.label;
      let isExist = false;
      value.forEach((item, i) => {
        if (item === label) {
          isExist = true;
          value.splice(i, 1);
        }
      });
      if (!isExist) {
        value.push(label);
      }
      this.$emit('input', value);
    },
  },
};
</script>

<style lang="scss">
@import '../style/checkbox.scss';
</style>
