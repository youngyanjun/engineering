<template>
  <shortLayout :routeName="'Person'">
    <div :class="$style.content">
      <div :class="[$style.message_set,$style.alltext]">
        <div
          :class="$style.userSetPic"
          style="background: rgb(106, 175, 255); text-align: center; line-height: 80px; border-radius: 50%;"
        >
          <span style="color:#FFFFFF;font-size: 35px ">{{userInfo.sysUser.userName[0]}}</span>
        </div>
        <div :class="$style.userSetName">{{userInfo.sysUser.userName}}</div>
        <div :class="$style.userSet_role" v-if="userInfo.sysRoles?true:false">
          <div v-for="item in userInfo.sysRoles" v-bind:key="item.id">{{item.roleName}}</div>
        </div>
      </div>

      <div :class="[$style.personal_con,$style.alltext]">
        <div :class="$style.label_top">
          <ul>
            <li :class="$style.lab_active">
              个人资料
              <div :class="$style.lab_line"></div>
            </li>
          </ul>
          <div :class="[$style.edit_btns,{ 'is-unshow': unEdit}]">
            <Button class="cancel"  @click="$emit('change')">取消</Button>
            <Button class="submit" style="margin-left:16px;" type="default" @click="$emit('submit', form)">保存</Button>
          </div>
        </div>
        <div :class="$style.lab_con_all">
          <div :class="$style.personal_text">
            <div :class="$style.personal_item">
              <p>头像</p>
              <div :class="$style.personal_value">
                <div :class="$style.personal_image">
                  <div :class="$style.image">
                    <span style="color:#FFFFFF;font-size: 35px ">{{userInfo.sysUser.userName[0]}}</span>
                  </div>
                </div>

                <!-- <div :class="$style.personal_image">
                                        <img :class="$style.image" src="http://iot.zcloudbim.com:8006/projectfile/OU9VT8CQJV6UR5LVZCZDVDYPQDIH4T2V.png" alt="头像"/>
                                        <span :class="$style.edit_image">修改头像</span>
                </div>-->
              </div>
            </div>
            <div :class="$style.personal_item">
              <p>姓名</p>
              <div :class="$style.personal_value">
                 <input v-model="form.userName" v-if="!unEdit" />
                <span v-if="unEdit">{{form.userName}}</span>
                <!-- <span>{{userInfo.sysUser.userName}}</span> -->
              </div>
            </div>
            <div :class="$style.personal_item">
              <p>邮箱</p>
              <div :class="$style.personal_value">
                <input v-model="form.email" v-if="!unEdit" />
                <span v-if="unEdit">{{form.email}}</span>
              </div>
            </div>
            <div :class="$style.personal_item">
              <p>手机</p>
              <div :class="$style.personal_value">
                <span>{{userInfo.sysUser.phone}}</span>
              </div>
            </div>
            <div :class="$style.personal_item">
              <p>密码</p>
              <div :class="$style.personal_value">
                <input v-model="form.userPwd" v-if="!unEdit"  autocomplete="off" type="password"/>
                <span v-if="unEdit" :class="$style.resetBtn" @click="$emit('change')">更改密码</span>
              </div>
            </div>
            <div :class="[$style.personal_item, { 'is-unshow': unEdit}]">
              <p>新密码</p>
              <div :class="$style.personal_value">
                <input v-model="form.newUserPwd"  autocomplete="off" type="password"/>
              </div>
            </div>
            <div :class="[$style.personal_item, { 'is-unshow': unEdit}]">
              <p>确认密码</p>
              <div :class="$style.personal_value">
                <input v-model="form.againPsw" autocomplete="off" type="password"/>
              </div>
            </div>

            <div :class="$style.personal_item">
              <p>公司</p>
              <div :class="$style.personal_value">
                <span>精工钢构</span>
              </div>
            </div>
            <div :class="[$style.affirm_password,$style.personal_item]" v-if="userInfo.organization?true:false">
              <p>组织</p>
              <div :class="$style.personal_value" >
                <span>{{userInfo.organization?userInfo.organization.name:""}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </shortLayout>
</template>

<script>
import shortLayout from '@/modules/app/short_layout/index';
import { Button } from '@/components/ls';

export default {
  name: '',
  components: { shortLayout, Button },
  props: {
    userInfo: {
      type: Object,
    },
    unEdit: {
      type: Boolean,
    },
  },
  data() {
    return {
      form: {
        userName: '',
        email: '',
        userPwd: '',
        newUserPwd: '',
        againPsw: '',
      },
    };
  },
  created() {
    if (this.userInfo.sysUser) {
      this.form.email = this.userInfo.sysUser.email;
      this.form.userName = this.userInfo.sysUser.userName;
    }
  },
  watch: {
    userInfo(val) {
      this.form.email = val.sysUser.email;
      this.form.userName = val.sysUser.userName;
    },
  },
};
</script>

<style lang="scss" module>
@import "./ui.scss";
</style>
