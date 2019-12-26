<template>
  <UI
    @submit="loadProject"
    :projectList="list"
  >
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
      list: [],
    };
  },
  created() {
    this.setProject({
      projectId: '',
      projectName: '',
    });
    this.getProjectList().then(data => {
      this.list = data;
    });
  },
  methods: {
    ...mapActions('project', [
      'getProjectList',
      'setProject',
    ]),

    loadProject({ id, name, category, type }) {
      let routeName;
      this.setProject({
        projectId: id,
        projectName: name,
      });
      if (category === 'dept') {
        routeName = 'department';
      } else if (category === 'engineering') {
        routeName = 'engineering';
      } else if (category === 'leader') {
        routeName = 'department';
      }
      console.log('goto', routeName);
      this.$router.push({
        path: `/report/${routeName}/${id}/${type}/list`,
      });
    },
  },
};
</script>
