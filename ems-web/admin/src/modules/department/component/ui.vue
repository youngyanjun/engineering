<template>
  <div :class="$style.content">
    <Tab :value="1">
      <TabItem label="组织结构" :value="1">
        <slot name="structure" />
      </TabItem>
      <TabItem label="角色与权限" :value="2">
        <slot name="permission" />
      </TabItem>
    </Tab>
  </div>
</template>

<script>
import { Tab, TabItem } from '@/components/ls';

export default {
  name: '',
  components: { Tab, TabItem },
  data() {
    return {

    };
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
