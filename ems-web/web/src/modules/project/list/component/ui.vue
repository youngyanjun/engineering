<template>
  <shortLayout :routeName="'ProjectList'">
    <baidu-map :class="$style.map" center="绍兴" :scroll-wheel-zoom="true" :zoom="10">
      <bml-marker-clusterer :averageCenter="true">
        <bm-marker
          v-for="item in projectList"
          v-bind:key="item.id"
          :position="{lng: item.projectlng, lat: item.projectlat}"
          :icon="{url: require(`@/assets/images/${item.category?item.category:'location-pro'}.png`), size: {width: 31, height: 36}}"
        ></bm-marker>
      </bml-marker-clusterer>

      <bm-overlay
        v-for="item in projectList"
        v-bind:key="item.id"
        pane="labelPane"
        :lng=" item.projectlng"
        :lat="item.projectlat"
        :class="[$style.maplable,$style[item.category]]"
        @draw="draw"
      >
        <div @click="$emit('submit', {id:item.id,name:name})">
          <span>{{item.name}}</span>
          <div :class="$style['marker-arrow']"></div>
        </div>
      </bm-overlay>
    </baidu-map>

    <div :class="$style.prolist_all">
      <ul :class="$style.prolist">
        <li v-for="item in projectList" v-bind:key="item.id">
          <div :class="$style.pro_item" @click="$emit('submit', { ...item, id:item.id,name:item.name,})">
            <span  :class="[ $style[item.category],$style.pro_tag]">{{item.category | projectType}}</span>
            <div :class="$style.pro_name" v-bind:title="item.name">{{item.name}}</div>
            <span :class="$style.pro_address"><i :class="$style['icon-address']"></i>{{item.address}}</span>
          </div>
        </li>
      </ul>
    </div>
  </shortLayout>
</template>

<script>
import { BmlMarkerClusterer } from 'vue-baidu-map';
import shortLayout from '@/modules/app/short_layout/index';

export default {
  name: '',
  components: { BmlMarkerClusterer, shortLayout },
  props: {
    projectList: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      points: [],
      markers: [],
    };
  },
  mounted() {
    // this.drawPoints();
  },
  methods: {
    draw({ el, BMap, map }) {
      let lng = el.getAttribute('lng');
      let lat = el.getAttribute('lat');
      const pixel = map.pointToOverlayPixel(new BMap.Point(lng, lat));
      el.style.left = pixel.x + 28 + 'px';
      el.style.top = pixel.y - 22 + 'px';
    },
    drawPoints() {
      const points = [];
      for (let i = 0; i < this.projectList.length; i++) {
        let item = this.projectList[i];
        points.push({ lng: item.lng, lat: item.lat });
      }
      this.points = points;
    }
  }
};
</script>

<style lang="scss" module>
@import './ui.scss';
/* @import "./ui.scss"; */

</style>
