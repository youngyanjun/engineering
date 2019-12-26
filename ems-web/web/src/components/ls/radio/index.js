import UI from './ui';

UI.install = function (Vue) {
  Vue.component(UI.name, UI);
};

export default UI;
