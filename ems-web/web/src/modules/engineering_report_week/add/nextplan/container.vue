<template>
  <UI
    ref="ui"
    :form="form"
    :config="config"
    :tableHead="tableHead"
    :help="help"
    @submit="handleSubmit"
    @back="handleBack"
  >
    <slot />
  </UI>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  props: {
    reportId: String,
    engineeringId: String,
  },
  data() {
    return {
      form: {},
      tableHead: {
        personnel: {
          content: [
            {
              name: '施工人员 (工日)',
              key: 'person',
              readonly: true,
              minWidth: '20%',
            },
          ],
        },
        machine: {
          content: [
            {
              name: '施工机械 (台班)',
              key: 'person',
              readonly: true,
              minWidth: '20%',
            },
          ],
        },
      },
      help: {
        tableHead: {
          personnel: {
            content: [
              {
                name: '施工人员 (工日)',
                key: 'person',
                readonly: true,
                minWidth: '20%',
              },
            ],
          },
          machine: {
            content: [
              {
                name: '施工机械 (台班)',
                key: 'person',
                readonly: true,
                minWidth: '20%',
              },
            ],
          },
        },
      },
    };
  },
  created() {
    this.init();
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'baseInfo',
      'field',
      'weekConfig',
    ]),
    config() {
      return {
        ...this.weekConfig.nextplan,
        contractTypes: this.field.contractTypes,
        baseInfo: this.baseInfo,
      };
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'addNextplan',
      'getNextplan',
      'getProgressPersonnel',
      'getProgressMachine',
      'getBaseInfo',
      'getField',
      'getNextPerson',
      'getNextMachine',
      'getNextProgress',
      'getProgress',
    ]),
    init() {
      this.getBaseInfo(this.reportId).then(data => {
        this.form = Object.assign({}, this.form, data);
        const tableHead = this.createTableHead(this.baseInfo.constructionDateStart);
        this.tableHead.personnel.content = [...this.tableHead.personnel.content, ...tableHead];
        this.tableHead.machine.content = [...this.tableHead.machine.content, ...tableHead];
        this.initHelp();
      });
      this.getNextplan(this.reportId).then(data => {
        data = data || {};
        // 字段转对象
        Object.keys(data).forEach(k => {
          if (k !== 'id' && k !== 'machine' && k !== 'personnel' && k !== 'stageInfoRemark') {
            data[k] = JSON.parse(data[k]) || [];
          }
        });
        data.personnel = [];
        data.machine = [];
        this.form = Object.assign({}, this.form, data);
      });
      this.getProgressPersonnel({ id: this.reportId, type: 1 }).then(data => {
        const personnel = this.config.personnelOption.map(k => {
          const temp = {};
          temp.person = k.label;
          temp.api = k.value;
          (data || []).forEach(item => {
            temp[item.dateAt.split(' ')[0]] = item[k.value];
          });
          return temp;
        });
        this.form = Object.assign({}, this.form, {
          personnel,
        });
      });
      this.getProgressMachine({ id: this.reportId, type: 1 }).then(data => {
        const machine = this.config.machineOption.map(k => {
          const temp = {};
          temp.person = k.label;
          temp.api = k.value;
          (data || []).forEach(item => {
            temp[item.dateAt.split(' ')[0]] = item[k.value];
          });
          return temp;
        });
        this.form = Object.assign({}, this.form, {
          machine,
        });
      });
      this.getField('contractTypes');
    },
    initHelp() {
      this.getProgress(this.reportId).then(data => {
        data = data || {};
        // 字段转对象
        Object.keys(data).forEach(k => {
          if (k !== 'id' && k !== 'machine' && k !== 'personnel' && k !== 'stageInfoRemark') {
            data[k] = JSON.parse(data[k]) || [];
          }
        });
        data.personnel = [];
        data.machine = [];
        this.help = Object.assign({}, this.help, data);
      });

      const tableHeadHelp = this.createTableHead(this.baseInfo.constructionDateStart, 7);
      this.help.tableHead.personnel.content = [...this.help.tableHead.personnel.content, ...tableHeadHelp];
      this.help.tableHead.machine.content = [...this.help.tableHead.machine.content, ...tableHeadHelp];
      this.getProgressPersonnel({ id: this.reportId, type: 0 }).then(data => {
        const personnel = this.config.personnelOption.map(k => {
          const temp = {};
          temp.person = k.label;
          temp.api = k.value;
          (data || []).forEach(item => {
            temp[item.dateAt.split(' ')[0]] = item[k.value];
          });
          return temp;
        });
        this.help = Object.assign({}, this.help, {
          personnel,
        });
      });
      this.getProgressMachine({ id: this.reportId, type: 0 }).then(data => {
        const machine = this.config.machineOption.map(k => {
          const temp = {};
          temp.person = k.label;
          temp.api = k.value;
          (data || []).forEach(item => {
            temp[item.dateAt.split(' ')[0]] = item[k.value];
          });
          return temp;
        });
        this.help = Object.assign({}, this.help, {
          machine,
        });
      });
    },
    submit(type) { // next preview submit
      this.$refs.ui.submit(type);
    },
    createTableHead(startDate, offsetDate = 0) {
      const dates = ['日', '一', '二', '三', '四', '五', '六'];
      const result = [];
      for (let i = 0; i <= 6; i++) {
        let tempDate = new Date(startDate);
        tempDate.setDate(tempDate.getDate() + i - offsetDate);
        let m = tempDate.getMonth() + 1;
        let d = String(tempDate.getDate()).padStart(2, '0');
        let w = dates[tempDate.getDay()];
        result.push({
          name: m + '月' + d + '日 星期' + w,
          key: tempDate.getFullYear() + '-' + m + '-' + d,
        });
      }
      return result;
    },
    handleSubmit({ type, data }) {
      console.log('submit', data);
      data.id = this.reportId;
      // 整理personnel
      const personnelResult = [];
      this.tableHead.personnel.content.forEach(item => {
        if (item.key !== 'person') {
          const temp = {};
          temp.dateAt = item.key + ' 00:00:00';
          data.personnel.forEach(v => {
            temp.reportId = this.reportId;
            temp.weeklyType = 1;
            temp[v.api] = v[item.key] || null;
          });
          personnelResult.push(temp);
        }
      });
      data.personnel = personnelResult;
      // 整理machine
      const machineResult = [];
      this.tableHead.machine.content.forEach(item => {
        if (item.key !== 'person') {
          const temp = {};
          temp.dateAt = item.key + ' 00:00:00';
          data.machine.forEach(v => {
            temp.reportId = this.reportId;
            temp.weeklyType = 1;
            temp[v.api] = v[item.key] || null;
          });
          machineResult.push(temp);
        }
      });
      data.personnel = personnelResult;
      data.machine = machineResult;
      this.addNextplan(data);
      if (type === 'next') {
        this.$emit('next');
      } else if (type === 'preview') {
        this.$emit('preview');
      }
    },
    handleBack() {
      this.$emit('prev');
    },
  },
};
</script>
