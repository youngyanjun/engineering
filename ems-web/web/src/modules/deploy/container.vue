<template>
  <UI
    :options="options"
    :routeName="$route.name"
    @change="handleChange"
  >
    <router-view></router-view>
  </UI>
</template>

<script>
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  data() {
    return {
      engineering: {
        options: [
          {
            label: '组织架构',
            value: 'DeployStructure',
          },
          {
            label: '角色',
            value: 'DeployRole',
          },
          {
            label: '基本信息',
            value: 'DeployBase',
          },
        ],
      },
      department: {
        options: [
          {
            label: '组织架构',
            value: 'DeployStructure',
          },
          {
            label: '角色',
            value: 'DeployRole',
          },
          {
            label: '项目设置',
            value: 'DeploySet',
          },
        ],
      },
      leader: {
        options: [
          {
            label: '组织架构',
            value: 'DeployStructure',
          },
          {
            label: '角色',
            value: 'DeployRole',
          },
        ],
      },
    };
  },
  computed: {
    options() { // TODO 权限
      if (/8/.test(this.$route.params.orgType)) {
        return this.engineering.options;
      } else if (/[1-7]/.test(this.$route.params.orgType)) {
        return this.department.options;
      } else if (/null/.test(this.$route.params.orgType)) {
        return this.leader.options;
      }
    },
  },
  methods: {
    handleChange(name) {
      console.log('change', name);
      this.$router.push({ name });
    },
  },
};
</script>
