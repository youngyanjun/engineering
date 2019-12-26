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
        libraryName: '@liangshu/listen',
        style: name => {
          return name + '/style/index.css';
        },
        // style: name => {
        //   const position = name.lastIndexOf('/');
        //   return name.slice(0, position) + '/style' + name.slice(position) + '.css';
        // },
      },
      'button',
    ],
  ],
};
