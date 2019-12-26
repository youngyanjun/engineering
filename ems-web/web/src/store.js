import Vue from 'vue';
import Vuex from 'vuex';
import app from '@/modules/app/store';
import engineeringReport from '@/modules/engineering_report/store';
import engineeringReportWeek from '@/modules/engineering_report_week/store';
import departmentReport from '@/modules/department_report/store';
import departmentReportWeek from '@/modules/department_report_week/store';
import deploy from '@/modules/deploy/store';
import deployStructurePerson from '@/modules/deploy_structure_person/store';
import deployRolePerson from '@/modules/deploy_role_person/store';
import reportSummary from '@/modules/report_summary/store';
import project from '@/modules/project/store';

Vue.use(Vuex);

const store = new Vuex.Store({
  ...app,
  modules: {
    engineeringReport,
    engineeringReportWeek,
    departmentReport,
    departmentReportWeek,
    deployStructurePerson,
    deployRolePerson,
    deploy,
    reportSummary,
    project,
  },
});

export default store;
