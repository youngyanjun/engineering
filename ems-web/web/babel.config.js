module.exports = {
  presets: [
    ['@babel/preset-env', {
      modules: false,
      targets: {
        browsers: [
          'iOS >= 6',
          'Android > 4.0',
          'not ie <= 8',
        ],
      },
    }],
  ],
  plugins: [
    '@babel/plugin-proposal-class-properties',
    'syntax-dynamic-import',
    [
      'component',
      {
        libraryName: 'element-ui',
        libraryDirectory: 'lib',
        styleLibraryName: 'theme-chalk',
      },
    ],
    [
      'import', {
        libraryName: 'vant',
        libraryDirectory: 'es',
        style: name => `${name}/style/less`,
      },
      'vant',
    ],
  ],
};
