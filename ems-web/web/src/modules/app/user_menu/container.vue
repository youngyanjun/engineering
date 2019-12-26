<template>
  <UI>
    <slot />
  </UI>
</template>

<script>
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  data() {
    return {

    };
  },
};
</script>
