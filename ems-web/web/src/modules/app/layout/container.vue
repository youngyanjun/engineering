<template>
  <UI
    :routeName="routeName"
    :menuList="menuList"
    :messageNum="unReadMessageNum"
    @menuItemClick="handleMenuItemClick"
    @logout="handleLogout"
    :projectName="$store.state.project.project.projectName"
    @toUserInfo='handleUserInfo'
    @uploadFile='handleUploadFile'
    @removeFile='handleRemoveFile'
    @handleSubmit='handleSubmitFeed'
    @backHome='handleLogo'
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
      fileList: {},
      unReadMessageNum: '',
    };
  },
  computed: {
    menuList() {
      this.getUnReadNotice().then(data => {
        this.unReadMessageNum = data.toString();
      });
      return [
        {
          label: '部门报表',
          value: 'DepartmentReportList',
          icon: '',
        },
        {
          label: '工程报表',
          value: 'EngineeringReportList',
          icon: '',
        },
        {
          label: '工程报表汇总',
          value: 'ReportSummary',
          icon: '',
        },
        {
          label: '产值和收付款汇总',
          value: 'OutputPaymentSummary',
          icon: '',
        },
        {
          label: '组织配置',
          value: 'Deploy',
          icon: '',
        },
      ];
    },
    routeName() {
      if (/Deploy/.test(this.$route.name)) {
        return 'Deploy';
      } else if (/ReportSummary/.test(this.$route.name)) {
        return 'ReportSummary';
      } else {
        return this.$route.name;
      }
    },
  },
  methods: {
    ...mapActions([
      'uploadFile',
      'setFeedBack',
    ]),
    ...mapActions('project', [
      'getUnReadNotice',
    ]),
    handleMenuItemClick(routeName) {
      this.$router.push({ name: routeName });
    },
    handleLogout() {
      this.$router.push({ name: 'Login' });
    },
    handleLogo() {
      this.$router.push({ name: 'ProjectList' });
    },
    handleUserInfo() {
      this.$router.push({ name: 'Person' });
    },
    handleUploadFile(fileObj) {
      this.uploadFile(fileObj.file).then(data => {
        console.log(JSON.stringify(this.fileList));
        this.fileList[fileObj.file.uid] = data[0];
      });
    },
    handleRemoveFile(fileId) {
      delete this.fileList[fileId];
    },
    handleSubmitFeed(data) {
      data.sysFileVOS = Object.values(this.fileList);

      this.setFeedBack(data).then(() => {
        window.$Message.success('反馈成功');
      });
    },
  },
};
</script>
