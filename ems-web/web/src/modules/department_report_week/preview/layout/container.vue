<template>
  <UI
    :editVisible="selfView"
    :submitVisible="selfView"
    @edit="handleEdit"
    @submit="handleSubmit"
  >
    <slot />
    <UserMenu slot="user" />
  </UI>
</template>

<script>
import UI from './component/ui';
import UserMenu from '@/modules/app/user_menu';

export default {
  name: '',
  components: { UI, UserMenu },
  props: {
    selfView: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    handleEdit() {
      this.$router.push({ name: 'DepartmentReportWeekAdd' });
    },
    handleSubmit() {
      this.$emit('submit');
    },
  },
};
</script>
