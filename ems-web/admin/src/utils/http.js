import 'whatwg-fetch';

class Http {
  // 拦截器
  static interceptors = {
    request: {
      queue: [],
      use(callback) {
        this.queue.push(callback);
      },
    },
    response: {
      queue: [],
      use(callback) {
        this.queue.push(callback);
      },
    },
  };

  static fetch(request) {
    const { method, headers } = request;
    const opts = {
      method,
      headers,
      credentials: 'include',
    };

    if (method === 'GET') {
      opts.body = null;
    } else if (headers['Content-Type'] === 'application/json') {
      opts.body = JSON.stringify(request.params);
    } else {
      opts.body = request.params;
    }

    // 请求前拦截处理
    this.interceptors.request.queue.forEach(interceptors => {
      request = interceptors(request);
    });

    // 请求
    return new Promise((resolve, reject) => {
      fetch(request.url, opts).then(res => {
        if (!res.ok) {
          throw new Error(JSON.stringify({
            code: res.status,
            message: res.statusText,
          }));
        } else {
          return res.json();
        }
      }).then(result => {
        let response = {
          result,
        };
        // 请求成功后拦截处理
        this.interceptors.response.queue.forEach(interceptors => {
          response = interceptors(response);
        });

        resolve(response.result);
      }).catch(err => {
        let response = {
          err,
        };
        // 请求失败后拦截处理
        this.interceptors.response.queue.forEach(interceptors => {
          response = interceptors(response);
        });
        reject(response.err);
      });
    });
  }

  static get(...args) {
    const [
      nativeUrl = '',
      params = {},
      headers = {},
    ] = args;

    // 参数转换
    let paramsStr = '';
    let url = nativeUrl;
    Object.keys(params).forEach((key) => {
      paramsStr = `${paramsStr + key}=${params[key]}&`;
    });

    if (paramsStr !== '') {
      paramsStr = paramsStr.substr(0, paramsStr.lastIndexOf('&'));
      url = `${url}?${paramsStr}`;
    }

    return this.fetch({
      method: 'GET',
      url,
      params,
      headers,
    });
  }

  static post(...args) {
    const [
      url = '',
      params = {},
      headers = {
        'Content-Type': 'application/json',
      },
    ] = args;

    return this.fetch({
      method: 'POST',
      url,
      params,
      headers,
    });
  }
}

export default Http;
