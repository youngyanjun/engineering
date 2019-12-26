import Http from '@/utils/http';

// 项目
const API_LIST = '/api/sys/engineering-organization/selectOrganizationList';
const API_DETAIL = '';
const API_DETAIL_UPDATE = '/api/sys/engineering-organization/updateOrganizationList';
const API_DETAIL_INSERT = '';

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
 */
const getList = ({ commit }, {
  keyword = '',
} = {}) => {
  return Http.get(API_LIST, {
    name: keyword,
  }).then(data => {
    commit('setState', {
      list: data,
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
  name,
  managerId,
  remark = '',
}) => {
  return Http.get(API_DETAIL_UPDATE, {
    id,
    name,
    managerId,
    remark,
  });
};

/**
 * 插入详情
 * @param {String} address
 * @param {String} contact
 */
const insertDetail = (ctx, {
  address,
  contact,
  juridicalPerson,
  phone,
  remark,
  taxCode,
  unitName,
} = {}) => {
  return Http.post(API_DETAIL_INSERT, {
    address,
    contact,
    juridicalPerson,
    phone,
    remark,
    taxCode,
    unitName,
  });
};

const actions = {
  getList,
  getDetail,
  updateDetail,
  insertDetail,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
