<template>
  <transition name="up">
    <div class="ls-dialog" v-if="visible">
      <div class="ls-dialog-innerwrap">
        <div class="ls-dialog-content" :class="[`is-${align}`]">
          <div class="ls-dialog-hd">
            <span class="ls-dialog-title">{{ title }}</span>
            <i class="ls-dialog-close" @click="handleOverlay" />
          </div>
          <div class="ls-dialog-bd">
            <slot />
          </div>
          <div class="ls-dialog-ft" v-if="!$slots.ft">
            <button class="ls-dialog-submit" @click="handleSubmit">确认</button><!--
        --><button class="ls-dialog-cancel" @click="handleOverlay">取消</button>
          </div>
          <slot v-else name="ft"></slot>
        </div>
        <div class="ls-dialog-overlay" @click="handleOverlay" @touchmove="e => {e.preventDefault();}"></div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'LsDialog',
  props: {
    value: {
      type: Boolean,
      default: false,
    },
    align: {
      type: String,
      default: 'center', // center bottom top left right
    },
    title: {
      type: String,
      default: '提示',
    },
  },
  created() {
    this.visible = this.value;
  },
  data() {
    return {
      visible: false,
    };
  },
  watch: {
    value(newVal) {
      this.visible = newVal;
    },
  },
  methods: {
    close() {
      this.visible = true;
    },
    /**
     * 遮罩被点击
     */
    handleOverlay() {
      this.$emit('input', false);
      this.close();
    },
    handleSubmit() {
      // this.$emit('input', false);
      this.$emit('submit');
      // this.close();
    },
  },
};
</script>

<style lang="scss">
@import '../style/dialog.scss';
</style>
