/**
 * @file 常用函数
 * @author: ace 2019/04/28
 */


/**
 * 配合REM设置文档字体大小
 *
 * @param {number} baseWidth 设计图宽度
 * @param {number} baseFontSize 比例，例如100，100px = 1rem
 * @return {number} 字体大小
 */
export const setDocumentFontSize = (baseWidth = 750, baseFontSize = 100) => {
  const setFontSize = () => {
    let rate = document.documentElement.clientWidth / baseWidth * baseFontSize;
    if (document.documentElement.clientWidth >= 640) {
      rate = 100;
    }
    document.documentElement.style.fontSize = `${rate}px`;
    return rate;
  };
  window.onresize = setFontSize;
  return setFontSize();
};

export const setViewPort = () => {
  const metaEl = document.createElement('meta');
  metaEl.setAttribute('name', 'viewport');
  metaEl.setAttribute('content',
    'width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, '
    + 'user-scalable=no, target-densitydpi=device-dpi, viewport-fit=cover');
  document.head.appendChild(metaEl);
};

export default {
  setDocumentFontSize,
  setViewPort,
};
