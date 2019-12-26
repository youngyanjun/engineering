<template>
  <UI
      @submit="resetInfo"
      @change="changeEdit"
      :unEdit="unEdit"
      :userInfo="userInfoMore">
    <slot />
  </UI>
</template>

<script>
import { mapActions } from 'vuex';
import UI from './component/ui';


export default {
  name: '',
  components: { UI },
  data() {
    return {
      unEdit: true,
      userInfoMore: {
        sysUser: {},
      },
    };
  },
  created() {
    this.getProjectUserInfo(this.$store.state.project.project.projectId).then(res => {
      this.userInfoMore = res;
    });
  },
  methods: {
    ...mapActions('project', [
      'getProjectUserInfo',
      'setProjectUserInfo',
    ]),
    resetInfo({ userName, email, userPwd, newUserPwd, againPsw }) {
      if (newUserPwd != againPsw) {
        window.$Message.error('密码不一致！');
        return false;
      }
      this.setProjectUserInfo({ userName, email, userPwd, newUserPwd }).then(data => {

      });
      this.unEdit = true;
    },
    changeEdit() {
      this.unEdit = !this.unEdit;
    },
  },
};
</script>
