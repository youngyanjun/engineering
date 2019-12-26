const singleDistance = 48;

const computed = (el, binding) => {
  const movedDistance = el.offsetTop;
  if (binding.value.draggend) {
    binding.value.draggend(binding.value.data, Math.ceil(movedDistance / singleDistance));
    setTimeout(() => {
      el.style.top = 'auto';
      el.style.zIndex = '0';
    });
  }
};

const drag = {
  inserted() {
    // console.log('drag => insert');
  },
  bind(el, binding, vnode) {
    // console.log('drag => bind', [el], binding, vnode);
    let y, isDragging;
    el.addEventListener('mousedown', (ev) => {
      // console.log(ev);
      y = ev.y;
      el.style.zIndex = '49';
      isDragging = true;
    });
    el.addEventListener('mousemove', (ev) => {
      if (isDragging === true) {
        el.style.top = (Number(el.style.top.replace('px', '')) || 0) + (ev.y - y) + 'px';
        y = ev.y;
      }
    });
    el.addEventListener('mouseup', (ev) => {
      // console.log('drag => mouseup');
      if (isDragging === true) {
        computed(el, binding);
        isDragging = false;
      }
    });
    el.addEventListener('mouseleave', (ev) => {
      // console.log('drag => mouseleave');
      if (isDragging === true) {
        computed(el, binding);
        isDragging = false;
      }
    });
  },
};

export default {
  install(Vue) {
    Vue.directive('drag', drag);
  },
};
