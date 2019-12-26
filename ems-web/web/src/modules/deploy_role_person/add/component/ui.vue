<template>
  <div class="dialog">
    <Dialog
      :title="`添加成员（${selection.length}）`"
      :value="visible"
      @input="$emit('close')"
      @submit="$emit('submit', selection)"
    >
      <div :class="$style.search">
        <Input placeholder="搜索" v-model="keyword" @input="$emit('keywordChange')" />
      </div>
      <div :class="$style.list">
        <p :class="$style.group" v-for="(user, i) in userOptions">
          <Checkbox :label="user.id" v-model="selection">
            <span :class="$style['group-username']">{{ user.userName }}</span>
            <span :class="$style['group-mobile']">{{ user.phone }}</span>
          </Checkbox>
        </p>
      </div>
    </Dialog>
  </div>
</template>

<script>
import { Dialog, Input, Button, Checkbox } from '@/components/ls';

export default {
  name: '',
  components: { Dialog, Input, Button, Checkbox },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    userOptions: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      keyword: '',
      selection: [],
    };
  },
  watch: {
    visible() {
      this.selection = [];
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
