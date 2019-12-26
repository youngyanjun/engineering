<template>
  <div :class="$style.content">
    <div :class="$style.aside">
      <div :class="[$style.menu, { 'is-shrink': menuShrinked }]">
        <div :class="$style['menu-hd']">
          <span :class="$style['menu-title']">
            <img
              :class="$style.logo"
              src="~@/assets/images/logo_consinner.png"
              alt="logo"
              @click="$emit('backHome')"
            />
          </span>
          <i :class="$style['menu-expand']" v-if="menuShrinked" @click="menuShrinked = false" />
          <i :class="$style['menu-shrink']" v-else @click="menuShrinked = true" />
        </div>
        <div :class="$style['menu-bd']">
          <div
            :class="[$style['menu-item'], { 'is-active': routeName === item.value }]"
            v-for="item in menuList"
            :key="item.value"
          >
            <div :class="$style['menu-label']" @click="$emit('menuItemClick', item.value)">
              <i :class="$style['menu-icon']" />
              <span :class="$style['menu-name']">{{ item.label }}</span>
            </div>
            <div
              :class="[$style['menu-subitem'], { 'is-active': routeName === item.value }]"
              v-for="child in item.children"
              :key="child.value"
            >
              <div :class="$style['menu-label']" @click="$emit('menuItemClick', child.value)">
                <i :class="$style['menu-icon']" />
                <span :class="$style['menu-name']">{{ child.label }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div :class="$style.main">
      <div :class="$style['main-hd']">
        <span :class="$style.crumbs">{{projectName}} / <em>{{ menuName }}</em></span>
        <span :class="$style.other">
          <a title="帮助" target="#help" href="/pi/help20190219.pdf">
            <i :class="$style.help" />
          </a>
          <i :class="$style.feedback" title="反馈" @click="centerDialogVisible = true" />
          <span style="position:relative">
            <i :class="$style.notice" title="通知" @click="toNotice" />
            <span :class="$style.messageNum" v-if="messageNum?true:false">{{messageNum}}</span>
          </span>

          <userMenu></userMenu>
        </span>
      </div>
      <div :class="$style['main-bd']">
        <slot />
      </div>
    </div>

    <Dialog
      class="feedback"
      title="问题反馈"
      :visible="centerDialogVisible"
      width="640px"
      :before-close="handleClose"
    >
      <div :class="$style.feedbackbox">
        <textarea
          :class="$style.feedbackboxInput"
          maxlength="400"
          placeholder="我想反馈的问题是……"
          v-model="feedbackValue"
        ></textarea>
        <div :class="$style.feedbackWord">
          <span>{{feedbackValue.length}}</span>/400
        </div>
        <div :class="$style.feedbackImgsBox">
          <Upload
            action="#"
            multiple
            list-type="picture-card"
            ref="my-upload"
            class="feedback-uploader"
            :on-change="handlePictureCardPreview"
            :on-remove="handRemove"
            :auto-upload="false"
            :file-list="fileList"
          >
            <i class="el-icon-plus"></i>
          </Upload>
        </div>
        <div :class="$style.feedbackPhone">
          <span>联系方式：</span>
          <input type="text" maxlength="20" placeholder="选填" v-model="telphone" />
        </div>
        <div :class="$style.feedbackFoot">我们将保护您的隐私安全，仅供客服人员联系使用</div>
      </div>

      <span slot="footer" class="dialog-footer">
        <Button class="dialog-submit" style="margin-right:20px;" type="default" @click="submit">提交反馈</Button>
        <Button class="dialog-cancel" @click="handleClose">取消</Button>
      </span>
    </Dialog>
  </div>
</template>

<script>
import { Dialog, Upload } from "element-ui";
import { Button } from "@/components/ls";
import userMenu from "@/modules/app/user_menu";

export default {
  name: "",
  components: { Button, Dialog, Upload, userMenu },
  props: {
    menuList: {
      type: Array,
      default: () => []
    },
    projectName: {
      type: String
    },
    messageNum: {
      type: String
    },
    routeName: String,
    handleUploadFile: {
      type: Function
    }
  },
  data() {
    return {
      feedbackValue: "",
      feedbacklen: 0,
      centerDialogVisible: false,
      userPoped: false,
      menuShrinked: false,
      telphone: "",
      fileList: []
    };
  },
  computed: {
    menuName() {
      let menu;
      menu = this.menuList.find(item => item.value === this.routeName) || {};
      return menu.label;
    },
  },
  methods: {
    toNotice() {
      this.$router.push({ name: "Notice" });
    },
    handleProtraitClick() {
      this.imageUrl = [];
      this.userPoped = !this.userPoped;
    },
    handleClose() {
      this.centerDialogVisible = false;
      this.clearFiles();
    },
    submit() {
      this.$emit("handleSubmit", {
        contactInformation: this.telphone,
        content: this.feedbackValue
      });
      this.handleClose();
    },
    handRemove(file) {
      this.$emit("removeFile", file.uid);
    },
    handlePictureCardPreview(file, fileList) {
      const isJPG = file.type === "image/jpeg" || "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG或PNG 格式!");
        return false;
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
        return false;
      }

      this.$emit("uploadFile", { file: file.raw, flieList: fileList });
    },
    clearFiles() {
      this.$refs["my-upload"].clearFiles();
    }
  }
};
</script>

<style lang="scss" module>
@import "./ui.scss";
</style>
<style  lang="scss">
.feedback {
  .el-upload--picture-card,
  .el-upload-list--picture-card .el-upload-list__item {
    width: 100px;
    height: 100px;
  }
  .el-dialog__title {
    font-size: 16px;
  }
  .el-dialog__body {
    padding: 10px 20px;
  }
  .el-upload--picture-card {
    box-sizing: content-box;
    background: url("~@/assets/images/addImgs.png") no-repeat;
  }
  .el-icon-plus {
    display: none;
  }
  //进度条
  .el-upload-list--picture-card .el-progress {
    width: 80px;
  }
  .el-progress-circle {
    height: 80px !important;
    width: 80px !important;
  }
  .el-upload--picture-card {
    border: 1px solid #e6e7ec;
  }
}
</style>