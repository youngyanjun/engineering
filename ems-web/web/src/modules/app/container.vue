<template>
  <router-view v-if="$route.meta && $route.meta.layout === false" />
  <Layout v-else>
    <router-view />
  </Layout>
</template>

<script>
import { mapActions } from 'vuex';
import Layout from './layout';

export default {
  name: 'App',
  components: { Layout },
  data() {
    return {

    };
  },
  created() {
    // const ENGINEERING_STORE = 'Engineering_STORE@v0.15';

    // // 加载本地store
    // const state = window.localStorage.getItem(ENGINEERING_STORE) || '';
    // if (state) {
    //   console.log(state);
    //   this.setState(JSON.parse(state));
    // }
    // // 暂存store
    // window.onbeforeunload = () => {
    //   window.localStorage.setItem(ENGINEERING_STORE, JSON.stringify(this.$store.state));
    // };
  },
  methods: {
    ...mapActions([
      'setState',
    ]),
  },
};
</script>
