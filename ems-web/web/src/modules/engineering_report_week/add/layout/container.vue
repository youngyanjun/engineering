<template>
  <UI
    :routeName="$route.name"
    :menuList="menuList"
    @menuItemClick="handleMenuItemClick"
    @preview="handlePreview"
    @submit="handleSubmit"
     @logout="handleLogout"
  >
    <UserMenu slot="user" />
    <slot />
  </UI>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import UserMenu from '@/modules/app/user_menu';

export default {
  name: '',
  components: { UI, UserMenu },
  props: {
    menuList: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {

    };
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'reportId',
    ]),
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'submitReport',
    ]),
    handleMenuItemClick(routeName) {
      this.$router.replace({ name: routeName });
    },
    handlePreview() {
      this.$emit('preview');
    },
    handleSubmit() {
      this.submitReport(this.reportId).then(() => {
        this.$router.push({ name: 'EngineeringReportList' });
      });
    },
    handleLogout() {
      this.$router.push({ name: 'Login' });
    },
  },
};
</script>
