<template>
  <!-- 必填字段未确认 -->
  <div :class="$style.content">
    <div :class="$style.aside">
      <div :class="$style.form">
        <div :class="[$style.group, 'is-require']">
          <label :class="$style['group-label']">项目名称</label>
          <Input :class="$style['group-value']" v-model="innerForm.engineeringName" :error="error.engineeringName.msg" />
        </div>
        <div :class="[$style.group, 'is-require']">
          <label :class="$style['group-label']">项目简称</label>
          <Input :class="$style['group-value']" v-model="innerForm.abbreviation" :error="error.abbreviation.msg" />
        </div>
        <div :class="[$style.group, 'is-require']">
          <label :class="$style['group-label']">项目位置</label>
          <Input
            :class="[$style.position, $style['group-value']]"
            placeholder="点击地图选择项目位置"
            :value="first ? '' : `${innerForm.address} ${innerForm.longitude} ${innerForm.latitude}`"
            suffix
            readonly
          />
        </div>
        <div :class="$style.group">
          <label :class="$style['group-label']">项目经理</label>
          <Input :class="$style['group-value']" v-model="innerForm.projecLeader" />
        </div>
        <div :class="$style.group">
          <label :class="$style['group-label']">技术负责人</label>
          <Input :class="$style['group-value']" v-model="innerForm.technicalLeader" />
        </div>
        <div :class="$style.group">
          <label :class="$style['group-label']">安全员</label>
          <Input :class="$style['group-value']" v-model="innerForm.safeMan" />
        </div>
        <div :class="$style.group">
          <label :class="$style['group-label']">建设单位</label>
          <Input :class="$style['group-value']" v-model="innerForm.constructionUnit" />
        </div>
        <div :class="$style.group">
          <label :class="$style['group-label']">监理单位</label>
          <Input :class="$style['group-value']" v-model="innerForm.qualityInspectionUnit" />
        </div>
        <div :class="$style.group">
          <label :class="$style['group-label']">质监站</label>
          <Input :class="$style['group-value']" v-model="innerForm.qualityInspectionStation" />
        </div>
        <div :class="$style.group">
          <label :class="$style['group-label']"></label>
          <span :class="$style.btns">
            <Button type="default" @click="$emit('submit', innerForm)">确定</Button>
            <Button @click="$emit('cancel')">取消</Button>
          </span>
        </div>
      </div>
    </div>
    <div :class="$style.main">
      <Input :class="$style.search" v-model="keyword">
        <span slot="append" type="default" @click="handleSearch.call(this, keyword)">查询</span>
      </Input>
      <baidu-map
        :class="$style.map"
        :center="center"
        :zoom="12"
        :scrollWheelZoom="true"
        v-if="mapLoaded"
        @click="handleMapClick"
      >
        <bml-marker-clusterer :averageCenter="true">
          <bm-marker
            :position="{
              lng: innerForm.longitude,
              lat: innerForm.latitude,
            }"
            :icon="{url: require('@/assets/images/location-pro.png'), size: {width: 31, height: 36}}"
          />
        </bml-marker-clusterer>
      </baidu-map>
    </div>
  </div>
</template>

<script>
import { BmlMarkerClusterer } from 'vue-baidu-map';
import { Input, Button } from '@/components/ls';

export default {
  name: '',
  components: { Input, Button, BmlMarkerClusterer },
  props: {
    form: {
      type: Object,
      default: () => {},
    },
    error: {
      type: Object,
      default: () => {},
    },
    first: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      innerForm: {},
      mapLoaded: false,
      keyword: '',
      center: {
        lng: 0,
        lat: 0,
      },
      geoc: null,
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
    loadMap() {
      if (this.first) {
        // 默认绍兴
        this.center = {
          lng: 120.588927,
          lat: 30.033869,
        };
      } else {
        this.center = {
          lng: this.innerForm.longitude,
          lat: this.innerForm.latitude,
        };
      }
      this.mapLoaded = false;
      this.$nextTick(() => {
        this.mapLoaded = true;
      });
    },
    searchAddressByPoint(point) {
      let geoc = this.geoc;
      if (!geoc) {
        geoc = new window.BMap.Geocoder();
        this.geoc = geoc;
      }
      return new Promise(resolve => {
        geoc.getLocation(point, (ev) => {
          resolve(ev);
        });
      });
    },
    /**
     * 暂时不用
     */
    searchPointByAddress(address) {
      let geoc = this.geoc;
      if (!geoc) {
        geoc = new window.BMap.Geocoder();
        this.geoc = geoc;
      }
      return new Promise(resolve => {
        geoc.getPoint(address, (point) => {
          resolve(point);
        });
      });
    },
    handleMapClick(ev) {
      console.log('click', ev);
      this.innerForm.longitude = ev.point.lng;
      this.innerForm.latitude = ev.point.lat;
      this.searchAddressByPoint(ev.point).then(data => {
        console.log('address', ev.point.lng, ev.point.lat, data);
        this.innerForm.address = data.address;
      });
    },
    handleSearch(keyword) {
      this.center = keyword;
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
