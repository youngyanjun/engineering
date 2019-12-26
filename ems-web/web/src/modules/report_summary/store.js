import Http from '@/utils/http';

// 单体设置
const API_ENGINEERING_ALL = '/api/sys/engineering-manager/all';
const API_SINGLEBODY_LIST = '/api/base/engineering/single/list';
const API_SINGLEBODY_SET = '/api/base/engineering/single/update';
const API_SINGLEBODY_ADD = '/api/base/engineering/single/add';
const API_SINGLEBODY_REMOVE = '/api/base/engineering/single/del';
const API_SINGLEBODY_ORDER = '/api/base/engineering/single/order';
// 原始数据
const API_BASE_LIST = '/api/web/raw/getRawDataList';

/**
 * 获取所有项目
 * @param {String} engineeringName 项目名称
 */
const getProjects = (ctx, engineeringName = '') => {
  return Http.get(API_ENGINEERING_ALL, {
    engineeringName,
  });
};

/**
 * 获取单体列表
 * @param {String} engineeringId 项目编号
 */
const getSingleBodys = (ctx, engineeringId) => {
  return Http.get(API_SINGLEBODY_LIST, { engineeringId });
};

/**
 * 更新单体
 * @param {String} id 单体编号
 * @param {String} engineeringId 项目编号
 * @param {String} mandatory 是否必须上次照片
 * @param {String} singleName 名称
 */
const setSingleBody = (ctx, {
  id,
  engineeringId,
  mandatory,
  singleName,
}) => {
  return Http.post(API_SINGLEBODY_SET, {
    id,
    engineeringId,
    mandatory,
    singleName,
  });
};

/**
 * 添加单体
 * @param {String} engineeringId 项目编号
 * @param {String} mandatory 是否必须上次照片
 * @param {String} singleName 名称
 */
const addSingleBody = (ctx, {
  engineeringId,
  mandatory,
  singleName,
}) => {
  return Http.post(API_SINGLEBODY_ADD, {
    engineeringId,
    mandatory,
    singleName,
  });
};

/**
 * 删除单体
 * @param {Array} ids 单体编号列表
 */
const removeSingleBodys = (ctx, ids = []) => {
  return Http.post(API_SINGLEBODY_REMOVE, { ids });
};

/**
 * 排序单体
 * @param {Array} ids 单体编号列表
 */
const setSingleBodysOrder = (ctx, ids = []) => {
  return Http.post(API_SINGLEBODY_ORDER, { ids });
};

/**
 * 获取原始数据列表
 * @param {String} reportName 报表名称
 * @param {String} orgId 组织编号
 * @param {String} reportType 报表类型 '': 全部 0：周报 1：月报
 * @param {String} reportMonth 月份 e.g. 2019-07
 * @param {String} pageNum 当前页码
 * @param {String} pageSize 页面记录条数
 */
const getBaseList = ({ commit }, {
  reportName = '',
  orgId = '',
  reportMonth = '',
  reportType = '',
  pageNum = 1,
  pageSize = 50,
} = {}) => {
  return Http.post(API_BASE_LIST, {
    reportName,
    orgId,
    reportMonth,
    reportType,
    pageNum,
    pageSize,
  }).then(data => {
    commit('setState', {
      baseList: data.records,
      baseRecordTotal: data.total,
    });
  });
};

export default {
  namespaced: true,
  state: {
    baseList: [],
    baseRecordTotal: 0,
  },
  getter: {},
  mutations: {
    setState: (state, payload) => { state = Object.assign(state, payload); },
  },
  actions: {
    getProjects,
    getSingleBodys,
    setSingleBody,
    addSingleBody,
    removeSingleBodys,
    setSingleBodysOrder,
    getBaseList,
  },
};
