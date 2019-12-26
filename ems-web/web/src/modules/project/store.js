import Http from '@/utils/http';

const API_PROJECT_LIST = 'api/base/organization/selectOrganizationsForLoginUser';
const API_PROJECT_USERINFO = '/api/sys/sys-user/getLoginUserInfoByOrgId';
const API_SET_PROJECT_USERINFO = '/api/sys/sys-user/updateLoginUserInfo';
const API_GET_PROJECT_NOTICE = '/api/sys/message/selectSysMessagesForLoginUser';
const API_UPDATE_PROJECT_NOTICE = '/api/sys/message/updateSysMessagesForLoginUser';
const API_DELETE_PROJECT_NOTICE = '/api/sys/message/deleteSysMessagesForLoginUser';
const API_GET_PROJECT_UNREAD_NOTICE = '/api/sys/message/selectUnReadMessagesForLoginUser';

/**
 * 获取项目列表
 */
const getProjectList = async () => {
  return Http.get(API_PROJECT_LIST);
};

// 获取项目下用户信息
const getProjectUserInfo = async (ctx, orgId) => {
  return Http.get(API_PROJECT_USERINFO, {
    orgId,
  });
};
const getUnReadNotice = async () => {
  return Http.get(API_GET_PROJECT_UNREAD_NOTICE);
};

// 设置用户信息
const setProjectUserInfo = (ctx, {
  userName,
  email,
  userPwd,
  newUserPwd,
} = {}) => {
  return Http.post(API_SET_PROJECT_USERINFO, {
    userName,
    email,
    userPwd,
    newUserPwd,
  });
};

// 获取通知
const getProjectNotice = async () => {
  return Http.get(API_GET_PROJECT_NOTICE);
};

// 更新通知状态
const updateProjectNotice = (ctx, {
  messageIds,
} = {}) => {
  return Http.post(API_UPDATE_PROJECT_NOTICE, {
    messageIds,
  });
};

// 删除通知
const deleteProjectNotice = (ctx, {
  messageIds,
} = {}) => {
  return Http.post(API_DELETE_PROJECT_NOTICE, {
    messageIds,
  });
};

const setProject = ({
  commit,
}, payload) => {
  commit('setState', {
    project: payload,
  });
};

const actions = {
  getProjectList,
  setProject,
  getProjectUserInfo,
  setProjectUserInfo,
  getProjectNotice,
  updateProjectNotice,
  deleteProjectNotice,
  getUnReadNotice,
};

export default {
  namespaced: true,
  state: {
    project: {},
  },
  getters: {},
  mutations: {
    setState: (s, payload) => {
      s = Object.assign(s, payload);
    },
  },
  actions,
};
