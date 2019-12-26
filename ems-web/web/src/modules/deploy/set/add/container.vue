<template>
  <UI
    :visible="visible"
    :form="form"
    :statusOptions="departmentStageOptions"
    :leaderOptions="users"
    @submit="handleSubmit"
    @cancel="handleCancel"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  props: {
    orgId: String,
  },
  data() {
    return {
      visible: false,
      form: {},
      users: [],
    };
  },
  computed: {
    ...mapState('deploy', [
      'departmentStageOptions',
    ]),
  },
  methods: {
    ...mapActions('deployStructurePerson', [
      'getListAll',
    ]),
    ...mapActions('deploy', [
      'setEngineeringStage',
    ]),
    open(option) {
      this.form = Object.assign({}, this.form, option);
      this.visible = true;
      this.getListAll(this.orgId).then(data => {
        this.users = (data || []).map(item => ({
          ...item,
          id: item.userId,
          name: item.userName,
        }));
      });
    },
    close() {
      this.visible = false;
    },
    handleSubmit(data) {
      console.log('submit', data);
      this.setEngineeringStage({
        id: data.id,
        engineeringStage: data.status,
        personInCharge: data.leader,
      }).then(() => {
        window.$Message.success('提交成功');
        this.$emit('refresh');
        this.visible = false;
      });
    },
    handleCancel() {
      this.close();
    },
  },
};
</script>
