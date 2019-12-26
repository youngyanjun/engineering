<template>
  <div
    :class="['ls-select', { 'is-active': !!innerValue }, { 'is-folded': !optionVisible }, { 'is-unfolded': optionVisible }]"
    :tabindex="-1"
    @click.self="handleFocus"
    @focus="handleFocus"
    @blur="handleBlur"
  >
    <input
      ref="input"
      class="ls-select-input"
      type="text"
      readonly="readonly"
      autocomplete="off"
      :placeholder="placeholder"
      :value="inputValue"
    />
    <span class="ls-select-suffix"></span>
    <div class="ls-select-dropdown" v-show="optionVisible">
      <slot />
    </div>
  </div>
</template>

<script>
export default {
  name: 'LSSelect',
  props: {
    placeholder: {
      type: String,
      default: '请选择',
    },
    value: {
      type: [Boolean, String, Number],
      default: '',
    },
  },
  data() {
    return {
      optionVisible: false,
      inputValue: '',
      innerValue: '',
    };
  },
  watch: {
    value(newVal) {
      this.init(newVal);
    },
  },
  mounted() {
    this.init(this.value);
  },
  methods: {
    init(newVal) {
      this.innerValue = newVal;
      this.$children.forEach(child => {
        if (child.value === newVal) {
          child.isActive = true;
          this.inputValue = child.label;
        } else {
          child.isActive = false;
        }
      });
    },
    handleFocus() {
      this.optionVisible = true;
    },
    handleBlur() {
      this.optionVisible = false;
    },
    handleOptionClick({ value, label }) {
      const isChange = this.value !== value;
      this.inputValue = label;
      this.innerValue = value;
      this.optionVisible = false;
      this.$emit('input', this.innerValue);
      if (isChange) {
        this.$emit('change', this.innerValue);
      }
    },
  },
};
</script>
