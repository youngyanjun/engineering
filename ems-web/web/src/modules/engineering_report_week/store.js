import Http from '@/utils/http';

const API_WEEKLY_CREATE = '/api/web/report/weeklyreport/insert/insertBaseInfo';
const API_WEEKLY_SUBMIT = '/api/web/report/weeklyreport/insert/submitReport';
const API_WEEKLY_GET = '/api/web/report/weeklyreport/get/getWeekly';
const API_WEEKLY_FIELD = '/api/web/report/Assist/getDictionaryList';
const API_WEEKLY_REQUIRE = '/api/web/report/weeklyreport/get/selectMustInfo';

// 表单更新
const API_WEEKLY_BASEINFO_INSERT = '/api/web/report/weeklyreport/insert/editBaseInfo';
const API_WEEKLY_BASEINFO_GET = '/api/web/report/weeklyreport/get/selectBaseInfo';
const API_WEEKLY_STAGEINFO_INSERT = '/api/web/report/weeklyreport/insert/insertStageInfo';
const API_WEEKLY_STAGEINFO_GET = '/api/web/report/weeklyreport/get/selectStageInfo';
const API_WEEKLY_STAGEINFO_PERSON_GET = '/api/web/report/weeklyreport/get/selectPersonInfo';
const API_WEEKLY_STAGEINFO_MACHINE_GET = '/api/web/report/weeklyreport/get/selectMachineInfo';
const API_WEEKLY_NEXTPLAN_INSERT = '/api/web/report/weeklyreport/insert/insertNextPlan';
const API_WEEKLY_NEXTPLAN_GET = '/api/web/report/weeklyreport/get/selectNextPlan';
const API_WEEKLY_IMAGE_INSERT = '/api/web/report/weeklyreport/insert/insertImageStage';
const API_WEEKLY_IMAGE_GET = '/api/web/report/weeklyreport/get/selectImageStage';
const API_WEEKLY_IMAGE_UPLOAD = '/api/sys/file/upload';
const API_WEEKLY_SITE_RECTIFICATION_INSERT = '/api/web/report/weeklyreport/insert/insertSiteRectify';
const API_WEEKLY_SITE_RECTIFICATION_GET = '/api/web/report/weeklyreport/get/selectSiteRectify';
const API_WEEKLY_WORK_SAFETY_INSERT = '/api/web/report/weeklyreport/insert/insertSafeInfo';
const API_WEEKLY_WORK_SAFETY_GET = '/api/web/report/weeklyreport/get/selectSafeInfo';
const API_WEEKLY_DATA_SUMMARY_INSERT = '/api/web/report/weeklyreport/insert/insertInOutFile';
const API_WEEKLY_DATA_SUMMARY_GET = '/api/web/report/weeklyreport/get/selectInOutFile';
const API_WEEKLY_AFFECT_FACTORS_INSERT = '/api/web/report/weeklyreport/insert/insertEffect';
const API_WEEKLY_AFFECT_FACTORS_GET = '/api/web/report/weeklyreport/get/selectEffect';
const API_WEEKLY_OTHER_REQUIREMENTS_INSERT = '/api/web/report/weeklyreport/insert/insertSiteOtherInfo';
const API_WEEKLY_OTHER_REQUIREMENTS_GET = '/api/web/report/weeklyreport/get/selectSiteOtherInfo';
const API_WEEKLY_FORECAST_INSERT = '/api/report/report-forecast/addForecast';
const API_WEEKLY_FORECAST_GET = '/api/report/report-forecast/queryForecast';

// 填写辅助
const API_WEEKLY_BASEINFO_HELP = '/api/sys/engineering-manager/getEngineeringByOrgId';
const API_WEEKLY_NEXT_PERSON = '/api/web/report/Assist/getNextPerson';
const API_WEEKLY_NEXT_MACHINE = '/api/web/report/Assist/getNextMachine';
const API_WEEKLY_NEXT_STAGEINFO = '/api/web/report/Assist/getNextStageInfo';

const storeState = {
  reportId: '',
  baseInfo: {},
  week: [],
  field: {
    problemTypes: [],
    contractTypes: [],
  },
  help: {
    baseInfo: {},
  },
  weekConfig: {
    progress: {
      table: {
        coordination: {
          title: '需要领导协调事项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        drawings: {
          title: '图纸设计情况（包括图纸变更）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '提出日期',
            key: 'presentationDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        contract: {
          title: '商务合同（分包、外协、安装合同等）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '提出日期',
            key: 'presentationDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        civilengineeringSub: {
          title: '土建施工分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        prefabricatedSub: {
          title: '装配施工分项（钢结构、板材集成材料吊装）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        waterpowerSub: {
          title: '水电安装施工分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        havcSub: {
          title: '暖通施工分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        decorationSub: {
          title: '装饰装修工程分项（卫浴、非集成项目）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        otherSub: {
          title: '其它附属工程分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
      },
      personnelOption: [
        {
          label: '管理人员',
          value: 'manager',
        },
        {
          label: '土建',
          value: 'civilengineering',
        },
        {
          label: '装配工种',
          value: 'prefabricated',
        },
        {
          label: '水电安装',
          value: 'waterPowerInstall',
        },
        {
          label: '暖通安装',
          value: 'havcInstall',
        },
        {
          label: '装饰装修',
          value: 'correct',
        },
        {
          label: '室外工程',
          value: 'outdoor',
        },
        {
          label: '其他',
          value: 'other',
        },
      ],
      machineOption: [
        {
          label: '汽车泵',
          value: 'autoPumps',
        },
        {
          label: '电焊机',
          value: 'electricWeldingMachine',
        },
        {
          label: '切割机',
          value: 'cutter',
        },
        {
          label: '砼运输车',
          value: 'concreteTransport',
        },
        {
          label: '叉车',
          value: 'forklift',
        },
        {
          label: '料运输车',
          value: 'materialTransport',
        },
        {
          label: '汽车起重机',
          value: 'craneTruck',
        },
        {
          label: '其他设备',
          value: 'other',
        },
      ],
    },
    nextplan: {
      table: {
        coordination: {
          title: '需要领导协调事项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        drawings: {
          title: '图纸设计情况（包括图纸变更）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '提出日期',
            key: 'presentationDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        contract: {
          title: '商务合同（分包、外协、安装合同等）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '提出日期',
            key: 'presentationDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        civilengineeringSub: {
          title: '土建施工分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        prefabricatedSub: {
          title: '装配施工分项（钢结构、板材集成材料吊装）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        waterpowerSub: {
          title: '水电安装施工分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        havcSub: {
          title: '暖通施工分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        decorationSub: {
          title: '装饰装修工程分项（卫浴、非集成项目）',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
        otherSub: {
          title: '其它附属工程分项',
          content: [{
            name: '内容',
            key: 'content',
          }, {
            name: '计划书完成日期',
            key: 'proposalCompletionDate',
            type: 'date',
          }, {
            name: '需要完成时间',
            key: 'manhour',
            type: 'date',
          }, {
            name: '目前状况',
            key: 'status',
          }, {
            name: '协调结果',
            key: 'result',
          }],
        },
      },
      personnelOption: [
        {
          label: '管理人员',
          value: 'manager',
        },
        {
          label: '土建',
          value: 'civilengineering',
        },
        {
          label: '装配工种',
          value: 'prefabricated',
        },
        {
          label: '水电安装',
          value: 'waterPowerInstall',
        },
        {
          label: '暖通安装',
          value: 'havcInstall',
        },
        {
          label: '装饰装修',
          value: 'correct',
        },
        {
          label: '室外工程',
          value: 'outdoor',
        },
        {
          label: '其他',
          value: 'other',
        },
      ],
      machineOption: [
        {
          label: '汽车泵',
          value: 'autoPumps',
        },
        {
          label: '电焊机',
          value: 'electricWeldingMachine',
        },
        {
          label: '切割机',
          value: 'cutter',
        },
        {
          label: '砼运输车',
          value: 'concreteTransport',
        },
        {
          label: '叉车',
          value: 'forklift',
        },
        {
          label: '料运输车',
          value: 'materialTransport',
        },
        {
          label: '汽车起重机',
          value: 'craneTruck',
        },
        {
          label: '其他设备',
          value: 'other',
        },
      ],
    },
    siteRectification: {
      table: {
        rectification: {
          title: '现场整改纪要',
          content: [{
            name: '问题种类',
            key: 'type',
          }, {
            name: '需整改问题',
            key: 'content',
          }, {
            name: '整改结果',
            key: 'result',
          }],
        },
      },
    },
    workSafety: {
      table: {
        dangerSource: {
          title: '本周重大危险源',
          content: [{
            name: '重大危险源',
            key: 'dangerSource',
          }, {
            name: '可能发生的区域',
            key: 'mightHappenArea',
          }, {
            name: '所采取的措施',
            key: ' measuresTaken',
          }],
        },
        nextDangerSource: {
          title: '下周预测重大危险源',
          content: [{
            name: '重大危险源',
            key: 'dangerSource',
          }, {
            name: '可能发生的区域',
            key: 'mightHappenArea',
          }, {
            name: '所采取的措施',
            key: ' measuresTaken',
          }],
        },
        siteCheck: {
          title: '本周现场检查情况',
          rowEditabled: false,
          content: [
            {
              name: '编号',
              key: 'id',
              align: true,
              width: '10%',
            }, {
              name: '单项',
              key: 'type',
              align: true,
              width: '10%',
            }, {
              name: '内容',
              key: 'content',
              width: '50%',
            }, {
              name: '是否满足要求',
              key: 'fit',
              width: '10%',
            }, {
              name: '备注',
              key: 'remark',
              width: '20%',
            },
          ],
        },
      },
      siteCheck: [
        {
          id: 1,
          type: '现场总览',
          content: [
            '施工现场是否封闭',
            '临设是否满足要求，项目公示牌是否达标',
            '工地是否设有急救箱',
            '三级安全教育培训',
            '周围是否有危险源对场地造成伤害',
          ],
          fit: [
            '',
            '',
            '',
            '',
            '',
          ],
          remark: [
            '',
            '',
            '',
            '',
            '',
          ],
        },
        {
          id: 2,
          type: '材料堆放',
          content: [
            '各种材料堆放是否按总平图整齐、有序、分类',
            '应当按照品种、规格堆放，并设明显标牌',
            '选择位置是否远离火源，并且场地坚实、平坦。材料堆场配备防火设施',
          ],
          fit: [
            '',
            '',
            '',
          ],
          remark: [
            '',
            '',
            '',
          ],
        },
        {
          id: 3,
          type: '安全、文明标识',
          content: [
            '重大危险源警示标识，基坑、洞口围栏，起重设备周围，高空作业警示牌等',
            '标识标牌位置是否清晰合理',
          ],
          fit: [
            '',
            '',
          ],
          remark: [
            '',
            '',
          ],
        },
        {
          id: 4,
          type: '安全设施',
          content: [
            '安全围栏是否合理设置',
            '警戒线，安全网生命线的设置是否合理',
            '安全帽、安全带是否发放到位；作业人员按规佩戴',
            '灭火器，黄沙是否设置及合理',
          ],
          fit: [
            '',
            '',
            '',
            '',
          ],
          remark: [
            '',
            '',
            '',
            '',
          ],
        },
        {
          id: 5,
          type: '施工用电',
          content: [
            '临时配电盘是否装设接地，开关，断路器正常',
            '电工作业人员无证上岗，电箱为专人管理',
            '有无定期巡检',
            '有无裸线情况发生',
            '接零保护系统不符合要求、电箱下引出线混乱',
            '有无非电工私拉乱接电线',
            '电缆架设或埋设是否要求',
          ],
          fit: [
            '',
            '',
            '',
            '',
            '',
            '',
            '',
          ],
          remark: [
            '',
            '',
            '',
            '',
            '',
            '',
            '',
          ],
        },
        {
          id: 6,
          type: '卫生',
          content: [
            '是否配备常用应急药物',
            '胸牌及帘是否配备及编号',
            '食堂、职工宿舍是否符合卫生要求',
            '办公区的卫生状况是否满足要求',
            '施工现场建筑垃圾是否统一堆放和及时清理',
          ],
          fit: [
            '',
            '',
            '',
            '',
            '',
          ],
          remark: [
            '',
            '',
            '',
            '',
            '',
          ],
        },
      ],
    },
    affectFactors: {
      table: {
        effect: {
          title: '可能影响工程的因素',
          content: [{
            name: '内容',
            key: 'content',
          }],
        },
      },
    },
    dataSummary: {
      table: {
        receiveFileIn: {
          title: '公司内部联络',
          content: [{
            name: '内容',
            key: 'content',
          }],
        },
        receiveFileOut: {
          title: '外部联络',
          content: [{
            name: '内容',
            key: 'content',
          }],
        },
      },
    },
    otherRequirements: {
      table: {
        otherSiteInfo: {
          title: '',
          content: [{
            name: '内容',
            key: 'content',
          }],
        },
      },
    },
    weather: {
      forecastOption: [{
        label: '天气',
        value: 'conditionDay',
      }, {
        label: '温度',
        value: 'tempDay',
      }, {
        label: '风力',
        value: 'windDirDay',
      }],
    },
  },
};

/**
 * 获取周次
 */
const getWeek = ({ commit }, id) => {
  return Http.get(API_WEEKLY_GET, { id }).then(data => {
    commit('setState', { week: data || [] });
  });
};

/**
 * 创建周报
 */
const createRecord = (ctx, {
  organizationId,
  constructionDateFinis,
  constructionDateStart,
  weeklyNum,
} = {}) => {
  return Http.post(API_WEEKLY_CREATE, {
    organizationId,
    constructionDateFinis,
    constructionDateStart,
    weeklyNum,
  });
};

/**
 * 新增基本信息
 */
const addBaseInfo = (ctx, {
  id,
  organizationId,
  completionRate,
  constructionDateFinis,
  constructionDateStart,
  constructionName,
  constructionUnit,
  contractPrice,
  moneyBack,
  projecLeader,
  qualityInspectionStation,
  qualityInspectionUnit,
  reportBy,
  safeMan,
  technicalLeader,
  weeklyNum,
} = {}) => {
  return Http.post(API_WEEKLY_BASEINFO_INSERT, {
    id,
    organizationId,
    completionRate,
    constructionDateFinis,
    constructionDateStart,
    constructionName,
    constructionUnit,
    contractPrice,
    moneyBack,
    projecLeader,
    qualityInspectionStation,
    qualityInspectionUnit,
    reportBy,
    safeMan,
    technicalLeader,
    weeklyNum,
  });
};

/**
 * 获取基本信息
 */
const getBaseInfo = ({ commit }, id) => {
  return Http.get(API_WEEKLY_BASEINFO_GET, { id }).then(data => {
    commit('setState', {
      baseInfo: {
        ...data,
      },
    });
    return data;
  });
};

/**
 * 新增项目进展情况
 */
const addProgress = (ctx, {
  id,
  civilengineeringSub,
  contract,
  coordination,
  decorationSub,
  drawings,
  havcSub,
  machine = [],
  otherSub,
  personnel = [],
  prefabricatedSub,
  stageInfoRemark,
  waterpowerSub,
} = {}) => {
  return Http.post(API_WEEKLY_STAGEINFO_INSERT, {
    id,
    civilengineeringSub: JSON.stringify(civilengineeringSub),
    contract: JSON.stringify(contract),
    coordination: JSON.stringify(coordination),
    decorationSub: JSON.stringify(decorationSub),
    drawings: JSON.stringify(drawings),
    havcSub: JSON.stringify(havcSub),
    machine,
    otherSub: JSON.stringify(otherSub),
    personnel,
    prefabricatedSub: JSON.stringify(prefabricatedSub),
    stageInfoRemark,
    waterpowerSub: JSON.stringify(waterpowerSub),
  });
};

/**
 * 获取项目进展情况
 */
const getProgress = (ctx, id) => {
  return Http.get(API_WEEKLY_STAGEINFO_GET, { id });
};

/**
 * 获取项目进展情况-人员信息
 */
const getProgressPersonnel = (ctx, {id, type } = {}) => {
  return Http.get(API_WEEKLY_STAGEINFO_PERSON_GET, { id, type });
};

/**
 * 获取项目进展情况-机械
 */
const getProgressMachine = (ctx, {id, type } = {}) => {
  return Http.get(API_WEEKLY_STAGEINFO_MACHINE_GET, { id, type });
};

/**
 * 新增下周计划及安排
 */
const addNextplan = (ctx, {
  id,
  civilengineeringSub,
  contract,
  coordination,
  decorationSub,
  drawings,
  havcSub,
  machine = [],
  otherSub,
  personnel = [],
  prefabricatedSub,
  stageInfoRemark,
  waterpowerSub,
} = {}) => {
  return Http.post(API_WEEKLY_NEXTPLAN_INSERT, {
    id,
    civilengineeringSub: JSON.stringify(civilengineeringSub),
    contract: JSON.stringify(contract),
    coordination: JSON.stringify(coordination),
    decorationSub: JSON.stringify(decorationSub),
    drawings: JSON.stringify(drawings),
    havcSub: JSON.stringify(havcSub),
    machine,
    otherSub: JSON.stringify(otherSub),
    personnel,
    prefabricatedSub: JSON.stringify(prefabricatedSub),
    stageInfoRemark,
    waterpowerSub: JSON.stringify(waterpowerSub),
  });
};

/**
 * 获取下周计划及安排
 */
const getNextplan = (ctx, id) => {
  return Http.get(API_WEEKLY_NEXTPLAN_GET, { id });
};

/**
 * 上传图片
 */
const uploadImage = (ctx, file) => {
  const formdata = new FormData();
  formdata.append('file', file);
  return Http.post(API_WEEKLY_IMAGE_UPLOAD, formdata, {});
};

/**
 * 新增形象进度
 */
const addSchedule = (ctx, { id, list } = {}) => {
  return Http.post(API_WEEKLY_IMAGE_INSERT, {
    bizId: id,
    sysFileVOS: list,
  });
};

/**
 * 获取形象进度
 */
const getSchedule = (ctx, id) => {
  return Http.get(API_WEEKLY_IMAGE_GET, {
    bizId: id,
  });
};

/**
 * 新增现场整改纪要
 */
const addSiteRectification = async (ctx, {
  id,
  rectification,
} = {}) => {
  return Http.post(API_WEEKLY_SITE_RECTIFICATION_INSERT, {
    id,
    rectification: JSON.stringify(rectification),
  });
};

/**
 * 获取现场整改纪要
 */
const getSiteRectification = (ctx, id) => {
  return Http.get(API_WEEKLY_SITE_RECTIFICATION_GET, { id });
};

/**
 * 新增安全生产情况
 */
const addWorkSafety = (ctx, {
  id,
  dangerSource,
  nextDangerSource,
  safeInfo,
  siteCheck,
} = {}) => {
  return Http.post(API_WEEKLY_WORK_SAFETY_INSERT, {
    id,
    dangerSource: JSON.stringify(dangerSource),
    nextDangerSource: JSON.stringify(nextDangerSource),
    safeInfo,
    siteCheck: JSON.stringify(siteCheck),
  });
};

/**
 * 获取安全生产情况
 */
const getWorkSafety = (ctx, id) => {
  return Http.get(API_WEEKLY_WORK_SAFETY_GET, { id });
};

/**
 * 新增本周收发资料汇总
 */
const addDataSummary = (ctx,  {
  id,
  receiveFileIn,
  receiveFileOut,
} = {}) => {
  return Http.post(API_WEEKLY_DATA_SUMMARY_INSERT, {
    id,
    receiveFileIn: JSON.stringify(receiveFileIn),
    receiveFileOut: JSON.stringify(receiveFileOut),
  });
};

/**
 * 获取本周收发资料汇总
 */
const getDataSummary = (ctx, id) => {
  return Http.get(API_WEEKLY_DATA_SUMMARY_GET, { id });
};

/**
 * 新增可能影响工程的因素
 */
const addAffectFactors = (ctx, { id, effect } = {}) => {
  return Http.post(API_WEEKLY_AFFECT_FACTORS_INSERT, {
    id,
    effect: JSON.stringify(effect),
  });
};

/**
 * 获取可能影响工程的因素
 */
const getAffectFactors = (ctx, id) => {
  return Http.get(API_WEEKLY_AFFECT_FACTORS_GET, { id });
};

/**
 * 新增现场其他需要说明
 */
const addOtherRequirements = (ctx, { id, otherSiteInfo } = {}) => {
  return Http.post(API_WEEKLY_OTHER_REQUIREMENTS_INSERT, {
    id,
    otherSiteInfo: JSON.stringify(otherSiteInfo),
  });
};

/**
 * 获取现场其他需要说明
 */
const getOtherRequirements = (ctx, id) => {
  return Http.get(API_WEEKLY_OTHER_REQUIREMENTS_GET, { id });
};

/**
 * 提交报表
 */
const submitReport = (ctx, id) => {
  return Http.post(API_WEEKLY_SUBMIT, { reportId: id });
};

/**
 * 获取自定义字段
 */
const getField = ({ commit }, type) => {
  let id;
  switch (type) {
    case 'problemTypes': id = 1; break;
    case 'contractTypes': id = 2; break;
    default:
  }
  return Http.get(API_WEEKLY_FIELD, { id }).then(data => {
    commit('setField', {
      [type]: JSON.parse(data.dictionaryChild || '[]'),
    });
  });
};

/**
 * 获取天气
 * @param {String} reportId 周报编号
 * @param {String} engineeringId
 * @param {String} startDate e.g. 2019-11-02
 * @param {String} endDate e.g. 2019-11-08
 */
const getForecast = (ctx, {
  reportId,
  engineeringId,
  startDate,
  endDate,
} = {}) => {
  return Http.get(API_WEEKLY_FORECAST_GET, {
    reportId,
    projectId: engineeringId,
    startDate,
    endDate,
  });
};

/**
 * 新增天气
 * @param {String} projectId
 * @param {String} reportId
 * @param {Array} reportForecastVOList
 * @param {String} reportForecastVOList.conditionDay 天气情况 e.g. 多云
 * @param {String} reportForecastVOList.predictDate 日期 e.g. 2019-11-02
 * @param {String} reportForecastVOList.tempDay 白天温度 e.g. 5
 * @param {String} reportForecastVOList.tempNight 夜晚温度 e.g. 10
 * @param {String} reportForecastVOList.windDirDay 风力 e.g. 东南风
 */
const addForecast = (ctx, {
  projectId,
  reportId,
  reportForecastVOList,
}) => {
  return Http.post(API_WEEKLY_FORECAST_INSERT, {
    projectId,
    reportId,
    reportForecastVOList,
  });
};

/**
 * 获取基本信息帮助
 * @param {String} orgId 组织编号
 */
const getBaseInfoHelp = ({ commit }, orgId) => {
  return Http.get(API_WEEKLY_BASEINFO_HELP, { orgId }).then(data => {
    commit('setHelp', {
      baseInfo: data,
    });
  });
};

/**
 * 获取施工人员帮助
 */
const getNextPerson = (ctx, { weekly, engineeringId } = {}) => {
  return Http.get(API_WEEKLY_NEXT_PERSON, { weekly, engineeringId });
};

/**
 * 获取施工机械帮助
 */
const getNextMachine = (ctx, { weekly, engineeringId } = {}) => {
  return Http.get(API_WEEKLY_NEXT_MACHINE, { weekly, engineeringId });
};

/**
 * 获取项目进度帮助
 */
const getNextProgress = (ctx, { weekly, engineeringId } = {}) => {
  return Http.get(API_WEEKLY_NEXT_STAGEINFO, { weekly, engineeringId });
};

/**
 * 检测必填项
 */
const getRequire = (ctx, id) => {
  return Http.get(API_WEEKLY_REQUIRE, { id });
};

const getters = {};

const mutations = {
  setState: (s, payload) => { s = Object.assign(s, payload); },
  setField: (s, payload) => { s.field = Object.assign({}, s.field, payload); },
  setHelp: (s, payload) => { s.help = Object.assign({}, s.help, payload); },
};

const actions = {
  createRecord,
  submitReport,
  getWeek,
  getField,
  getBaseInfoHelp,
  addBaseInfo,
  getBaseInfo,
  addProgress,
  getProgress,
  getProgressPersonnel,
  getProgressMachine,
  addNextplan,
  getNextplan,
  uploadImage,
  addSchedule,
  getSchedule,
  addSiteRectification,
  getSiteRectification,
  addWorkSafety,
  getWorkSafety,
  addDataSummary,
  getDataSummary,
  addAffectFactors,
  getAffectFactors,
  addOtherRequirements,
  getOtherRequirements,
  addForecast,
  getForecast,
  getNextPerson,
  getNextMachine,
  getNextProgress,
  getRequire,
};

export default {
  namespaced: true,
  state: storeState,
  getters,
  mutations,
  actions,
};
