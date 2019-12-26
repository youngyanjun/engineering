<template>
  <div :class="$style.content">
    <div :class="$style.main">
      <div :class="$style['main-hd']" style="background:#fff">
        <span :class="$style.left">
          <img
            :class="$style.logo"
            src="~@/assets/images/logo-cons.png"
            alt="logo"
            @click="handelToHome"
          />
        </span>
        <span :class="$style.other">
           <a title="帮助" target="#help" href="/pi/help20190219.pdf">
            <i :class="$style.help" />
          </a>
          <!-- <i :class="$style.feedback" title="反馈" @click="centerDialogVisible = true" /> -->
          <userMenu></userMenu>
        </span>
      </div>
      <div :class="$style['main-bd']">
        <slot />
      </div>
    </div>
  </div>
</template>

<script>
import userMenu from "@/modules/app/user_menu";

export default {
  name: "",
  props: {},
  components:{userMenu},
  data() {
    return {
      userPoped: false,
    };
  },
  methods: {
   
    handelToHome() {
      if (this.$store.state.project.project.projectId) {
        this.$router.push({
          path: `/report/engineering/${this.$store.state.project.project.projectId}/list`
        });
      } else {
          this.$router.push({ name: "Home" });
      }
    }
  }
};
</script>

<style lang="scss" module>
@import "./ui.scss";
</style>
