import Vue from 'vue';
import Vuex from 'vuex';
import app from '@/modules/app/store';
import subpackage from '@/modules/subpackage/store';
import log from '@/modules/log/store';
import person from '@/modules/person/store';
import field from '@/modules/field/store';
import project from '@/modules/project/store';
import organization from '@/modules/organization/store';
import department from '@/modules/department/store';

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    app,
    subpackage,
    log,
    person,
    field,
    project,
    organization,
    department,
  },
});

export default store;
