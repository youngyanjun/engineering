import Http from '@/utils/http';

// 项目
const API_LIST = '/api/sys/engineering-manager/selectList';
const API_DETAIL = '/api/subcontractor/engineering-subcontract/selectSubcontractorById';
const API_DETAIL_UPDATE = '/api/sys/engineering-manager/updateEngineering';
const API_DETAIL_INSERT = '/api/sys/engineering-manager/insertEngineering';
const API_RANGE = '/api/sys/engineering-manager-ranage/selectRangeList';
const API_SUBCONTRACT = '/api/sys/engineering-involve-subcontract/selectSubcontractList';
const API_MANAGER_LIST = '/api/sys/engineering-manager/selectUserListByNamePhone';
const API_SUBCONTRACT_LIST = '/api/sys/engineering-manager/selectSubcontractList';

const state = {
  list: [],
  total: 0,
  subpackageList: [],
  managerList: [],
  ranges: [{
    label: '设计',
    value: '1',
  }, {
    label: 'BIM',
    value: '2',
  }, {
    label: '深化-钢构',
    value: '3',
  }, {
    label: '深化-PC',
    value: '4',
  }, {
    label: '制造-钢构',
    value: '5',
  }, {
    label: '制造-PC',
    value: '6',
  }, {
    label: '制造-门窗',
    value: '7',
  }, {
    label: '施工',
    value: '8',
  }],
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
    engineeringName: keyword,
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
const getDetail = async (ctx, id) => {
  const resultData = {};
  // let result = [];
  const p1 = await Http.get(API_RANGE, { engineeringId: id });
  const p2 = await Http.get(API_SUBCONTRACT, { engineeringId: id });
  // console.log(Promise.all(p1, p2));
  // Promise.all(p1, p2).then(() => {

  // });
  // result = await Promise.all(p1, p2);
  resultData.rangeList = p1;
  resultData.subcontractList = p2;
  return resultData;
};

/**
 * 获取管理员列表
 */
const getManagerList = ({ commit }) => {
  return Http.get(API_MANAGER_LIST).then(data => {
    commit('setState', { managerList: data });
  });
};

/**
 * 获取分包列表
 */
const getSubpackageList = ({ commit }) => {
  return Http.get(API_SUBCONTRACT_LIST).then(data => {
    commit('setState', { subpackageList: data });
  });
};

/**
 * 更新详情
 * @param {String} id
 */
const updateDetail = (ctx, {
  id,
  contentPrice,
  engineeringAbbreviation,
  engineeringCode,
  engineeringName,
  flag,
  managerId,
  rangeList,
  subcontractList,
}) => {
  return Http.post(API_DETAIL_UPDATE, {
    id,
    contentPrice,
    engineeringAbbreviation,
    engineeringCode,
    engineeringName,
    flag,
    managerId,
    rangeList,
    subcontractList,
  });
};

/**
 * 插入详情
 * @param {String} address
 * @param {String} contact
 */
const insertDetail = (ctx, {
  contentPrice,
  engineeringAbbreviation,
  engineeringCode,
  engineeringName,
  flag,
  managerId,
  rangeList = [],
  subcontractList = [],
} = {}) => {
  return Http.post(API_DETAIL_INSERT, {
    contentPrice,
    engineeringAbbreviation,
    engineeringCode,
    engineeringName,
    flag,
    managerId,
    rangeList,
    subcontractList,
  });
};

const actions = {
  getList,
  getDetail,
  updateDetail,
  insertDetail,
  getManagerList,
  getSubpackageList,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
