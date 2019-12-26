import Http from '@/utils/http';

// 用户
const API_USER_LIST = '/api/sys/role/selectUsersForRole';
const API_USER_ADD = '/api/sys/role/addUsersForRole';
const API_USER_REMOVE = '/api/sys/role/deleteUsersForRole';
const API_USER_LIST_ALL = '/api/sys/role/selectUsersForRole';
const API_ALL_USER_LIST = '/api/sys/role/selectUsersNotInRole';

const state = {
  list: [],
};

const getters = {};

const mutations = {
  setState: (s, payload) => { s = Object.assign(s, payload); },
};

/**
 * 角色人员列表
 * @param {String} roleId 角色树节点编号
 * @param {String} orgId
 */
const getList = ({ commit }, {
  orgId,
  roleId,
} = {}) => {
  return Http.get(API_USER_LIST, {
    orgId,
    roleId,
  }).then(data => {
    commit('setState', {
      list: data,
    });
  });
};

/**
 * 批量添加角色人员
 * @param {String} roleId 角色节点编号
 * @param {Array} userIds
 */
const addUser = (ctx, {
  roleId,
  userIds,
} = {}) => {
  return Http.post(API_USER_ADD, {
    roleId,
    userIds,
  });
};

/**
 * 批量删除角色人员
 * @param {String} roleId 角色节点编号
 * @param {Array} userIds
 */
const removeUser = (ctx, {
  roleId,
  userIds,
} = {}) => {
  return Http.post(API_USER_REMOVE, {
    roleId,
    userIds,
  });
};

/**
 * 获取所有用户
 * @param {String} roleId 组织节点编号
 * @param {String} keyword 关键词
 */
const getAllUserList = (ctx, {
  roleId,
  keyword = '',
} = {}) => {
  return Http.get(API_ALL_USER_LIST, {
    roleId,
    userName: keyword,
  });
};

/**
 * 获取所有角色人员
 * @param {String} orgId 角色编号
 */
const getListAll = ({ commit }, orgId) => {
  return Http.get(API_USER_LIST_ALL, {
    orgId,
  }).then(data => {
    commit('setState', {
      list: data,
    });
  });
};

const actions = {
  getList,
  getListAll,
  getAllUserList,
  removeUser,
  addUser,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
