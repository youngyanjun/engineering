<template>
  <UI 
  >
    <slot />
  </UI>
</template>

<script>
import UI from "./component/ui";

export default {
  name: "",
  props: {
    routeName: {
      type: String
    }
  },
  components: { UI },
  data() {
    return {};
  },
  created(){
      this.$router.push({ name: this.routeName });
  },
  computed: {},
  methods: {}
};
</script>
