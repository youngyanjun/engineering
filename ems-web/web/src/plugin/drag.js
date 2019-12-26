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
  },
  bind(el, binding, vnode) {
    let y, isDragging;
    el.addEventListener('mousedown', (ev) => {
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
      if (isDragging === true) {
        computed(el, binding);
        isDragging = false;
      }
    });
    el.addEventListener('mouseleave', (ev) => {
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
