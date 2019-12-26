import UI from './excel';

UI.install = function (Vue) {
  Vue.component(UI.name, UI);
};

export default UI;
