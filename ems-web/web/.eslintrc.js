module.exports = {
    settings: {
        "import/resolver": {
            webpack: {
                config: './build/webpack.config.js'
            }
        }
    },
    "env": {
        "browser": true,
        "es6": true
    },
    "extends": [
        "plugin:vue/base",
        "airbnb-base",
    ],
    "globals": {
    },
    "parserOptions": {
        "parser": "babel-eslint",
        // "ecmaVersion": 2018,
        // "sourceType": "module",
    },
    plugins: [
        "vue",
    ],
    "rules": {
        "no-unused-vars": "warn",
        "import/extensions": "warn",
        "import/no-extraneous-dependencies": [2, { "devDependencies": true }],
        "import/no-unresolved": [2, { "ignore": ["antd-mobile"] }],
        "no-param-reassign": "warn",
        "max-len": ["error", { "code": 120 }],
        "vue/no-parsing-error": "warn",
        "arrow-parens": "off",
        "consistent-return": "off",
        "no-else-return": "off",
        "prefer-destructuring": "off",
        "strict": "off",
        "linebreak-style": "off",
        "prefer-template": "off",
        "prefer-const": "off",
        "one-var": "off",
        "one-var-declaration-per-line": "off"
    }
};