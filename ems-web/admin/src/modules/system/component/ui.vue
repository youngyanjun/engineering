<template>
  <div :class="$style.content">
    <div :class="$style.aside">
      <div :class="$style.menu">
        <div :class="$style['menu-hd']">设置项</div>
        <ul :class="$style['menu-bd']">
          <li :class="[$style['menu-item'], { 'is-active': true }]">工程报表日历-周次</li>
        </ul>
      </div>
    </div>
    <div :class="$style.bd">
      <div :class="$style['main-hd']">工程报表日历-周次</div>
      <div :class="$style['main-bd']">
        <div :class="$style.group">
          <span :class="$style['group-label']">选择年份</span>
          <Select :class="$style['group-value']" v-model="year">
            <Option :value="2019" :label="'2019'"></Option>
            <Option :value="2018" :label="'2018'"></Option>
          </Select>
        </div>
        <div :class="$style.group">
          <span :class="$style['group-label']">第一周的开始日期</span>
          <DatePicker :class="$style['group-value']" v-model="" />
        </div>
        <div :class="$style.group">
          <span :class="$style['group-label']"></span>
          <span>
            <Button type="default">保存并应用</Button>
            <Button>恢复默认</Button>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Select, Option, Input, Button } from '@/components/ls';
import { DatePicker } from 'element-ui';

export default {
  name: '',
  components: { Button, Select, Option, Input, DatePicker },
  data() {
    return {
      year: 2019,
    };
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
