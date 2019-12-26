<template>
  <UI
    :routeName="$route.name"
    :menuList="menuList"
    :editVisible="selfView"
    :submitVisible="selfView"
    @menuItemClick="handleMenuItemClick"
    @edit="handleEdit"
    @print="handlePrint"
    @export="handleExport"
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
    selfView: Boolean,
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
    handleEdit() {
      this.$router.push({ name: 'EngineeringReportWeekAdd' });
    },
    handleMenuItemClick(routeName) {
      this.$emit('menuItemClick', routeName);
    },
    handlePrint() {
      window.print();
    },
    handleExport() {
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
