<template>
  <UI :list="noticeList" @iconCheck="handelCheck" @delete="handelDelete" @ready="handelReady">
    <slot />
  </UI>
</template>

<script>
import { MessageBox } from 'element-ui';
import { mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI, MessageBox },
  data() {
    return {
      noticeList: [],
      checkList: {}
    };
  },
  created() {
    this.getNotice();
  },
  methods: {
    ...mapActions('project', [
      'getProjectNotice',
      'updateProjectNotice',
      'deleteProjectNotice'
    ]),
    handelCheck(item) {
      item.checked = !item.checked;
      if (item.checked) {
        this.checkList[item.id] = item;
      } else {
        delete this.checkList[item.id];
      }
    },
    getNotice() {
      this.getProjectNotice().then(data => {
        data.map(item => (item.checked = false));
        this.noticeList = data;
      });
    },
    handelDelete() {
      let messageIds = this.getSelectIds();
      if (!messageIds.length) {
        window.$Message.error('未选择数据');
        return false;
      }
      MessageBox.confirm('确认删除？')
        .then(_ => {
          this.deleteProjectNotice({ messageIds }).then(data => {
            window.$Message.success('删除成功');
            this.getNotice();
          });
        })
        .catch(_ => {});
    },
    handelReady() {
      let messageIds = this.getSelectIds();
      if (!messageIds.length) {
        window.$Message.error('未选择数据');
        return false;
      }
      this.updateProjectNotice({ messageIds }).then(data => {
        window.$Message.success('标记成功');
        this.getNotice();
      });
    },
    getSelectIds() {
      let checkData = Object.values(this.checkList);
      const selectIds = [];
      for (let i = 0; i < checkData.length; i++) {
        selectIds.push(checkData[i].id);
      }
      return selectIds;
    },
  },
};
</script>
