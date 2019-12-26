<script>
export default {
  name: 'LsDivider',
  props: {
    direction: {
      type: String,
      default: 'horizontal', // horizontal vertical
    },
    shape: {
      type: String,
      default: '', // solid dashed
    },
  },
  render(h) {
    const { direction, shape } = this;
    return h('div', {
      class: [
        'ls-divider',
        `is-${direction}`,
        `is-${shape}`,
      ],
    });
  },
};
</script>

<style lang="scss">
@import '../style/divider.scss';
</style>
