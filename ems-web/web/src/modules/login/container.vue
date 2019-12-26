<template>
  <UI @submit="handleSubmit">
    <slot />
  </UI>
</template>

<script>
import { mapActions } from "vuex";
import UI from "./component/ui";

export default {
  name: "",
  components: { UI },
  data() {
    return {};
  },
  methods: {
    ...mapActions(["login", "setUserInfo"]),
    handleSubmit({ phone, password }) {
      this.login({ phone, password }).then(res => {
        this.setUserInfo({
          userName: res.userName,
          userType: res.userType
        });
        window.$Message.success("登录成功");
        this.$router.push({ name: "Home" });
      });
    }
  }
};
</script>
