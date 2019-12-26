import Http from '@/utils/http';

const API_ROLE_LIST = '/api/sys/role/selectRoles';
const API_ROLE_UPDATE = '/api/sys/role/updateRole';
const API_ROLE_ADD = '/api/sys/role/addRole';
const API_ROLE_REMOVE = '/api/sys/role/deleteRole';
const API_PERMISSION_LIST = '/api/sys/menu/selectMenuList';
const API_PERMISSION_UPDATE = '/api/sys/role-menu/updateRoleMenu';
const API_STRUCTURE_LIST = '/api/base/structure/selectStructureTree';
const API_STRUCTURE_UPDATE = '/api/base/structure/updateStructure';
const API_STRUCTURE_INSERT = '/api/base/structure/addStructure';
const API_STRUCTURE_DELETE = '/api/base/structure/deleteStructure';
const API_ENGINEERING_BASEINFO = '/api/sys/engineering-manager/getEngineeringByOrgId';
const API_ENGINEERING_BASEINFO_UPDATE = '/api/sys/engineering-manager/updateEngineeringByOrgId';
const API_ENGINEERING_STAGE = '/api/base/engineering/ranage/selectEngineeringRanages';
const API_ENGINEERING_STAGE_SET = '/api/base/engineering/ranage/updateEngineeringRanage';

/** 角色 */

/**
 * 获取角色列表
 */
const getRoleList = ({ commit }, id) => {
  return Http.get(API_ROLE_LIST, { orgId: id }).then(data => {
    commit('setState', {
      roleList: data,
    });
  });
};

/**
 * 新增角色
 * @param {String} orgId
 * @param {String} parentId
 * @param {String} roleName
 * @param {String} remark
 */
const addRole = (ctx, {
  orgId,
  parentId,
  roleName,
  remark,
} = {}) => {
  return Http.post(API_ROLE_ADD, {
    orgId,
    parentId,
    roleName,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 更新角色
 * @param {String} id
 * @param {String} roleName
 * @param {String} remark
 */
const updateRole = (ctx, {
  id,
  roleName,
  remark,
}) => {
  return Http.post(API_ROLE_UPDATE, {
    id,
    roleName,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 删除角色
 * @param {String} id
 */
const removeRole = (ctx, id) => {
  return Http.post(API_ROLE_REMOVE, { id });
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

/** 组织 */

/**
 * 获取组织树
 * @param {String} orgId 组织编号
 */
const getStructureList = ({ commit }, orgId) => {
  return Http.get(API_STRUCTURE_LIST, { orgId }).then(data => {
    commit('setState', {
      structureList: data,
    });
  });
};

/**
 * 更新组织
 * @param {String} id 组织节点编号（structureId）
 * @param {String} structureName 组织节点名称
 * @param {String} remark 备注
 */
const updateStructure = (ctx, {
  id,
  structureName,
  remark,
}) => {
  return Http.post(API_STRUCTURE_UPDATE, {
    id,
    structureName,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 新增组织节点
 * @param {String} orgId 组织编号
 * @param {String} parentId 父组织节点编号（structureId）
 * @param {String} structureName 组织节点名称
 * @param {String} remark 备注
 */
const insertStructure = (ctx, {
  orgId,
  parentId,
  structureName,
  remark,
}) => {
  return Http.post(API_STRUCTURE_INSERT, {
    orgId,
    parentId,
    structureName,
    remark: encodeURIComponent(remark),
  });
};

/**
 * 删除组织节点
 * @param {String} id 组织节点编号（structureId）
 */
const deleteStructure = (ctx, id) => {
  return Http.post(API_STRUCTURE_DELETE, { id });
};

/** 基本信息 */

/**
 * 获取组织基本信息
 * @param {String} orgId
 */
const getBaseInfo = async ({ commit }, orgId) => {
  return Http.get(API_ENGINEERING_BASEINFO, { orgId }).then(data => {
    commit('setState', {
      baseInfo: {
        ...data,
      },
    });
  });
};

/**
 * 获取组织基本信息
 */
const setBaseInfo = async (ctx, {
  orgId,
  abbreviation,
  constructionUnit,
  engineeringName,
  latitude,
  longitude,
  projecLeader,
  qualityInspectionStation,
  qualityInspectionUnit,
  safeMan,
  technicalLeader,
  address,
} = {}) => {
  return Http.post(API_ENGINEERING_BASEINFO_UPDATE, {
    orgId,
    abbreviation,
    constructionUnit,
    engineeringName,
    latitude,
    longitude,
    projecLeader,
    qualityInspectionStation,
    qualityInspectionUnit,
    safeMan,
    technicalLeader,
    address,
  });
};

/**
 * 获取项目状态
 */
const getEngineeringStage = () => {
  return Http.get(API_ENGINEERING_STAGE);
};

/**
 * 更新项目
 * @param {String} id 关联编号
 * @param {String} engineeringStage 项目状态
 * @param {String} personInCharge 负责人
 */
const setEngineeringStage = (ctx, {
  id,
  engineeringStage,
  personInCharge,
}) => {
  return Http.post(API_ENGINEERING_STAGE_SET, {
    id,
    engineeringStage,
    personInCharge,
  });
};

export default {
  namespaced: true,
  state: {
    roleList: [],
    permissionList: [],
    structureList: [],
    baseInfo: {},
    departmentStageOptions: [{
      label: '未开始',
      value: '1',
    }, {
      label: '进行中',
      value: '2',
    }, {
      label: '已完成',
      value: '3',
    }],
  },
  getters: {},
  mutations: {
    setState(s, payload) { s = Object.assign(s, payload); },
  },
  actions: {
    getRoleList,
    addRole,
    updateRole,
    removeRole,
    getPermissionList,
    updatePermissionList,
    getStructureList,
    updateStructure,
    insertStructure,
    deleteStructure,
    getBaseInfo,
    setBaseInfo,
    getEngineeringStage,
    setEngineeringStage,
  },
};
