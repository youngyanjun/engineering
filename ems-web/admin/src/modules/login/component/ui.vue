<template>
  <div :class="$style.content">
    <div :class="$style.hd">
      <div :class="$style['hd-content']">
        <img :class="$style.logo" src="~@/assets/images/logo_bimforestzcloud.png" alt="logo" />
      </div>
    </div>
    <div :class="$style.bd">
      <div :class="$style.form">
        <div :class="$style['form-aside']"></div>
        <div :class="$style['form-main']">
          <div :class="$style.title">量树之云工程管理后台</div>
          <div :class="$style.subtitle">BIM Forest ZCloud Construction</div>
          <div :class="$style['form-group']">
            <div :class="$style['form-label']">手机/邮箱</div>
            <div><Input :class="$style['form-value']" v-model="form.phone" /></div>
          </div>
          <div :class="$style['form-group']">
            <div :class="$style['form-label']">密码</div>
            <div><Input :class="$style['form-value']" type="password" v-model="form.password" /></div>
          </div>
          <div :class="$style['form-group']">
            <Button :class="$style.submit" type="default" @click="$emit('submit', form)">登录</Button>
          </div>
          <div :class="$style['form-group']">
            <label :class="$style.remember"><Checkbox v-model="remember" />记住登录</label>
          </div>
        </div>
      </div>
    </div>
    <div :class="$style.ft">
      <div :class="$style.friendlink">
        <span>量树之云</span>
        <span>资源下载</span>
        <span>帮助中心</span>
        <span>联系我们</span>
      </div>
      <div :class="$style.copyright">Copyright © BIM Forest</div>
    </div>
  </div>
</template>

<script>
import { Input, Checkbox, Button } from '@/components/ls';

export default {
  name: '',
  components: { Input, Button, Checkbox },
  data() {
    return {
      form: {
        phone: '',
        password: '',
      },
      remember: [],
    };
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
