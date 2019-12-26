<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <span>{{ '嘉兴海盐项目部周报 - 2019年第26周（2019.6.22 - 2019.6.28）' }}</span>
    </div>
    <div :class="$style.bd">
      <div :class="$style.field">
        <Excel
          :thead="thead"
          :tbody="tbody"
          :msgOptions="msgOptions"
          @currentCellChange="$emit('currentCellChange', arguments[0])"
        />
      </div>
      <div :class="$style.field">
        <div :class="$style['field-hd']"><span>驳回意见</span></div>
        <div :class="$style['field-bd']">
          <div :class="$style['rejection']">
            <div :class="$style['rejection-item']" v-for="item in 4">
              <div :class="$style['rejection-title']">{{ '项管中心' }}|{{ '2019-09-05' | dateFormat('yyyy.MM.dd') }}</div>
              <div :class="$style['rejection-detail']">
                {{ '周/月报被驳回后，每次进入编辑页面，都会在最底部看到驳回意见，多次驳回，会有多个驳回意见。' }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Excel from '@/components/excel';

export default {
  name: '',
  components: { Excel },
  props: {
    thead: Array,
    tbody: Array,
    msgOptions: Array,
  },
  data() {
    return {

    };
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
