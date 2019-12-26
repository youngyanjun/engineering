<template>
  <shortLayout :routeName="'Notice'">
    <div :class="$style.content">
      <div :class="[$style.message_set,$style.alltext]">
        <div
          :class="$style.userSetPic"
          style="background: rgb(106, 175, 255); text-align: center; line-height: 80px; border-radius: 50%;"
        >
          <span style="color:#FFFFFF;font-size: 35px " v-if="$store.state.userInfo.userName">{{$store.state.userInfo.userName[0]}}</span>
        </div>
        <div :class="$style.userSetName">{{$store.state.userInfo.userName}}</div>
      </div>

      <div :class="[$style.personal_con,$style.alltext]">
        <div :class="$style.label_top">
          <ul>
            <li :class="$style.lab_active">
              全部消息
              <div :class="$style.lab_line"></div>
            </li>
          </ul>
          <div :class="$style.edit_btns">
            <Button class="cancel" @click="$emit('delete')">批量删除</Button>
            <Button class="submit"  style="margin-left:16px;" @click="$emit('ready')">标记已读</Button>
          </div>
        </div>
        <div :class="$style.lab_con_all">
          <div :class="[$style.notice_item,{'only_ready':item.readFlag}]" v-for="item in list" :key="item.id" >
            <div :class="$style.notice_checkCol">
              <span
                :class="[$style.icon,{ 'is-check':!item.checked},{'is-checked':item.checked}]"
                @click="$emit('iconCheck',item)"
              ></span>
            </div>
            <div :class="$style.notice_contentCol">
              <div :class="$style.notice_content">{{item.content}}</div>
              <div :class="$style.notice_detail">
                <div :class="$style.notice_title">{{item.source}}</div>
                <span :class="$style.notice_time">{{item.createDate | filterTime}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </shortLayout>
</template>

<script>
import shortLayout from '@/modules/app/short_layout/index';
import { Button } from '@/components/ls';

export default {
  name: '',
  components: { shortLayout, Button },
  props: {
    list: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {

    };
  },
  created() {},
  methods: {

  },
  filters: {
    filterTime(value) {
      return value.slice(5, 16);
    },
  },
};
</script>

<style lang="scss" module>
@import "./ui.scss";
</style>
