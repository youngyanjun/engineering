<script>
export default {
  name: 'LsTabItem',
  props: {
    label: {
      type: String,
      default: '',
    },
    value: {
      type: [String, Number],
      default: '',
    },
  },
  data() {
    return {
      isActive: false,
    };
  },
  render(h) {
    const { $slots, isActive } = this;
    return h('div', {
      class: ['ls-tab-item', { 'is-active': isActive }],
    }, [
      $slots.default,
    ]);
  },
};
</script>
