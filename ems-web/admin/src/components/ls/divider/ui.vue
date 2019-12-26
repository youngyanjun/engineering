<script>
export default {
  name: 'LsDivider',
  props: {
    direction: {
      type: String,
      default: 'horizontal', // horizontal vertical
    },
  },
  render(h) {
    const { direction } = this;
    return h('div', {
      class: [
        'ls-divider',
        `is-${direction}`,
      ],
    });
  },
};
</script>

<style lang="scss">
@import '../style/divider.scss';
</style>
