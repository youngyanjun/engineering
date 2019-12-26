<template>
  <UI
    :visible="visible"
    :config="config"
    @add="handleAdd"
    @close="handleClose"
    @back="handleBack"
  />
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UI from './component/ui';

export default {
  name: '',
  components: { UI },
  props: {
    engineeringId: String,
  },
  data() {
    return {
      visible: false,
    };
  },
  created() {
  },
  computed: {
    ...mapState('engineeringReportWeek', [
      'week',
      'reportId',
    ]),

    config() {
      return {
        weeks: this.week.sort((a, b) => b.weekly - a.weekly),
      };
    },
  },
  methods: {
    ...mapActions('engineeringReportWeek', [
      'getWeek',
      'createRecord',
    ]),
    open() {
      this.getWeek(this.engineeringId).then(() => {
        if (this.week.length <= 0) {
          window.$Message.warning('当前无可用周次');
        } else if (this.week.length === 1) {
          this.handleAdd(this.week[0]);
        } else {
          this.visible = true;
        }
      });
    },
    handleAdd(data) {
      const weekOption = data;
      this.createRecord({
        organizationId: this.engineeringId,
        weeklyNum: weekOption.weekly,
        constructionDateStart: weekOption.sdate,
        constructionDateFinis: weekOption.edate,
      }).then(reportId => {
        this.$router.push({ name: 'EngineeringReportWeekBase', params: { reportId } });
      });
    },
    handleClose() {
      this.visible = false;
    },
    handleBack() {
      this.visible = false;
    },
  },
};
</script>
