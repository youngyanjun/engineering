import './ui';

let messages = 0;
class Message {
  constructor(msg) {
    this.show(msg, 'info');
  }

  static show(msg, type = 'info') {
    const content = document.createElement('div');
    content.className = `ls-message is-${type}`;
    content.style.top = messages * 70 + 20 + 'px';
    content.innerHTML = `<span class="ls-message-inner">${msg}</span>`;
    document.body.appendChild(content);
    messages += 1;
    setTimeout(() => {
      content.style.top = 20 + 'px';
    });
    setTimeout(() => {
      document.body.removeChild(content);
      messages -= 1;
    }, 2000);
  }

  static info(msg) {
    this.show(msg, 'info');
  }

  static success(msg) {
    this.show(msg, 'success');
  }

  static warning(msg) {
    this.show(msg, 'warning');
  }

  static error(msg) {
    this.show(msg, 'error');
  }
}

export default Message;
