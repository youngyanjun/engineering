<template>
  <div :class="$style.content">
    <div :class="$style.main">
      <div :class="$style.hd">
        <span>
          {{ config.baseInfo.reportName }}（{{ config.baseInfo.constructionDateStart | dateFormat('yyyy-MM-dd') }} - {{ config.baseInfo.constructionDateFinis | dateFormat('yyyy-MM-dd') }}）
          <i />
        </span>
      </div>
      <div :class="$style.bd">
        <div :class="$style.title">天气状况</div>
        <Divider />
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周天气实况</div>
          <EditedTable
            :rowEditabled="false"
            :thead="tableHead.forecast.content"
            :tbody="innerForm.forecast"
          />
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">下周天气预报<span :class="$style.danger">（预测信息，不可修改）</span></div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="tableHead.nextForecast.content"
            :tbody="innerForm.nextForecast"
          />
        </div>
        <div :class="[$style['group'], 'is-center']">
          <Button :class="$style.submit" size="large" @click="$emit('back')">上一步</Button>
          <Button :class="$style.submit" type="default" size="large" @click="submit">下一步</Button>
        </div>
      </div>
    </div>
    <div :class="$style.aside">
      <Help>
        <div :class="$style['help-section']">
          <div :class="$style['help-group']">
            天气情况来自于：**地气象局
          </div>
          <div :class="$style['help-group']">
            http://www.weather.com.cn/weather/101020100.shtml
          </div>
        </div>
      </Help>
    </div>
  </div>
</template>

<script>
import { Divider, Input, Button } from '@/components/ls';
import Help from '../../component/help';
import EditedTable from '../../component/edited_table';

export default {
  name: '',
  components: { Divider, Input, Button, Help, EditedTable },
  props: {
    form: {
      type: Object,
      default: () => {},
    },
    formHead: {
      type: Object,
      default: () => {},
    },
    tableHead: {
      type: Object,
      default: () => {},
    },
    config: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      innerForm: {},
    };
  },
  created() {
    this.innerForm = this.form;
  },
  watch: {
    form(val) {
      this.innerForm = val;
    },
  },
  methods: {
    submit(type = 'next') {
      this.$emit('submit', {
        type,
        data: this.innerForm,
      });
    },
  },
};
</script>

<style lang="scss" module>
@import '../../component/form.scss';
@import './ui.scss';
</style>
