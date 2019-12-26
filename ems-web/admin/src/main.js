'use strict';

import Vue from 'vue';
import '@/styles/common.scss';
import App from '@/modules/app';
import store from '@/store';
import router from '@/router';
import { setViewPort, setDocumentFontSize, setFavicon } from '@/utils';
import Http from '@/utils/http';
import Drag from '@/plugin/drag';
import Mixin from '@/plugin/mixin';
import { Message } from '@/components/ls';

document.title = '量树之云工程';

console.log(process.env);

const faviconImg = require('./assets/images/favicon.ico');

setViewPort();
setFavicon(faviconImg);
window.$rem = setDocumentFontSize(750, 100);
window.$Message = Message;

Vue.use(Drag);
Vue.use(Mixin);

Http.interceptors.request.use(request => {
  // console.log(request);
  return request;
});

Http.interceptors.response.use(response => {
  // 服务异常
  if (response.err) {
    const msg = JSON.parse(response.err.message);
    console.log(msg.message);
    window.$Message.error(msg.message);
    if (msg.code === 'SYS2002') {
      router.push({ name: 'Login' });
    }
    return response.err;
  } else if (response.result.status === 'SUCCESS') {
    // 请求成功
    return response.result;
  } else {
    // 业务异常
    throw new Error(JSON.stringify({
      code: response.result.errorCode,
      message: response.result.errorMsg,
    }));
  }
});

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  render: h => h('App'),
});
