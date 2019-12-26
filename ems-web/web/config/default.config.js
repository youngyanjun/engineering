module.exports = {
  base: {
    rootPath: 'dist_engineering', // 打包路径
    assetsPath: 'static/', // 资源相对路径
  },
  dev: {
    host: '0.0.0.0',
    port: '60010',
    proxy: {
      '/api': {
        target: 'http://10.11.0.196:8080',
        crossOrigin: true,
      },
      '/pi': {
        target: 'http://10.11.0.196:8080',
        crossOrigin: true,
      },
    },
    // 环境变量，会全局替换js中的process.env对象
    env: {
      NODE_ENV: '"development"',
      PACKAGE_ENV: '"dev"',
    },
  },
  prod: {
    publicPath: './', // 资源绝对路径
    env: {
      NODE_ENV: '"production"',
      PACKAGE_ENV: '"prod"',
    },
  },
  test: {
    publicPath: './', // 资源绝对路径
    env: {
      NODE_ENV: '"production"',
      PACKAGE_ENV: '"test"',
    },
  },
};
