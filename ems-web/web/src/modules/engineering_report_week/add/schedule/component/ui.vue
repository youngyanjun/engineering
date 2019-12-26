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
        <div :class="$style.title">
          形象进度
          <template v-if="!batched">
            <Button type="default" @click="batched = true">批量选择</Button>
          </template>
          <template v-else>
            <Button type="danger" @click="$emit('batchedDelete', batchedList)">批量删除</Button>
            <Button type="default" @click="handleBatchedCancel">取消</Button>
          </template>
        </div>
        <Divider />
        <div :class="[$style.thumb, { 'is-batched': batched }]">
          <Upload :class="$style.upload" @change="$emit('upload', arguments[0])" />
          <div :class="$style['thumb-item']" v-for="item in thumbList">
            <div :class="$style['thumb-delete']">
              <Button
                :class="$style['thumb-delete-btn']"
                type="danger"
                size="large"
                @click="$emit('batchedDelete', [item.id])"
              >删除</Button>
            </div>
            <Checkbox :class="$style['thumb-checkbox']" :label="item.id" v-model="batchedList">
              <img :class="$style['thumb-img']" :src="item.url" />
              <Input
                :class="$style['thumb-description']"
                placeholder="请输入描述，最多15字"
                v-model="item.description"
                max-length="15"
              />
            </Checkbox>
          </div>
        </div>
        <div :class="[$style['group'], 'is-center']">
          <Button :class="$style.submit" size="large" @click="$emit('back')">上一步</Button>
          <Button :class="$style.submit" type="default" size="large" @click="submit">下一步</Button>
        </div>
      </div>
    </div>
    <div :class="$style.aside">
      <Help />
    </div>
  </div>
</template>

<script>
import { Divider, Button, Upload, Checkbox, Input } from '@/components/ls';
import Help from '../../component/help';

export default {
  name: '',
  components: { Divider, Button, Help, Upload, Checkbox, Input },
  props: {
    thumbList: {
      type: Array,
      default: () => [],
    },
    config: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      batchedList: [],
      batched: false,
      innerForm: {},
    };
  },
  methods: {
    handleBatchedCancel() {
      this.batched = false;
      this.batchedList = [];
    },
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
