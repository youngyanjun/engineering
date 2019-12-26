<template>
  <UI @submit="handleSubmit">
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

    };
  },
  methods: {
    ...mapActions([
      'login',
    ]),
    handleSubmit({ phone, password }) {
      this.login({ phone, password }).then(() => {
        window.$Message.success('登录成功');
        this.$router.push({ name: 'Home' });
      });
    },
  },
};
</script>
