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
        <div :class="$style.title">项目进展情况</div>
        <Divider />
        <div :class="$style['field']" v-for="k in Object.keys(config.table)">
          <div :class="$style['field-hd']">{{ config.table[k].title }}</div>
          <EditedTable
            :thead="config.table[k].content"
            :tbody="innerForm[k]"
          >
            <Select slot="content" slot-scope="scope" v-model="scope.data.content">
              <Option :label="item" :value="item" v-for="item in config.contractTypes"></Option>
            </Select>
          </EditedTable>
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周施工人员及机械</div>
          <EditedTable
            :rowEditabled="false"
            :thead="tableHead.personnel.content"
            :tbody="innerForm.personnel"
          />
        </div>
        <div :class="$style['field']">
          <EditedTable
            :rowEditabled="false"
            :thead="tableHead.machine.content"
            :tbody="innerForm.machine"
          />
        </div>
        <div :class="$style['field']">
          <Input type="textarea" placeholder="请输入备注" v-model="form.stageInfoRemark" />
        </div>
        <div :class="[$style['group'], 'is-center']">
          <Button :class="$style.submit" size="large" @click="$emit('back')">上一步</Button>
          <Button :class="$style.submit" type="default" size="large" @click="submit">下一步</Button>
        </div>
      </div>
    </div>
    <div :class="$style.aside">
      <Help :class="$style.help">
        <div :class="$style['help-hd']">本周计划</div>
        <div :class="$style['field']" v-for="k in Object.keys(config.table)" v-show="help[k] && help[k].length > 0">
          <div :class="$style['field-hd']">{{ config.table[k].title }}</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.table[k].content"
            :tbody="help[k]"
          >
            <Select slot="content" slot-scope="scope" v-model="scope.data.content">
              <Option :label="item" :value="item" v-for="item in config.contractTypes"></Option>
            </Select>
          </EditedTable>
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周施工人员及机械</div>
          <EditedTable
            :rowEditabled="false"
            :thead="help.tableHead.personnel.content"
            :tbody="help.personnel"
          />
        </div>
        <div :class="$style['field']">
          <EditedTable
            :rowEditabled="false"
            :thead="help.tableHead.machine.content"
            :tbody="help.machine"
          />
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">备注</div>
          <div :class="$style['field-bd']">{{ help.stageInfoRemark || '无' }}</div>
        </div>
      </Help>
    </div>
  </div>
</template>

<script>
import { Divider, Input, Button, Select, Option } from '@/components/ls';
import Help from '../../component/help';
import EditedTable from '../../component/edited_table';

export default {
  name: '',
  components: { Divider, Input, Button, Help, EditedTable, Select, Option },
  props: {
    form: {
      type: Object,
      default: () => {},
    },
    config: {
      type: Object,
      default: () => {},
    },
    tableHead: {
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
