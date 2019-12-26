<template>
  <div :class="$style.content" ref="content">
    <!--startprint-->
    <div :class="$style.main" ref="base">
      <div :class="$style.hd">
        <span>
          {{ baseInfo.reportName }}（{{ baseInfo.constructionDateStart | dateFormat('yyyy-MM-dd') }} - {{ baseInfo.constructionDateFinis | dateFormat('yyyy-MM-dd') }}）
          <i />
        </span>
      </div>
      <div :class="$style.bd">
        <!-- 基本信息 -->
        <div :class="$style.title">基本信息</div>
        <Divider />
        <div :class="$style['form']">
          <div :class="$style['group']">
            <label :class="$style['group-label']">工程名称：</label>
            <span :class="$style['group-value']">{{ baseInfo.constructionName }}</span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">建设单位：</label>
            <span :class="$style['group-value']">{{ baseInfo.constructionUnit }}</span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">监理单位：</label>
            <span :class="$style['group-value']">{{ baseInfo.qualityInspectionUnit }}</span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">质监站：</label>
            <span :class="$style['group-value']">{{ baseInfo.qualityInspectionStation }}</span>
          </div>
          <Divider shape="dashed" />
          <div :class="$style['group']">
            <label :class="$style['group-label']">项目经理：</label>
            <span :class="$style['group-value']">{{ baseInfo.projecLeader }}</span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">技术负责人：</label>
            <span :class="$style['group-value']">{{ baseInfo.technicalLeader }}</span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">安全员：</label>
            <span :class="$style['group-value']">{{ baseInfo.safeMan }}</span>
          </div>
          <Divider shape="dashed" />
          <div :class="$style['group']">
            <label :class="$style['group-label']">合同总价：</label>
            <span :class="$style['group-value']">{{ baseInfo.contractPrice }} 万元</span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">已收款：</label>
            <span :class="$style['group-value']">{{ baseInfo.moneyBack }} 万元</span>
          </div>
          <Divider shape="dashed" />
          <div :class="$style['group']">
            <label :class="$style['group-label']">周次：</label>
            <span :class="$style['group-value']">第{{ baseInfo.weeklyNum }}周</span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">施工周期：</label>
            <span :class="$style['group-value']">
              {{ baseInfo.constructionDateStart | dateFormat('yyyy-MM-dd') }} ~ {{ baseInfo.constructionDateFinis | dateFormat('yyyy-MM-dd') }}
            </span>
          </div>
          <div :class="$style['group']">
            <label :class="$style['group-label']">报送者：</label>
            <span :class="$style['group-value']">{{ baseInfo.reportBy }}</span>
          </div>
          <Divider shape="dashed" />
          <div :class="$style['group']">
            <label :class="$style['group-label']">完工率：</label>
            <span :class="$style['group-value']">{{ baseInfo.completionRate }}</span>
          </div>
        </div>
        <!-- 项目进展情况 -->
        <div :class="$style.title" ref="progress">项目进展情况</div>
        <Divider />
        <div :class="$style['field']" v-for="k in Object.keys(config.progress.table)">
          <div :class="$style['field-hd']">{{ config.progress.table[k].title }}</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.progress.table[k].content"
            :tbody="progress[k]"
          />
        </div>
        <div class="a4"></div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周施工人员及机械</div>
          <div :class="$style['field-bd']">
            <EditedTable
              :treadonly="true"
              :rowEditabled="false"
              :thead="config.personnel"
              :tbody="progress.personnel"
            />
          </div>
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-bd']">
            <EditedTable
              :treadonly="true"
              :rowEditabled="false"
              :thead="config.machine"
              :tbody="progress.machine"
            />
          </div>
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">备注</div>
          <div :class="$style['field-bd']">
            {{ progress.stageInfoRemark || '无' }}
          </div>
        </div>
        <div class="a4"></div>
        <!-- 形象进度 -->
        <div :class="$style.title" ref="schedule">形象进度</div>
        <Divider />
        <div :class="$style['field']">
          <div :class="$style['thumb']" >
            <div :class="$style['thumb-item']" v-for="item in schedule">
              <img :class="$style['thumb-img']" :src="item.fileName" alt="图片" />
              <span :class="$style['thumb-description']">{{ item.remark }}</span>
            </div>
          </div>
        </div>
        <!-- 下周计划及安排 -->
        <div :class="$style.title" ref="nextplan">下周计划及安排</div>
        <Divider />
        <div :class="$style['field']" v-for="k in Object.keys(config.progress.table)">
          <div :class="$style['field-hd']">{{ config.progress.table[k].title }}</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.progress.table[k].content"
            :tbody="nextplan[k]"
          />
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">施工人员及机械</div>
          <div :class="$style['field-bd']">
            <EditedTable
              :treadonly="true"
              :rowEditabled="false"
              :thead="config.nextPersonnel"
              :tbody="nextplan.personnel"
            />
          </div>
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-bd']">
            <EditedTable
              :treadonly="true"
              :rowEditabled="false"
              :thead="config.nextMachine"
              :tbody="nextplan.machine"
            />
          </div>
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">备注</div>
          <div :class="$style['field-bd']">
            {{ nextplan.stageInfoRemark || '无' }}
          </div>
        </div>
        <!-- 现场整改纪要 -->
        <div :class="$style.title" ref="siteRectification">现场整改纪要</div>
        <Divider />
        <div :class="$style['field']">
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.siteRectification.table.rectification.content"
            :tbody="siteRectification.rectification"
          />
        </div>
        <!-- 安全生产情况 -->
        <div :class="$style.title" ref="workSafety">安全生产情况</div>
        <Divider />
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周安全生产情况</div>
          <div :class="$style['field-bd']">
            {{ workSafety.safeInfo }}
          </div>
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周重大危险源</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.workSafety.table.dangerSource.content"
            :tbody="workSafety.dangerSource"
          />
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">下周预测重大危险源</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.workSafety.table.nextDangerSource.content"
            :tbody="workSafety.nextDangerSource"
          />
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周现场检查情况</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.workSafety.table.siteCheck.content"
            :tbody="workSafety.siteCheck"
          />
        </div>
        <!-- 本周收发资料汇总 -->
        <div :class="$style.title" ref="dataSummary">本周收发资料汇总</div>
        <Divider />
        <div :class="$style['field']">
          <div :class="$style['field-hd']">公司内部联络</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.dataSummary.table.receiveFileIn.content"
            :tbody="dataSummary.receiveFileIn"
          />
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">外部联络</div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.dataSummary.table.receiveFileOut.content"
            :tbody="dataSummary.receiveFileOut"
          />
        </div>
        <!-- 可能影响工程的因素 -->
        <div :class="$style.title" ref="affectFactors">可能影响工程的因素</div>
        <Divider />
        <div :class="$style['field']">
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.affectFactors.table.content"
            :tbody="affectFactors.effect"
          />
        </div>
        <!-- 现场其他需要说明 -->
        <div :class="$style.title" ref="otherRequirements">现场其他需要说明</div>
        <Divider />
        <div :class="$style['field']">
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.otherRequirements.table.content"
            :tbody="otherRequirements.otherRequirements"
          />
        </div>
        <!-- 天气情况 -->
        <div :class="$style.title" ref="weather">天气状况</div>
        <Divider />
        <div :class="$style['field']">
          <div :class="$style['field-hd']">本周天气实况</div>
          <EditedTable
            :rowEditabled="false"
            :thead="config.forecast.content"
            :tbody="weather.forecast"
          />
        </div>
        <div :class="$style['field']">
          <div :class="$style['field-hd']">下周天气预报<span :class="$style.danger">（预测信息，不可修改）</span></div>
          <EditedTable
            :treadonly="true"
            :rowEditabled="false"
            :thead="config.nextForecast.content"
            :tbody="weather.nextForecast"
          />
        </div>
        <!-- 驳回意见 -->
        <div :class="$style.title" ref="rejection">驳回意见</div>
        <Divider />
        <div :class="$style['field']" v-for="item in []">
          <label>{{ item.title }} | {{ item.date }} </label>
          <div>{{ item.content || '暂无' }}</div>
        </div>
        <div v-if="[].length <= 0">暂无回复</div>
      </div>
    </div>
    <!--endprint-->
  </div>
</template>

<script>
import { Divider } from '@/components/ls';
import EditedTable from '../../add/component/edited_table';

export default {
  name: '',
  components: { Divider, EditedTable },
  props: {
    config: {
      type: Object,
      default: () => {},
    },
    baseInfo: {
      type: Object,
      default: () => {},
    },
    progress: {
      type: Object,
      default: () => {},
    },
    schedule: {
      type: Array,
      default: () => [],
    },
    nextplan: {
      type: Object,
      default: () => {},
    },
    siteRectification: {
      type: Object,
      default: () => {},
    },
    otherRequirements: {
      type: Object,
      default: () => {},
    },
    affectFactors: {
      type: Object,
      default: () => {},
    },
    dataSummary: {
      type: Object,
      default: () => {},
    },
    workSafety: {
      type: Object,
      default: () => {},
    },
    weather: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      table: [
        {
          person: '管理人员',
        }, {
          person: '土建',
        }, {
          person: '装配工种',
        }, {
          person: '水电安装',
        }, {
          person: '暖通安装',
        }, {
          person: '装饰装修',
        }, {
          person: '室外工程',
        }, {
          person: '其他',
        },
      ],
    };
  },
  methods: {
    scroll(val) {
      console.log('scroll', [this.$refs[val]]);
      val = this.$refs[val].offsetTop - 22;
      this.$refs.content.scrollTop = val;
    },
  },
};
</script>

<style lang="scss" module>
@import './ui.scss';
</style>
