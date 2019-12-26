<template>
  <div class="dialog">
    <Dialog
      v-if="config.weeks.length > 0"
      title="选择周次"
      :value="visible"
      @input="$emit('close')"
    >
      <div :class="$style.title">您有多个周次未填写，请选择本次周报的周次</div>
      <div :class="$style.list">
        <Radio
          v-model="currentWeekIndex"
          v-for="(item, index) in config.weeks"
          :value="index"
          :label="index"
        >第{{ item.weekly }}周
          <span :class="$style.date">
            {{ checkThisWeek(item.sdate, item.edate) ? '本周' : ''}}
            {{ item.sdate | dateFormat('MM.dd') }} ~ {{ item.edate | dateFormat('MM.dd') }}
          </span>
        </Radio>
      </div>
      <div slot="ft">
        <Button
          :class="$style.btn"
          type="default"
          size="large"
          @click="$emit('add', config.weeks[currentWeekIndex])"
        >新建周报</Button>
      </div>
    </Dialog>
    <Dialog v-else :class="$style.alert" title="提示" :value="visible" @input="$emit('close')">
      <div :class="$style['alert-hd']">当前无可用周次</div>
      <div :class="$style['alert-bd']">
        请返回<Button type="text" @click="$emit('back')">工程报表列表</Button>查看已创建的周报
      </div>
      <div slot="ft">
        <Button :class="$style.btn" type="default" @click="$emit('back')">返回</Button>
      </div>
    </Dialog>
  </div>
</template>

<script>
import { Dialog, Radio, Button } from '@/components/ls';

export default {
  name: '',
  components: { Dialog, Radio, Button },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    config: {
      type: Object,
      default: () => {},
    },
  },
  methods: {
    checkThisWeek(start, end) {
      return Date.now() >= new Date(start).getTime() && Date.now() <= new Date(end).getTime();
    },
  },
  data() {
    return {
      currentWeekIndex: 0,
    };
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
