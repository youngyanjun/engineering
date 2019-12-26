<template>
  <section :class="$style.content">
    <p><span :class="$style.title">报表设置</span></p>
    <p>1.设置项目所含单体</p>
    <p>2.设置各单体是否必须上传照片</p>
    <div :class="$style.set">
      <div :class="$style.project">
        <div :class="$style['project-hd']">
          <span :class="$style['project-title']">项目列表</span>
          <Input
            :class="$style['project-search']"
            placeholder="请输入项目名称..."
            :suffix="true"
            v-model="keyword"
            @suffixClick="$emit('searchSubmit', keyword)"
          />
        </div>
        <div :class="$style['project-bd']">
          <Radio
            :label="project.id"
            :value="currentProjectId"
            v-for="project in projects"
            :key="project.id"
            @input="$emit('projectChange', project)"
          >{{ project.name }}</Radio>
        </div>
      </div>
      <div :class="$style.singlebody">
        <div :class="$style['singlebody-hd']">
          <span :class="$style['singlebody-title']">单体列表</span>
          <span :class="$style['singlebody-photograph']">必须拍照</span>
        </div>
        <div :class="$style['singlebody-bd']">
          <div :class="$style['drag-stage']">
            <p
              :class="$style['drag-item']"
              v-for="(item, index) in innerSingleBodys"
              v-drag="{
                draggend: handleDrag,
                data: index,
              }"
            >
              <i :class="$style['drag-prex']" />
              <Input
                :value="item.label"
                @input="innerSingleBodys[index].label = arguments[0]"
                @blur="handleBlur.call(this, item)"
              />
              <i :class="$style['drag-suffix']" @click="handleRemove.call(this, index)" />
              <ls-switch v-model="item.checked" :label="item.id" @change="$emit('checkedChange', item)" />
            </p>
            <p>
              <Button :class="$style['drag-add']" @click="handleAdd">新建单体</Button>
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { Input, Radio, Switch, Button } from '@/components/ls';

export default {
  name: '',
  components: { Input, Radio, LsSwitch: Switch, Button },
  props: {
    projects: {
      type: Array,
      default: () => [],
    },
    singleBodys: {
      type: Array,
      default: () => [],
    },
    currentProjectId: {
      type: String,
      default: '',
    },
  },
  data() {
    return {
      keyword: '',
      innerSingleBodys: this.singleBodys,
    };
  },
  watch: {
    singleBodys(val) {
      this.innerSingleBodys = val;
    },
  },
  methods: {
    handleDrag(oldIndex, newIndex) {
      if (oldIndex === newIndex) {
        return;
      }
      console.log('singlebody => drag', oldIndex, newIndex);
      const temp = this.innerSingleBodys[oldIndex];
      newIndex = Math.max(0, Math.min(newIndex, this.innerSingleBodys.length));
      if (newIndex > oldIndex) {
        this.innerSingleBodys.splice(newIndex, 0, temp);
        this.innerSingleBodys.splice(oldIndex, 1);
      } else {
        this.innerSingleBodys.splice(oldIndex, 1);
        this.innerSingleBodys.splice(newIndex, 0, temp);
      }
      this.$emit('orderSet', this.innerSingleBodys);
    },
    handleAdd() {
      this.innerSingleBodys.push({
        id: '',
        label: '',
        checked: false,
      });
    },
    handleRemove(index) {
      if (window.confirm('确认要删除吗?')) {
        const data = this.innerSingleBodys[index];
        this.innerSingleBodys.splice(index, 1);
        this.$emit('remove', data);
      }
    },
    handleBlur(data) {
      if (data.id) {
        this.$emit('set', data);
      } else {
        this.$emit('add', data);
      }
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
