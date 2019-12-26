import Http from '@/utils/http';

const API_LOGIN = '/api/sys/login';
const API_LOGOUT = '/api/sys/logout';

const API_FILE_UPLOAD = '/api/sys/file/upload';
const API_SET_FEEDBACK = '/api/sys/problem-feedback/saveProblemFeedback';
const API_GET_PROJECT_USERINFO = '/api/sys/sys-user/getLoginUserInfoByOrgId';

/**
 * 登录
 * @param {String} password
 * @param {String} phone
 */
const login = (ctx, {
  password,
  phone,
} = {}) => {
  return Http.post(API_LOGIN, {
    password,
    phone,
  });
};

/**
 * 文件上传
 */
const uploadFile = (ctx, file) => {

  const formdata = new FormData();
  formdata.append('file', file);
  return Http.post(API_FILE_UPLOAD, formdata, {});
};
/**
 * 注销
 */
const logout = (ctx) => {
  return Http.get(API_LOGOUT);
};

// 反馈
const setFeedBack = (ctx, {
  contactInformation,
  content,
  sysFileVOS,
} = {}) => {

  return Http.post(API_SET_FEEDBACK, {
    contactInformation,
    content,
    sysFileVOS,
  });
};


const setState = ({
  commit,
}, payload) => {
  commit('setState', payload);
};

const setUserInfo = ({
  commit,
}, payload) => {
  commit('setState', {
    userInfo: payload,
  });
};

const actions = {
  login,
  logout,
  uploadFile,
  setState,
  setUserInfo,
  setFeedBack,
};

export default {
  state: {
    userInfo: {},
    projectRanges: [{
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
  },
  getters: {},
  mutations: {
    setState(s, payload) {
      // s.engineeringReportWeek = Object.assign({}, payload.engineeringReportWeek);
      // s.engineeringReport = Object.assign({}, payload.engineeringReport);
      // s.project = Object.assign({}, payload.project);
      // s.userInfo = Object.assign({}, payload.userInfo);
      // s.reportSummary = Object.assign({}, payload.reportSummary);
      // delete payload.engineeringReportWeek;
      // delete payload.engineeringReport;
      // delete payload.project;
      // delete payload.userInfo;
      s = Object.assign(s, payload);
    },
  },
  actions,
};
