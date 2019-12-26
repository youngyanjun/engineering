import Http from '@/utils/http';

const API_ROLE_LIST = '/api/sys/role/selectRoleTemplateList';
const API_ROLE_UPDATE = '/api/sys/role/updateRoleTemplateList';
const API_ROLE_INSERT = '/api/sys/role/insertRoleTemplateList';
const API_ROLE_DELETE = '/api/sys/role/deleteRoleTemplateList';
const API_PERMISSION_LIST = '/api/sys/menu/selectMenuList';
const API_PERMISSION_UPDATE = '/api/sys/role-menu/updateRoleMenu';
const API_STRUCTURE_LIST = '/api/sys/engineering-organization-template/selectOrganizationTemplateList';
const API_STRUCTURE_UPDATE = '/api/sys/engineering-organization-template/updateOrganizationTemplateList';
const API_STRUCTURE_INSERT = '/api/sys/engineering-organization-template/insertOrganizationTemplateList';
const API_STRUCTURE_DELETE = '/api/sys/engineering-organization-template/deleteOrganizationTemplateList';

const state = {
  roleList: [],
  permissionList: [],
  structureList: [],
};

const getters = {};

const mutations = {
  setState(s, payload) { s = Object.assign(s, payload); },
};

/**
 * 获取角色列表
 */
const getRoleList = ({ commit }) => {
  return Http.get(API_ROLE_LIST).then(data => {
    commit('setState', {
      roleList: data,
    });
  });
};

/**
 * 更新角色
 */
const updateRole = (ctx, {
  id,
  name,
  remark,
}) => {
  return Http.get(API_ROLE_UPDATE, {
    id,
    name,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 新增角色
 */
const insertRole = (ctx, {
  parentId,
  name,
  remark,
}) => {
  return Http.get(API_ROLE_INSERT, {
    parentId,
    name,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 删除角色
 * @param {String} id
 */
const deleteRole = (ctx, id) => {
  return Http.get(API_ROLE_DELETE, { id });
};

/**
 * 获取角色列表
 */
const getStructureList = ({ commit }) => {
  return Http.get(API_STRUCTURE_LIST).then(data => {
    commit('setState', {
      structureList: data,
    });
  });
};

/**
 * 更新角色
 */
const updateStructure = (ctx, {
  id,
  name,
  remark,
}) => {
  return Http.get(API_STRUCTURE_UPDATE, {
    id,
    name,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 新增角色
 */
const insertStructure = (ctx, {
  parentId,
  name,
  remark,
}) => {
  return Http.get(API_STRUCTURE_INSERT, {
    parentId,
    name,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 删除角色
 * @param {String} id
 */
const deleteStructure = (ctx, id) => {
  return Http.get(API_STRUCTURE_DELETE, { id });
};


/**
 * 获取权限列表
 * @param {String} id
 */
const getPermissionList = ({ commit }, id) => {
  return Http.get(API_PERMISSION_LIST, {
    id,
  }).then(data => {
    commit('setState', {
      permissionList: data,
    });
  });
};

/**
 * 更新角色权限
 * * @param {Array} list
 */
const updatePermissionList = (ctx, list) => {
  return Http.post(API_PERMISSION_UPDATE, {
    list,
  });
};

const actions = {
  getRoleList,
  updateRole,
  insertRole,
  deleteRole,
  getStructureList,
  updateStructure,
  insertStructure,
  deleteStructure,
  getPermissionList,
  updatePermissionList,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
