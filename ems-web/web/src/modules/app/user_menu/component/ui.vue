<template>
  <div :class="$style.content" @blur="userPoped = false" tabindex="1">
    <!-- <img
      :class="$style.portrait"
      src="~@/assets/images/header_default.png"
      alt="头像"
      @click="handleProtraitClick"
      @click.stop="userPoped=true"
    />-->

    <span
      :class="$style.user_pic"
      @click="handleProtraitClick"
      @click.stop="userPoped=true"
    >{{$store.state.userInfo.userName?$store.state.userInfo.userName[0]:""}}</span>

    <div :class="$style['user-pop']" v-show="userPoped" @click.stop="userPoped=true">
      <div :class="$style.user_top">
        <div :class="$style.user_pic">
          <span style="color: #FFFFFF;font-size: 25px">{{$store.state.userInfo.userName?$store.state.userInfo.userName[0]:""}}</span>
        </div>
        <div :class="$style.user_info">
          <p :class="$style.user_name">{{$store.state.userInfo.userName}}</p>
          <!-- <p :class="$style.user_role">超级管理员</p> -->
        </div>
      </div>
      <div :class="$style.carve_line"></div>
      <div :class="$style.personal_data" @click="handleUserInfo">
        <i :class="$style.userIcon"></i>
        <span>个人资料</span>
      </div>
      <div :class="$style.personal_data" @click="handleLogout">
        <i :class="$style.exitIcon"></i>
        <span>退出登录</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: '',
  data() {
    return {
      userPoped: false,
    };
  },
  created() {
  },
  methods: {
    handleProtraitClick() {
      this.userPoped = !this.userPoped;
    },
    handleLogout() {
      this.$router.push({ name: 'Login' });
    },
    handleUserInfo() {
      this.$router.push({ name: 'Person' });
    }
  }
};
</script>

<style lang="scss" module>
@import "./ui.scss";
</style>
