<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <div :class="$style.filter">
        <Checkbox
          :label="option.value"
          :key="option.value"
          v-for="option in statusOptions"
          v-model="status"
        >&nbsp;{{ option.label }}</Checkbox>
        <Input
          placeholder="输入项目名称"
          :suffix="true"
          v-model="keyword"
          @suffixClick="$emit('search', keyword)"
        />
      </div>
    </div>
    <div :class="$style.bd">
      <ul :class="$style.list">
        <li
          :class="[$style['list-item'], `is-type${item.status}`]"
          :key="item.id"
          v-for="item in list"
          v-show="status.includes(item.status) && new RegExp(keyword).test(item.name)"
          @click="$emit('itemClick', item)"
        >
          <div :class="$style['list-name']">{{ item.name | dafault }}</div>
          <div :class="$style['list-leader']">责任人：{{ item.leader | dafault}}</div>
          <div :class="$style['list-other']">
            <span :class="$style['list-status']">{{ statusMap[item.status] }}</span>
            <span :class="$style['list-time']">
              创建时间：{{ item.createdDate | dateFormat('yyyy.MM.dd') | dafault }}
            </span>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { Checkbox, Input } from '@/components/ls';

export default {
  name: '',
  components: { Checkbox, Input },
  props: {
    statusOptions: {
      type: Array,
      default: () => [],
    },
    list: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      status: [],
      keyword: '',
    };
  },
  created() {
    this.status = this.statusOptions.map(item => item.value);
  },
  computed: {
    statusMap() {
      const result = {};
      this.statusOptions.forEach(item => {
        result[item.value] = item.label;
      });
      return result;
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
