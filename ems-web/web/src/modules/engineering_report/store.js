import Http from '@/utils/http';

const API_RECORDS = '/api/web/report/index/getWeeklyReport';

/**
 * 列表
 * @param {String} type ''：全部 0：项目部周报 1：项目部月报 2：项目部形象进度
 * @param {String} keyword 查询关键词
 * @param {Number} pageSize
 * @param {Number} pageNum
 */
const getRecords = ({ commit }, {
  organizationId,
  type = '',
  keyword = '',
  pageSize = 50,
  pageNum = 1,
} = {}) => {
  return Http.get(API_RECORDS, {
    organizationId,
    type: type === '-1' ? '' : type,
    name: keyword,
    pageSize,
    pageNum,
  }).then(data => {
    commit('setState', {
      records: data.records,
      total: data.total,
    });
  });
};

export default {
  namespaced: true,
  state: {
    records: [],
    total: 0,
  },
  getters: {},
  mutations: {
    setState: (s, payload) => { s = Object.assign(s, payload); },
  },
  actions: {
    getRecords,
  },
};
