'use strict';

import Vue from 'vue';
import '@/styles/common.scss';
import App from '@/modules/app';
import store from '@/store';
import router from '@/router';
import Http from '@/utils/http';
import { setViewPort, setDocumentFontSize } from '@/utils';
import { Message } from '@/components/ls';
import DateFormat from '@/plugin/date';
import Mixin from '@/plugin/mixin';
import Drag from '@/plugin/drag';
import * as filters from '@/plugin/filters';

// 百度地图
import BaiduMap from 'vue-baidu-map';

Vue.use(BaiduMap, {
  ak: 'IhN3GUj44ojWlW96rEGemHHMj7GAI8s5',
});

// 全局过滤器
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key]);
});

setViewPort();
setDocumentFontSize(750, 100);
window.$Message = Message;
Vue.use(DateFormat);
Vue.use(Mixin);
Vue.use(Drag);

document.title = '之云工程';

Http.interceptors.request.use(request => {
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
console.log(process.env);

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  render: h => h('App'),
});
