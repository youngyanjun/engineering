<template>
  <UI
    :visible="visible"
    :userOptions="list"
    @close="handleClose"
    @submit="handleSubmit"
    @keywordChange="handleKeywordChange"
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
      visible: false,
      id: 0,
      list: [],
      keyword: '',
    };
  },
  methods: {
    ...mapActions('deployStructurePerson', [
      'getDetail',
      'getAllUserList',
      'addUser',
    ]),
    init() {
      this.getAllUserList({
        structureId: this.id,
        keyword: this.keyword,
      }).then(data => {
        this.list = data;
      });
    },
    open(option) {
      this.id = option.id;
      this.visible = true;
      this.init();
    },
    close() {
      this.visible = false;
    },
    handleClose() {
      this.close();
    },
    handleKeywordChange(keyword) {
      this.keyword = keyword;
      this.init();
    },
    handleSubmit(data) {
      console.log('submit', data);
      this.addUser({
        structureId: this.id,
        userIds: data,
      }).then(() => {
        window.$Message.success('添加成功');
        this.$emit('refresh');
        this.close();
      });
    },
  },
};
</script>
