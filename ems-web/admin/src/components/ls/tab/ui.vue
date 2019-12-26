<script>
export default {
  name: 'LSTab',
  props: {
    value: {
      type: [Number, String],
      default: 0,
    },
  },
  created() {
    const { value } = this;
    this.innerValue = value;
  },
  render(h) {
    const { $slots, tabs, innerValue } = this;
    const tabsNode = [];
    tabs.forEach(item => {
      tabsNode.push(h('div', {
        class: ['ls-tab-nav', { 'is-active': innerValue === item.value }],
        on: {
          click: ev => {
            this.innerValue = item.value;
          },
        },
      }, [
        h('span', {}, [
          item.label,
        ]),
      ]));
    });
    return h('div', {
      class: 'ls-tab',
      ref: 'container',
    }, [
      h('div', {
        class: 'ls-tab-navs',
      }, tabsNode),
      h('div', {
        class: 'ls-tab-items',
      }, [
        $slots.default,
      ]),
    ]);
  },
  data() {
    return {
      innerValue: 0,
      tabs: [],
    };
  },
  watch: {
    value(newVal) {
      this.innerValue = newVal;
    },
    innerValue(newVal) {
      this.$emit('input', newVal);
      this.init();
    },
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      const { innerValue } = this;
      this.tabs = this.$children;
      this.tabs.forEach(item => {
        item.isActive = false;
        if (item.value === innerValue) {
          item.isActive = true;
        }
      });
    },
  },
};
</script>

<style lang="scss">
@import '../style/tab.scss';
</style>
