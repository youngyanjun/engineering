<template>
  <router-view v-if="$route.meta && $route.meta.layout === false" />
  <Layout v-else>
    <router-view />
  </Layout>
</template>

<script>
import Layout from './layout';

export default {
  name: 'App',
  components: { Layout },
  data() {
    return {

    };
  },
  created() {
  },
  methods: {
  },
};
</script>
