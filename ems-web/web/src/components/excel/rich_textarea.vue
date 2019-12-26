<template>
  <div
    :class="['bh-rich-textarea', { 'is-readonly': readonly }]"
  >
    <div
      class="bh-rich-textarea-input"
      ref="stage"
      @input="handleInput"
      @blur="handeBlur"
      @keydown="handleKeydown"
      v-html="innerValue"
    />
    <ul
      class="bh-rich-textarea-list"
      v-show="optionsVisible && !msgDisabled"
      :style="{ top: tempRange.top - msgOptions.length * 28 + 'px', left: tempRange.left + 'px' }"
    >
      <li
        class="bh-rich-textarea-item"
        v-for="msg in msgOptions"
        :key="msg.value"
        @click="handleAddMsg.call(this, msg)"
      >{{ msg.label }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: '',
  props: {
    value: {
      type: String,
      default: '',
    },
    msgOptions: {
      type: Array,
      default: () => [],
    },
    readonly: {
      type: Boolean,
      default: false,
    },
    msgDisabled: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      range: null,
      optionsVisible: false,
      tempRange: {},
      innerValue: this.value,
    };
  },
  created() {
    document.addEventListener('selectionchange', this.handleSelectionchange);
  },
  watch: {
    value(val) {
      console.log('change', this.$refs.stage.innerHTML, val);
      if (this.$refs.stage.innerHTML !== val) {
        this.innerValue = val;
      }
    },
  },
  methods: {
    /**
     * 添加消息节点
     */
    addMsg(msg, id) {
      console.log('addMsg', msg, id);
      const dom = document.createElement('custom-msg');
      dom.innerHTML = `@${msg}<custom-userid>${id}</custom-userid>&nbsp;`;
      // 删除@
      this.range.setStart(this.tempRange.commonAncestorContainer, this.tempRange.startOffset);
      this.range.setEnd(this.tempRange.commonAncestorContainer, this.tempRange.startOffset + 1);
      this.range.deleteContents();
      // 插入节点
      this.range.insertNode(dom);
      this.range.setStartAfter(dom);
      this.$emit('input', this.$refs.stage.innerHTML);
    },
    /**
     * 记录光标位置
     */
    setRange() {
      let commonAncestorContainer, startOffset, position, dom;

      commonAncestorContainer = this.range.commonAncestorContainer;
      startOffset = this.range.startOffset - 1;
      dom = document.createElement('custom-fill');
      this.range.setStart(commonAncestorContainer, startOffset + 1);
      this.range.insertNode(dom);
      position = dom.getBoundingClientRect();
      this.tempRange = Object.assign({}, {
        commonAncestorContainer,
        startOffset,
        top: position.top,
        left: position.left,
      });
      dom.parentNode.removeChild(dom);
    },
    /**
     * 更新光标位置
     */
    handleSelectionchange() {
      let sel = window.getSelection();
      let range = sel.rangeCount > 0 ? sel.getRangeAt(0) : null;
      this.range = range;
      // console.log('input sel', range);
    },
    handleKeydown(ev) {
      console.log('keydown', ev);
      if (ev.key === '@' || ev.key === 'Process') {
        setTimeout(() => {
          this.setRange();
          this.optionsVisible = true;
        });
      }
    },
    handleInput(ev) {
      console.log('input', ev);
      this.optionsVisible = false;
      this.$emit('input', this.$refs.stage.innerHTML);
    },
    /**
     * 添加消息
     */
    handleAddMsg({ label, value }) {
      this.addMsg(label, value);
      this.optionsVisible = false;
      this.$refs.stage.focus();
    },
    handeBlur() {
      if (this.optionsVisible === true) {
        // 失去焦点时隐藏列表，防止列表点击失效，延迟一个click事件的判断时间200毫秒
        setTimeout(() => {
          this.optionsVisible = false;
        }, 200);
      }
    },
  },
  destroyed() {
    document.removeEventListener('selectionchange', this.handleSelectionchange);
  },
};
</script>

<style lang="scss">
.bh-rich-textarea {

  &.is-readonly &-input {
    -webkit-user-modify: read-only !important;
  }

  &-input {
    width: 100%;
    height: 100%;
    -webkit-user-modify: read-write-plaintext-only !important;
    outline: none;
  }

  &-list {
    position: fixed;
    top: 0;
    min-width: 64px;
    max-height: 128px;
    background-color: #fff;
    overflow-y: auto;
    z-index: 1;
    cursor: pointer;
    box-shadow: 0 2px 6px rgba(68, 82, 121, .16);
  }

  &-item {
    box-sizing: border-box;
    width: 90px;
    height: 28px;
    padding: 4px;
    font-size: 12px;
    color: #445279;
    line-height: 20px;

    &:hover {
      background-color: rgba(85, 168, 253, .24);
    }
  }

  custom-msg {
    display: inline-block;
    -webkit-user-modify: read-only !important;
    color: #55A8FD;
  }

  custom-userid {
    display: none;
  }
}
</style>
