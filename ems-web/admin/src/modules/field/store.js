import Http from '@/utils/http';

// 自定义字段
const API_LIST = '/api/sys/dictionary/selectList';
const API_DETAIL_UPDATE = '/api/sys/dictionary/updateDictionaryChild';

const state = {
  list: [],
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
 * 更新详情
 * @param {String} id
 * @param {String} dictionaryChild
 * @param {String} name
 */
const updateDetail = (ctx, {
  id,
  dictionaryChild,
  name,
}) => {
  return Http.get(API_DETAIL_UPDATE, {
    id,
    dictionaryChild,
    name,
  });
};

const actions = {
  getList,
  updateDetail,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
