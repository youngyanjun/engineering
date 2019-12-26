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
        <div :class="$style.title">基本信息</div>
        <Divider />
        <div :class="$style['form']">
          <div :class="[$style['group'], 'is-require']">
            <label :class="$style['group-label']">工程名称</label>
            <Input :class="$style['group-value']" v-model="innerForm.constructionName" />
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">建设单位</label>
            <Input :class="$style['group-value']" v-model="innerForm.constructionUnit" />
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">监理单位</label>
            <Input :class="$style['group-value']" v-model="innerForm.qualityInspectionUnit" />
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">质监站</label>
            <Input :class="$style['group-value']" v-model="innerForm.qualityInspectionStation" />
          </div>
          <Divider shape="dashed" />
          <div :class="[$style['group'], 'is-require']">
            <label :class="$style['group-label']">项目经理</label>
            <Input :class="$style['group-value']" v-model="innerForm.projecLeader" />
          </div>
          <div :class="[$style['group'], 'is-require']">
            <label :class="$style['group-label']">技术负责人</label>
            <Input :class="$style['group-value']" v-model="innerForm.technicalLeader" />
          </div>
          <div :class="[$style['group'], 'is-require']">
            <label :class="$style['group-label']">安全员</label>
            <Input :class="$style['group-value']" v-model="innerForm.safeMan" />
          </div>
          <Divider shape="dashed" />
          <div :class="[$style['group'], 'is-require']">
            <label :class="$style['group-label']">合同总价</label>
            <Input :class="[$style['group-value'], { 'is-small': true }]" v-model="innerForm.contractPrice">
              <span slot="append">%</span>
            </Input>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">已收款</label>
            <Input :class="[$style['group-value'], { 'is-small': true }]" v-model="innerForm.moneyBack">
              <span slot="append">%</span>
            </Input>
          </div>
          <Divider shape="dashed" />
          <div :class="$style['group']">
            <label :class="$style['group-label']">周次</label>
            <div :class="$style['group-value']">
              第{{ innerForm.weeklyNum }}周
            </div>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">施工周期</label>
            <div :class="$style['group-value']">
              {{ innerForm.constructionDateStart | dateFormat('yyyy-MM-dd') }} ~ {{ innerForm.constructionDateFinis | dateFormat('yyyy-MM-dd') }}
            </div>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">报送者</label>
            <Input :class="$style['group-value']" v-model="innerForm.reportBy" />
          </div>
          <Divider shape="dashed" />
          <div :class="[$style['group'], 'is-require']">
            <label :class="$style['group-label']">完工率</label>
            <Input :class="[$style['group-value'], 'is-small']" v-model="innerForm.completionRate">
              <span slot="append">%</span>
            </Input>
          </div>
          <div :class="[$style['group'], 'is-center']">
            <Button :class="$style.submit" type="default" size="large" @click="submit">下一步</Button>
          </div>
        </div>
      </div>
    </div>
    <div :class="$style.aside">
      <Help>
        <div :class="$style['help-section']">
          <div :class="$style['help-group']"></div>
          <div :class="$style['help-group']">工程名称：{{ help.baseInfo.constructionName }}</div>
          <div :class="$style['help-group']">建设单位：{{ help.baseInfo.constructionUnit }}</div>
          <div :class="$style['help-group']">监理单位：{{ help.baseInfo.qualityInspectionUnit }}</div>
          <div :class="$style['help-group']">质监站：{{ help.baseInfo.qualityInspectionStation }}</div>
        </div>
        <div :class="$style['help-section']">
          <div :class="$style['help-group']">项目经理：{{ help.baseInfo.projecLeader }}</div>
          <div :class="$style['help-group']">技术负责人：{{ help.baseInfo.technicalLeader }}</div>
          <div :class="$style['help-group']">安全员：{{ help.baseInfo.safeMan }}</div>
        </div>
        <div :class="$style['help-section']">
          <div :class="$style['help-group']">合同总价：{{ '暂缺' }}万元</div>
        </div>
      </Help>
    </div>
  </div>
</template>

<script>
import { Divider, Input, Button, Select, Option } from '@/components/ls';
import Help from '../../component/help';

export default {
  name: '',
  components: { Divider, Input, Button, Help, Select, Option },
  props: {
    form: {
      type: Object,
      default: () => {},
    },
    config: {
      type: Object,
      default: () => {},
    },
    help: {
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
