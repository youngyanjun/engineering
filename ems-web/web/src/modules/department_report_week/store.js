import Http from '@/utils/http';

const API_WEEKLY_CREATE = '/api/web/department/report-report-list/getDepartmentReportWeekly';
const API_WEEKLY_GET = '/api/web/department/report-report-list/AddDepartmentReportWeekly';
const API_WEEKLY_RECORD = '/api/web/department/report-weekly/EditDepartmentReport';
const API_WEEKLY_SAVE = '/api/web/department/report-weekly/AddDepartmentReportWeekly';
const API_WEEKLY_SUBMIT = '/api/web/department/report-weekly/SubmitDepartmentReport';
const API_WEEKLY_MSG_USER = '/api/web/department/report-weekly/getOrgEngUser';

/**
 * 获取周次
 */
const getWeek = ({ commit }, id) => {
  return Http.get(API_WEEKLY_GET, { organizationId: id }).then(data => {
    commit('setState', { week: data || [] });
  });
};

/**
 * 创建周报
 * @param {String} organizationId
 * @param {String} organizationType
 * @param {String} weeklyMonth
 * @param {String} reportType
 * @param {String} constructionDateStart
 * @param {String} constructionDateFinis
 */
const createRecord = (ctx, {
  organizationId,
  organizationType,
  weeklyMonth,
  reportType = 0,
  constructionDateStart,
  constructionDateFinis,
} = {}) => {
  return Http.post(API_WEEKLY_CREATE, {
    organizationId,
    organizationType,
    weeklyMonth,
    reportType,
    constructionDateStart,
    constructionDateFinis,
  });
};

/**
 * 提交报表
 * @param {String} reportId
 * @param {String} smList
 */
const submitReport = (ctx, {
  reportId,
  smList = [],
}) => {
  return Http.post(API_WEEKLY_SUBMIT, { reportId, smList });
};

/**
 * 获取记录
 * @param {String} reportId
 */
const getRecord = (ctx, reportId) => {
  return Http.get(API_WEEKLY_RECORD, { reportId });
};

/**
 * 保存记录
 */
const saveRecord = (ctx, list) => {
  return Http.post(API_WEEKLY_SAVE, list);
};

/**
 * 获取消息用户列表
 * @param {String} organizationType
 */
const getMsgUsers = ({ commit }, organizationType) => {
  return Http.get(API_WEEKLY_MSG_USER, {
    organizationType,
  }).then(data => {
    commit('setState', {
      msgUsers: data,
    });
  });
};

export default {
  namespaced: true,
  state: {
    week: [],
    msgUsers: [],
    thead1: [
      [
        {
          value: '序号',
          style: {
            readonly: true,
            column: 2,
            align: 'center',
          },
        }, {
          value: '基本信息',
          style: {
            row: 2,
            align: 'center',
          },
        }, {
          value: '本周工作完成情况及下周工作计划',
          style: {
            row: 5,
            align: 'center',
          },
        }, {
          value: '各个项目总工作完成及配合情况',
          style: {
            row: 5,
            align: 'center',
          },
        }, {
          value: '备注',
          style: {
            column: 2,
            align: 'center',
          },
        },
      ],
      [
        {
          value: '',
          style: {
            column: 0,
            width: 50,
          },
        },
        {
          value: '工程名称',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '部门',
          style: {
            column: 1,
            align: 'center',
          },
        },
        {
          value: '本周计划工作',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '本周计划工作完成情况',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '本周进度滞后原因',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '解决方案',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '下周工作计划',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '项目总进度完成率',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '总进度未完滞后原因',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '解决方案',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '需其他部门配合事宜',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '项目负责人',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '',
          style: {
            column: 0,
            width: 100,
          },
        },
      ],
    ],
    theadKey1: [
      'engName',
      'department',
      'plan',
      'planComplete',
      'backWard',
      'solution',
      'nextPlan',
      'workText',
      'total',
      'solutionAll',
      'coordination',
      'projectLeader',
      'remark',
    ],
    thead2: [
      [
        {
          value: '序号',
          style: {
            readonly: true,
            column: 2,
            align: 'center',
          },
        }, {
          value: '基本信息',
          style: {
            row: 3,
            align: 'center',
          },
        }, {
          value: '本周工作完成情况及下周工作计划',
          style: {
            row: 5,
            align: 'center',
          },
        }, {
          value: '各个项目总工作完成及配合情况',
          style: {
            row: 5,
            align: 'center',
          },
        }, {
          value: '备注',
          style: {
            column: 2,
            align: 'center',
          },
        },
      ],
      [
        {
          value: '工程名称',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '部门',
          style: {
            column: 1,
            align: 'center',
          },
        },
        {
          value: '项目阶段',
          style: {
            column: 1,
            align: 'center',
          },
        },
        {
          value: '本周计划工作',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '本周计划工作完成情况',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '本周进度滞后原因',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '解决方案',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '下周工作计划',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '项目总进度完成率',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '总进度未完滞后原因',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '解决方案',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '需其他部门配合事宜',
          style: {
            column: 1,
            align: 'center',
          },
        }, {
          value: '项目负责人',
          style: {
            column: 1,
            align: 'center',
          },
        },
      ],
    ],
    theadKey2: [
      'engName',
      'department',
      'stage',
      'plan',
      'planComplete',
      'backWard',
      'solution',
      'nextPlan',
      'workText',
      'total',
      'solutionAll',
      'coordination',
      'projectLeader',
      'remark',
    ],
  },
  getters: {},
  mutations: {
    setState: (s, payload) => { s = Object.assign(s, payload); },
    setField: (s, payload) => { s.field = Object.assign({}, s.field, payload); },
    setHelp: (s, payload) => { s.help = Object.assign({}, s.help, payload); },
  },
  actions: {
    createRecord,
    submitReport,
    getWeek,
    getRecord,
    saveRecord,
    getMsgUsers,
  },
};
