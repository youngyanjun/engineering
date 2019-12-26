<template>
  <div :class="[
      'ls-input',
      `is-${size}`,
      {
        'is-error': error,
        'is-prepended': $slots.prepend,
        'is-appended': $slots.append,
        'is-textarea': type === 'textarea',
        'is-readonly': readonly,
      }
    ]"
  >
    <div class="ls-input-innerwrap">
      <span class="ls-input-prepend" v-if="$slots.prepend">
        <slot name="prepend" />
      </span>
      <i :class="['ls-input-prefix']" v-if="prefix" />
      <textarea
        class="ls-input-inner"
        :placeholder="placeholder"
        :type="type"
        :maxlength="maxLength"
        :readonly="readonly"
        v-model="innerValue"
        v-if="type === 'textarea'"
      />
      <input
        class="ls-input-inner"
        :placeholder="placeholder"
        :type="type"
        :maxlength="maxLength"
        :readonly="readonly"
        v-model="innerValue"
        v-else
        @focus="suggestionsVisible = true"
        @blur="$emit('blur', arguments[0])"
      />
      <i :class="['ls-input-suffix', suffixIcon]" v-if="suffix" @click="$emit('suffixClick', innerValue)" />
      <span class="ls-input-append" v-if="$slots.append">
        <slot name="append" />
      </span>
      <em class="ls-input-error">{{ error }}</em>
      <div class="ls-input-suggestions" v-show="suggestionsVisible && suggestionOptions.length > 0">
        <div
          class="ls-input-suggestions-item"
          @click="handleSuggestClick.call(this, item)"
          v-for="item in suggestionOptions"
        >{{ item.value }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LsInput',
  props: {
    value: null,
    error: String,
    maxLength: {
      type: [String, Number],
    },
    placeholder: {
      type: String,
    },
    prefix: {
      type: Boolean,
      default: false,
    },
    suffix: {
      type: Boolean,
      default: false,
    },
    suffixIcon: {
      type: String,
    },
    type: String,
    readonly: {
      type: Boolean,
      default: false,
    },
    suggestions: {
      type: Array,
      default: () => [],
    },
    size: {
      type: String,
      default: '', // large normal small
    },
  },
  data() {
    return {
      innerValue: '',
      suggestionsVisible: false,
    };
  },
  created() {
    this.innerValue = this.value;
  },
  watch: {
    value(newVal) {
      this.innerValue = newVal;
    },
    innerValue(newVal) {
      // console.log('innerValue => change', newVal);
      this.$emit('input', newVal);
    },
  },
  computed: {
    suggestionOptions() {
      return this.suggestions.filter(data => this.innerValue && data.value.match(this.innerValue));
    },
  },
  methods: {
    handleSuggestClick(data) {
      this.$emit('suggestionClick', data);
      this.innerValue = data.value;
      setTimeout(() => {
        this.suggestionsVisible = false;
      });
    },
  },
};
</script>

<style lang="scss">
@import '../style/input.scss';
</style>
