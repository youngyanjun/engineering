import Http from '@/utils/http';

const API_LIST = '/api/sys/log/sys-log/selectLogList';

const state = {
  list: [],
  total: 0,
};

const getters = {};

const mutations = {
  setList: (s, payload) => { s = Object.assign(s, payload); },
};

/**
 * 获取列表
 * @param {String} startTime
 * @param {String} endTime
 * @param {Number} pageSize
 * @param {Number} pageNum
 */
const getList = ({ commit }, { startTime, endTime, pageSize, pageNum } = {}) => {
  const params = {
    pageSize,
    pageNum,
  };

  if (startTime) {
    params.startTime = encodeURIComponent(startTime);
  }

  if (endTime) {
    params.endTime = encodeURIComponent(endTime);
  }

  return Http.get(API_LIST, params).then(data => {
    commit('setList', {
      list: data.records,
      total: data.total,
    });
  });
};

const actions = {
  getList,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
