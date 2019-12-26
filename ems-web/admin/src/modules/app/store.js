import Http from '@/utils/http';

const API_LOGIN = '/api/sys/login';
const API_LOGOUT = '/api/sys/logout';

const state = {};

const getters = {};

const mutations = {};

/**
 * 登录
 * @param {String} password
 * @param {String} phone
 */
const login = (ctx, { password, phone } = {}) => {
  return Http.post(API_LOGIN, { password, phone });
};

/**
 * 注销
 */
const logout = (ctx) => {
  return Http.get(API_LOGOUT);
};

const actions = {
  login,
  logout,
};

export default {
  state,
  getters,
  mutations,
  actions,
};
