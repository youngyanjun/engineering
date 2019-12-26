<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <Tab :value="routeName" @input="$emit('change', arguments[0])">
        <TabItem
          :label="option.label"
          :value="option.value"
          v-for="option in options"
          :key="option.value"
        />
      </Tab>
    </div>
    <div :class="$style.bd">
      <slot />
    </div>
  </div>
</template>

<script>
import { Tab, TabItem } from '@/components/ls';

export default {
  name: '',
  components: { Tab, TabItem },
  props: {
    options: {
      type: Array,
      default: () => [],
    },
    routeName: String,
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
