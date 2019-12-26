<template>
  <div class="ls-upload">
    <i class="ls-upload-icon">+</i>
    <i class="ls-upload-loading" v-if="isLoading" />
    <input class="ls-upload-input" type="file" @input="handleInput" :accept="accept" />
  </div>
</template>

<script>
export default {
  name: 'LsUpload',
  props: {
    accept: {
      type: String,
      default: 'image/*',
    },
  },
  data() {
    return {
      isLoading: false,
    };
  },
  methods: {
    handleInput(ev) {
      console.log(ev, ev.target.files[0]);
      this.isLoading = true;
      this.$emit('change', ev.target.files[0]);
    },

  },
};
</script>

<style lang="scss">
@import '../style/upload.scss';
</style>
