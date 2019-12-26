<script>
const name = 'LsButton';
const namespace = 'ls-button';

export default {
  name,
  props: {
    type: {
      type: String,
      default: 'base', // default success danger warning
    },
    size: {
      type: String,
      default: '', // large normal small
    },
    plain: {
      type: Boolean,
      default: false,
    },
    round: {
      type: Boolean,
      default: false,
    },
    disabled: {
      type: Boolean,
      default: false,
    },
  },
  render(h) {
    const { $slots, type, size, round, plain, disabled } = this;
    const decorations = [];
    if (size) {
      decorations.push(`is-${size}`);
    }
    if (type) {
      decorations.push(`is-${type}`);
    }
    return h('button', {
      class: [
        namespace,
        ...decorations,
        {
          'is-round': round,
        },
        {
          'is-plain': plain,
        },
        {
          'is-disabled': disabled === true,
        },
      ],
      on: {
        click: () => {
          if (disabled === false) {
            this.$emit('click');
          }
        },
      },
    }, [
      h('span', {}, [$slots.default]),
    ]);
  },
};
</script>

<style lang="scss">
@import '../style/button.scss';
</style>
