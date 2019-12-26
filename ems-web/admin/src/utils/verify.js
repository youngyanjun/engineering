export default {
  verify(data) {
    let pass = true;
    Object.keys(this.error).forEach(k => {
      this.error[k].verify.forEach(item => {
        this.error[k].msg = '';
        if (item.test(data[k]) === true) {
          this.error[k].msg = item.msg;
          pass = false;
        }
      });
    });
    return pass;
  }
};