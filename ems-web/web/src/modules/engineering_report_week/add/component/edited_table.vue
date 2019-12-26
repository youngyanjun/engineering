<template>
  <div class="edited-table">
    <div class="edited-table-hd">
      <div class="edited-table-tr">
        <div class="edited-table-th edited-table-select" v-if="selected">
          <i
            :class="[
              'edited-table-checkbox', {
              'is-checked': selection.length === innerTbody.length
            }]"
            @click="handleSelectAll" />
        </div>
        <div class="edited-table-th" :style="{ 'max-width': '10%' }" v-if="rowEditabled">编号</div>
        <div
          :class="['edited-table-th', { 'is-center': item.align }]"
          v-for="item in thead"
          :style="{ 'max-width': item.width, 'min-width': item.minWidth }"
        >{{ item.name }}</div>
      </div>
    </div>
    <div class="edited-table-bd">
      <template v-if="innerTbody.length">
        <div class="edited-table-tr" v-for="(item, index) in innerTbody">
          <div class="edited-table-td is-split" :style="{ 'max-width': '10%' }" v-if="rowEditabled">
            <span class="edited-table-td-split">
              <i class="edited-table-stop" @click="handleDelete.call(this, index)" />
            </span>
            <span class="edited-table-td-split">{{ index + 1 }}</span>
          </div>
          <div class="edited-table-td edited-table-select" v-if="selected">
            <i
              :class="[
                'edited-table-checkbox', {
                'is-checked': selection.includes(index)
              }]"
              @click="handleSelectSingle.call(this, index)"
            />
          </div>
          <div
            :class="['edited-table-td', { 'is-center': align }, { 'is-split': Object.prototype.toString.call(item[key]) === '[object Array]' }]"
            v-for="{ key, slot, align, width, filter, type, readonly, minWidth } in thead"
            :style="{ 'max-width': width, 'min-width': minWidth }"
          >
            <template v-if="Object.prototype.toString.call(item[key]) === '[object Array]'">
              <div class="edited-table-tr"
                v-for="(subitem, i) in item[key]"
              >
                <div
                  :class="['edited-table-td']"
                >
                  <Input v-if="true || !$slots" v-model="filter ? filter(item[key][i]) : item[key][i]" :readonly="treadonly" />
                  <slot v-else :name="`${key}-${i}`" :data="item[key][i]"></slot>
                </div>
              </div>
            </template>
            <template v-else>
              <DatePicker
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                v-if="type === 'date'" v-model="filter ? filter(item[key]) : item[key]"
                :readonly="treadonly || readonly"
              />
              <Input v-else-if="!slot || !$slots[key]" v-model="filter ? filter(item[key]) : item[key]" :readonly="treadonly || readonly" />
              <slot v-else :name="key" :data="item"></slot>
            </template>
          </div>
        </div>
      </template>
      <div class="edited-table-tr"  v-if="rowEditabled">
        <Button type="text" size="normal" @click="handleAdd"><i>+</i>&nbsp;新增行</Button>
      </div>
    </div>
  </div>
</template>

<script>
import { DatePicker } from 'element-ui';
import { Button, Input, Divider } from '@/components/ls';

export default {
  name: 'app',
  components: { Button, Input, Divider, DatePicker },
  props: {
    thead: {
      type: Array,
      default: () => [],
    },
    tbody: {
      type: Array,
      default: () => [],
    },
    selected: {
      type: Boolean,
      default: false,
    },
    rowEditabled: {
      type: Boolean,
      default: true,
    },
    treadonly: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      selection: [],
      innerTbody: [],
    };
  },
  created() {
    this.innerTbody = this.tbody;
  },
  watch: {
    tbody(val) {
      this.innerTbody = val;
      this.selection = [];
    },
    selection(newVal) {
      this.$emit('selectionChange', newVal);
    },
  },
  methods: {
    handleSelectAll() {
      const { innerTbody } = this;
      if (this.selection.length !== innerTbody.length) {
        this.selection = innerTbody.map((item, index) => index);
      } else {
        this.selection = [];
      }
    },
    handleSelectSingle(index) {
      const { selection } = this;
      let isExist = false;
      selection.forEach((item, i) => {
        if (item === index) {
          isExist = true;
          selection.splice(i, 1);
        }
      });
      if (!isExist) {
        selection.push(index);
      }
    },
    handleAdd() {
      this.innerTbody.push({});
    },
    handleDelete(index) {
      this.innerTbody.splice(index, 1);
    },
  },
};
</script>

<style lang="scss">
@import './edited_table.scss';
</style>
