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
        <div :class="$style.title">可能影响工程的因素</div>
        <Divider />
        <div :class="$style['field']" v-for="k in Object.keys(config.table)">
          <EditedTable
            :thead="config.table[k].content"
            :tbody="form[k]"
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
