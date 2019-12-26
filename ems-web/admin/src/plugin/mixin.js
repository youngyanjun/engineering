import { format as DateFormat } from '@/utils/date';

/**
 * 表单验证
 *  data: {
 *    managerName: '',
 *  },
 *  error: {
 *    managerName: {
 *      msg: '',
 *      verify: [{
 *        msg: '不能为空',
 *        test: v => v === '' || v === undefined,
 *      }],
 *    },
 *  },
 */
const FormVerify = function (data) {
  let pass = true;
  Object.keys(this.error).forEach(k => {
    this.error[k].verify.forEach(item => {
      this.error[k].msg = '';
      if (item.test(data[k]) === true) {
        this.error[k].msg = item.msg;
        pass = false;
      }
    });
  });
  return pass;
};

const FormVerifyClear = function (data) {
  let pass = true;
  Object.keys(this.error).forEach(k => {
    this.error[k].verify.forEach(item => {
      this.error[k].msg = '';
    });
  });
  return pass;
};

export default {
  install: (Vue) => {
    Vue.mixin({
      methods: {
        DateFormat,
        FormVerify,
        FormVerifyClear,
      },
    });
  },
};
