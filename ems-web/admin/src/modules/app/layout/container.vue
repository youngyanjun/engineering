<template>
  <UI
    :routeName="$route.name"
    :userPoped="userPoped"
    @goto="handleGoto"
    @logout="handleLogout"
    @portraitClick="handlePortraitClick"
  >
    <slot />
  </UI>
</template>

<script>
import { mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: 'Layout',
  components: { UI },
  data() {
    return {
      userPoped: false,
    };
  },
  created() {
  },
  methods: {
    ...mapActions([
      'logout',
    ]),
    handleGoto(name) {
      this.$router.push({ name });
    },
    handleLogout() {
      this.logout();
      this.$router.push({ name: 'Login' });
    },
    handlePortraitClick() {
      this.userPoped = !this.userPoped;
    },
  },
};
</script>
