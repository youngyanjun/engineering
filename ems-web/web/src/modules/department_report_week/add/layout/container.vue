<template>
  <UI
    @preview="handlePreview"
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
  methods: {
    handlePreview() {
      this.$emit('preview');
    },
    handleSubmit() {
      this.$emit('submit');
    },
  },
};
</script>
