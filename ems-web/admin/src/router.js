import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '@/modules/login';
import Person from '@/modules/person/list';

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [{
    path: '/',
    redirect: '/person',
    name: 'Home',
  }, {
    path: '/login',
    component: Login,
    meta: {
      public: true,
      layout: false,
    },
    name: 'Login',
  }, {
    path: '/person',
    component: Person,
    name: 'PersonList',
  }, {
    path: '/organization',
    component: () => import(/* webpackChunkName: "organization" */'@/modules/organization/list'),
    name: 'OrganizationList',
  }, {
    path: '/project',
    component: () => import(/* webpackChunkName: "project" */'@/modules/project/list'),
    name: 'ProjectList',
  }, {
    path: '/subpackage',
    component: () => import(/* webpackChunkName: "subpackage" */'@/modules/subpackage/list'),
    name: 'SubpackageList',
  }, {
    path: '/field',
    component: () => import(/* webpackChunkName: "field" */'@/modules/field/list'),
    name: 'FieldList',
  }, {
    path: '/log',
    component: () => import(/* webpackChunkName: "log" */'@/modules/log/list'),
    name: 'LogList',
  }, {
    path: '/department',
    component: () => import(/* webpackChunkName: "department" */'@/modules/department'),
    name: 'Department',
  }],
});

router.beforeEach((to, from, next) => {
  next();
});

export default router;
