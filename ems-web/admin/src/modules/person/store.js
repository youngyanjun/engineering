import Http from '@/utils/http';

// 用户
const API_LIST = '/api/sys/sys-user/userList';
const API_DETAIL = '';
const API_DETAIL_UPDATE = '/api/sys/sys-user/updateAccountOne';
const API_DETAIL_INSERT = '/api/sys/sys-user/insertUser';
const API_DETAIL_DELETE = '/api/sys/sys-user/deleteAccount';
const API_PASSWORD_RESET = '/api/sys/sys-user/updatePassword';
const API_DETAIL_FREEZE = '/api/sys/sys-user/updateAccountDisableBatch';
const API_DETAIL_UNFREEZE = '/api/sys/sys-user/updateAccountEnableBatch';

const ROLE_MAP = {
  1: '正常',
  2: '冻结',
};

const state = {
  list: [],
  total: 0,
};

const getters = {};

const mutations = {
  setState: (s, payload) => { s = Object.assign(s, payload); },
};

/**
 * 列表
 * @param {String} keyword 查询关键词
 * @param {Number} pageSize
 * @param {Number} pageNum
 */
const getList = ({ commit }, {
  keyword = '',
  pageSize = 50,
  pageNum = 1,
} = {}) => {
  return Http.get(API_LIST, {
    userName: keyword,
    pageSize,
    pageNum,
  }).then(data => {
    commit('setState', {
      list: data.records.map(item => ({ ...item, statusLabel: ROLE_MAP[item.status] })),
      total: data.total,
    });
  });
};

/**
 * 获取详情
 * @param {String} id
 */
const getDetail = (ctx, id) => {
  return Http.get(API_DETAIL, { id });
};

/**
 * 更新详情
 * @param {String} id
 */
const updateDetail = (ctx, {
  id,
  userName,
  email,
}) => {
  return Http.get(API_DETAIL_UPDATE, {
    id,
    userName,
    email,
  });
};

/**
 * 插入详情
 * @param {String} address
 * @param {String} contact
 */
const insertDetail = (ctx, {
  userName,
  email,
  phone,
} = {}) => {
  return Http.get(API_DETAIL_INSERT, {
    userName,
    email,
    phone,
  });
};

/**
 * 批量删除详情
 * @param {Array} ids
 */
const deleteDetail = (ctx, ids) => {
  return Http.get(API_DETAIL_DELETE, { ids: ids.join(',') });
};

/**
 * 重置密码
 * @param {Array} ids
 */
const resetPassword = (ctx, ids) => {
  return Http.get(API_PASSWORD_RESET, { ids: ids.join(',') });
};

/**
 * 冻结账户
 * @param {Array} ids
 */
const freezeDetail = (ctx, ids) => {
  return Http.get(API_DETAIL_FREEZE, { ids: ids.join(',') });
};

/**
 * 解冻账户
 * @param {Array} ids
 */
const unfreezeDetail = (ctx, ids) => {
  return Http.get(API_DETAIL_UNFREEZE, { ids: ids.join(',') });
};

const actions = {
  getList,
  getDetail,
  updateDetail,
  insertDetail,
  deleteDetail,
  resetPassword,
  freezeDetail,
  unfreezeDetail,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
