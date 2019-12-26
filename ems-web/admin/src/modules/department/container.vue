<template>
  <UI>
    <Structure slot="structure" />
    <Permission slot="permission" />
  </UI>
</template>

<script>
import UI from './component/ui';
import Permission from './permission';
import Structure from './structure';

export default {
  name: '',
  components: { UI, Permission, Structure },
  data() {
    return {

    };
  },
};
</script>
