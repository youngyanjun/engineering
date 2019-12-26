import { format } from '@/utils/date';

export default {
  install: (Vue) => {
    Vue.filter('dateFormat', format);
  },
};
