<template>
  <div>
    <UI
      :statusOptions="departmentStageOptions"
      :list="orgList"
      @itemClick="handleItemClick.call(this, arguments[0])"
    />
    <Add ref="add" @refresh="handleRefresh" :orgId="engineeringId" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';
import Add from './add';

export default {
  name: '',
  components: { UI, Add },
  props: {
    engineeringId: String,
  },
  data() {
    return {
      orgList: [],
    };
  },
  computed: {
    ...mapState('deploy', [
      'departmentStageOptions',
    ]),
  },
  created() {
    this.init();
  },
  methods: {
    ...mapActions('deploy', [
      'getEngineeringStage',
    ]),
    init() {
      this.getEngineeringStage().then(data => {
        this.orgList = (data || []).map(item => ({
          ...item,
          name: item.engineeringName,
          leader: item.userName,
          leaderId: item.userId,
          status: item.engineeringStage,
          createdDate: item.createDate,
        }));
      });
    },
    handleItemClick(data) {
      this.$refs.add.open(data);
    },
    handleRefresh() {
      this.init();
    },
  },
};
</script>
