import Http from '@/utils/http';

// 分包商
const API_LIST = '/api/sys/subcontractor/engineering-subcontract/selectSubcontractorList';
const API_DETAIL = '/api/sys/subcontractor/engineering-subcontract/selectSubcontractorById';
const API_DETAIL_UPDATE = '/api/sys/subcontractor/engineering-subcontract/updateSubcontractorById';
const API_DETAIL_INSERT = '/api/sys/subcontractor/engineering-subcontract/insertUser';
const API_DETAIL_REMOVE = '/api/sys/subcontractor/engineering-subcontract/deleteSubcontractor';

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
const getRecords = ({ commit }, {
  keyword = '',
  pageSize = 50,
  pageNum = 1,
} = {}) => {
  return Http.get(API_LIST, {
    subcontractorName: keyword,
    pageSize,
    pageNum,
  }).then(data => {
    commit('setState', {
      list: data.records,
      total: data.total,
    });
  });
};

/**
 * 获取详情
 * @param {String} id
 */
const getRecord = (ctx, id) => {
  return Http.get(API_DETAIL, { id });
};

/**
 * 更新记录
 * @param {String} id
 */
const setRecord = (ctx, {
  id,
  address,
  contact,
  juridicalPerson,
  phone,
  remark,
  taxCode,
  unitName,
}) => {
  return Http.post(API_DETAIL_UPDATE, {
    id,
    address,
    contact,
    juridicalPerson,
    phone,
    remark,
    taxCode,
    unitName,
  });
};

/**
 * 插入记录
 * @param {String} address
 * @param {String} contact
 */
const addRecord = (ctx, {
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

/**
 * 删除记录
 * @param {String} id
 */
const removeRecord = (ctx, id) => {
  return Http.get(API_DETAIL_REMOVE, { id });
};


const actions = {
  getRecords,
  getRecord,
  setRecord,
  addRecord,
  removeRecord,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
