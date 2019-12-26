/**
 * 格式化时间
 * y 年
 * M 月
 * w 年份中的周数
 * W 月份中的周数
 * D 年份中的天数
 * d 月份中的天数
 * H 24小时制的小时数
 * h 12小时制的小时数
 * m 分钟
 * s 秒
 * S 毫秒
 */

export const format = (value, fmt) => {
  if (value === undefined || value === null) {
    // 异常值处理
    return '';
  } else if (typeof value === 'string') {
    // 兼容ios
    value = value.replace(/-/g, '/');
  }

  const date = new Date(value);
  const vals = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds(),
    'S': date.getMilliseconds()
  };

  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear().toString().slice(-RegExp.$1.length)));
  }

  for (let k in vals) {
    if (new RegExp('(' + k + ')').test(fmt)) {
      fmt = fmt.replace(RegExp.$1, ('0' + vals[k]).slice(-RegExp.$1.length));
    }
  }
  return fmt;
};

export default {
  format,
};
