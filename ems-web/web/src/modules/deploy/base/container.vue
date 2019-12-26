<template>
  <UI
    ref="ui"
    :form="baseInfo"
    :error="error"
    :longitude="baseInfo.longitude"
    :latitude="baseInfo.latitude"
    :first="isNullPosition"
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
    engineeringId: String,
  },
  data() {
    return {
      error: {
        engineeringName: {
          msg: '',
          verify: [{
            msg: '不能为空',
            test: v => v === '' || v === undefined,
          }],
        },
        abbreviation: {
          msg: '',
          verify: [{
            msg: '不能为空',
            test: v => v === '' || v === undefined,
          }],
        },
        longitude: {
          msg: '',
          verify: [{
            msg: '不能为空',
            test: v => v === '' || v === undefined,
          }],
        },
      },
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('deploy', [
      'baseInfo',
    ]),
    isNullPosition() {
      return !(this.baseInfo.longitude * this.baseInfo.latitude);
    },
  },
  methods: {
    ...mapActions('deploy', [
      'getBaseInfo',
      'setBaseInfo',
    ]),
    init() {
      this.getBaseInfo(this.engineeringId).then(() => {
        console.log('baseInfo', JSON.stringify(this.baseInfo));
        this.$refs.ui.loadMap();
      });
    },
    handleSubmit(data) {
      console.log('submit', data);
      if (this.FormVerify(data) !== true) {
        return;
      }
      this.setBaseInfo(data).then(() => {
        window.$Message.success('更新成功');
      });
    },
    handleCancel() {
      // TODO 重新获取数据
    },
  },
};
</script>
