import Http from '@/utils/http';

// 用户
const API_USER_LIST = '/api/base/structure/selectUsersByStructureId';
const API_USER_ADD = '/api/base/structure/addUsersForStructure';
const API_USER_REMOVE_BY_STRUCTURE = '/api/base/structure/deleteUsersForStructure';
const API_USER_REMOVE_BY_ORG = '/api/base/structure/deleteUsersForOrg';
const API_USER_LIST_ALL = '/api/base/structure/selectUsersByOrgId';
const API_ALL_USER_LIST = '/api/base/structure/selectUsersNotInStructure';

/**
 * 组织人员列表
 * @param {String} structureId 组织树节点编号
 */
const getList = ({ commit }, structureId) => {
  return Http.get(API_USER_LIST, {
    structureId,
  }).then(data => {
    commit('setState', {
      list: data,
    });
  });
};

/**
 * 批量添加组织人员
 * @param {String} structureId 组织节点编号
 * @param {Array} userIds
 */
const addUser = (ctx, {
  structureId,
  userIds,
} = {}) => {
  return Http.post(API_USER_ADD, {
    structureId,
    userIds,
  });
};

/**
 * 批量删除组织人员
 * @param {String} structureId 组织节点编号
 * @param {Array} userIds
 */
const removeUserByStructure = (ctx, {
  structureId,
  userIds,
} = {}) => {
  return Http.post(API_USER_REMOVE_BY_STRUCTURE, {
    structureId,
    userIds,
  });
};

/**
 * 批量删除组织人员
 * @param {String} structureId 组织节点编号
 * @param {Array} userIds
 */
const removeUserByOrg = (ctx, {
  orgId,
  userIds,
} = {}) => {
  return Http.post(API_USER_REMOVE_BY_ORG, {
    orgId,
    userIds,
  });
};

/**
 * 获取所有用户
 * @param {String} structureId 组织节点编号
 * @param {String} keyword 关键词
 */
const getAllUserList = (ctx, {
  structureId,
  keyword = '',
} = {}) => {
  return Http.get(API_ALL_USER_LIST, {
    userName: keyword,
    structureId,
  });
};

/**
 * 获取所有组织人员
 * @param {String} orgId 组织编号
 */
const getListAll = ({ commit }, orgId) => {
  return Http.get(API_USER_LIST_ALL, {
    orgId,
  }).then(data => {
    commit('setState', {
      list: data,
    });
    return data;
  });
};

const actions = {
  getList,
  getListAll,
  getAllUserList,
  removeUserByStructure,
  removeUserByOrg,
  addUser,
};

export default {
  namespaced: true,
  state: {
    list: [],
  },
  getters: {},
  mutations: {
    setState: (s, payload) => { s = Object.assign(s, payload); },
  },
  actions,
};
