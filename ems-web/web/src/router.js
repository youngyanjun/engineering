import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '@/modules/login';

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [
    {
      name: 'Home',
      path: '/',
      redirect: {
        name: 'ProjectList',
      },
    },
    {
      name: 'Login',
      path: '/login',
      component: Login,
      meta: {
        layout: false,
      },
    },
    {
      name: 'ProjectList',
      path: '/project/list',
      component: () => import(/* webpackChunkName: "project" */'@/modules/project/list'),
      props: true,
      meta: {
        layout: false,
      },
    },
    {
      name: 'Person',
      path: '/person',
      component: () => import(/* webpackChunkName: "person" */'@/modules/person'),
      meta: {
        layout: false,
      },
    },
    {
      name: 'Notice',
      path: '/notice',
      component: () => import(/* webpackChunkName: "notice" */'@/modules/notice'),
      meta: {
        layout: false,
      },
    },
    {
      name: 'Help',
      path: '/help',
      component: () => import(/* webpackChunkName: "help" */'@/modules/help'),
      meta: {
        layout: false,
      },
    },
    {
      name: 'EngineeringReportList',
      path: '/report/engineering/:engineeringId/:orgType/list',
      component: () => import(/* webpackChunkName: "ereport" */'@/modules/engineering_report/list'),
      props: true,
    },
    {
      name: 'EngineeringReportWeekAdd',
      path: '/report/engineering/:engineeringId/:orgType/week/:reportId/add/',
      component: () => import(/* webpackChunkName: "ereport_wad" */'@/modules/engineering_report_week/add'),
      props: true,
      meta: {
        layout: false,
      },
      redirect: {
        name: 'EngineeringReportWeekBase',
      },
      children: [
        {
          name: 'EngineeringReportWeekBase',
          path: 'base',
          component: () => import(/* webpackChunkName: "ereport_wbs" */'@/modules/engineering_report_week/add/base'),
          meta: {
            layout: false,
          },
        },
        {
          name: 'EngineeringReportWeekProgress',
          path: 'progress',
          component: () => import(/* webpackChunkName: "ereport_wpg" */'@/modules/engineering_report_week/add/progress'),
          meta: {
            layout: false,
          },
        },
        {
          name: 'EngineeringReportWeekSchedule',
          path: 'schedule',
          component: () => import(/* webpackChunkName: "ereport_wsd" */'@/modules/engineering_report_week/add/schedule'),
          meta: {
            layout: false,
          },
        },
        {
          name: 'EngineeringReportWeekNextplan',
          path: 'nextplan',
          component: () => import(/* webpackChunkName: "ereport_wnp" */'@/modules/engineering_report_week/add/nextplan'),
          meta: {
            layout: false,
          },
        },
        { // 现场其他需要说明
          name: 'EngineeringReportWeekOtherRequirements',
          path: 'other_requirements',
          component: () => import(/* webpackChunkName: "ereport_wor" */'@/modules/engineering_report_week/add/other_requirements'),
          meta: {
            layout: false,
          },
        },
        { // 可能影响工程的因素
          name: 'EngineeringReportWeekOtherAffectFactors',
          path: 'affect_factors',
          component: () => import(/* webpackChunkName: "ereport_waf" */'@/modules/engineering_report_week/add/affect_factors'),
          meta: {
            layout: false,
          },
        },
        { // 本周收发资料汇总
          name: 'EngineeringReportWeekOtherDataSummary',
          path: 'data_summary',
          component: () => import(/* webpackChunkName: "ereport_wds" */'@/modules/engineering_report_week/add/data_summary'),
          meta: {
            layout: false,
          },
        },
        { // 本周收发资料汇总
          name: 'EngineeringReportWeekOtherWorkSafety',
          path: 'work_safety',
          component: () => import(/* webpackChunkName: "ereport_wws" */'@/modules/engineering_report_week/add/work_safety'),
          meta: {
            layout: false,
          },
        },
        { // 现场整改纪要
          name: 'EngineeringReportWeekOtherSiteRectification',
          path: 'site_rectification',
          component: () => import(/* webpackChunkName: "ereport_wsr" */'@/modules/engineering_report_week/add/site_rectification'),
          meta: {
            layout: false,
          },
        },
        { // 天气情况
          name: 'EngineeringReportWeekWeather',
          path: 'weather',
          component: () => import(/* webpackChunkName: "ereport_wwr" */'@/modules/engineering_report_week/add/weather'),
          meta: {
            layout: false,
          },
        },
        { // 驳回意见
          name: 'EngineeringReportWeekRejection',
          path: 'rejection',
          component: () => import(/* webpackChunkName: "ereport_wrj" */'@/modules/engineering_report_week/add/rejection'),
          meta: {
            layout: false,
          },
        },
      ],
    },
    {
      name: 'EngineeringReportWeekPreview',
      path: '/report/engineering/:engineeringId/:orgType/week/:reportId/preview/:viewType',
      component: () => import(/* webpackChunkName: "ereport_wp" */'@/modules/engineering_report_week/preview'),
      props: true,
      meta: {
        layout: false,
      },
    },
    {
      name: 'ReportSummary', // 工程报表汇总
      path: '/report/summary/:engineeringId/:orgType',
      component: () => import(/* webpackChunkName: "rsummary" */'@/modules/report_summary'),
      props: true,
      redirect: {
        name: 'ReportSummaryBase',
      },
      children: [{
        name: 'ReportSummaryBase', // 原始数据
        path: 'base',
        component: () => import(/* webpackChunkName: "rsummary_bs" */'@/modules/report_summary/base'),
      }, {
        name: 'ReportSummaryList', // 周月报列表
        path: 'list',
        component: () => import(/* webpackChunkName: "rsummary_ls" */'@/modules/report_summary/list'),
      }, {
        name: 'ReportSummaryWeekDetail', // 周报详情
        path: 'week_detail',
        component: () => import(/* webpackChunkName: "rsummary_dt" */'@/modules/report_summary/week_detail'),
      }, {
        name: 'ReportSummaryConstructionPlan', // 月度施工计划
        path: 'construction_plan',
        component: () => import(/* webpackChunkName: "rsummary_cpn" */'@/modules/report_summary/construction_plan'),
      }, {
        name: 'ReportSummaryConstructionPlanRate', // 月度施工计划目标执行率
        path: 'construction_plan_rate',
        component: () => import(/* webpackChunkName: "rsummary_cpnr" */'@/modules/report_summary/construction_plan_rate'),
      }, {
        name: 'ReportSummarySet', // 报表设置
        path: 'set',
        component: () => import(/* webpackChunkName: "rsummary_st" */'@/modules/report_summary/set'),
      }],
    },
    {
      name: 'DepartmentReportList',
      path: '/report/department/:engineeringId/:orgType/list',
      component: () => import(/* webpackChunkName: "dreport" */'@/modules/department_report/list'),
      props: true,
    },
    {
      name: 'DepartmentReportWeekAdd',
      path: '/report/department/:engineeringId/:orgType/week/:reportId/add/',
      component: () => import(/* webpackChunkName: "dreport_wa" */'@/modules/department_report_week/add'),
      props: true,
      meta: {
        layout: false,
      },
    },
    {
      name: 'DepartmentReportWeekPreview',
      path: '/report/department/:engineeringId/:orgType/week/:reportId/preview/:viewType',
      component: () => import(/* webpackChunkName: "dreport_wp" */'@/modules/department_report_week/preview'),
      props: true,
      meta: {
        layout: false,
      },
    },
    {
      name: 'Deploy',
      path: '/deploy/:engineeringId/:orgType',
      component: () => import(/* webpackChunkName: "deploy" */'@/modules/deploy'),
      redirect: {
        name: 'DeployStructure',
      },
      props: true,
      children: [
        {
          name: 'DeployStructure',
          path: 'structure',
          component: () => import(/* webpackChunkName: "deploy_st" */'@/modules/deploy/structure'),
          props: true,
        },
        {
          name: 'DeployRole',
          path: 'role',
          component: () => import(/* webpackChunkName: "deploy_rl" */'@/modules/deploy/role'),
          props: true,
        },
        {
          name: 'DeployBase',
          path: 'base',
          component: () => import(/* webpackChunkName: "deploy_bs" */'@/modules/deploy/base'),
          props: true,
        },
        {
          name: 'DeploySet',
          path: 'set',
          component: () => import(/* webpackChunkName: "deploy_st" */'@/modules/deploy/set'),
          props: true,
        },
      ],
    },
  ],
});

router.beforeEach((to, from, next) => {
  next();
});

export default router;
