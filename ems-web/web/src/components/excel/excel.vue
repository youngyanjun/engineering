<template>
  <div>
    <table class="bh-excel" ref="table">
      <colgroup>
        <col
          :width="thead[thead.length - 1][i].style && thead[thead.length - 1][i].style.width
            ? thead[thead.length - 1][i].style.width : cellWidth"
          v-for="(item, i) in thead[thead.length - 1].length"
        />
      </colgroup>
      <thead class="bh-excel-hd">
        <tr class="bh-excel-tr" v-for="(row, i) in thead">
          <th
            :class="[
              'bh-excel-th',
              `is-row${typeof column.style !=='undefined' && typeof column.style.row !== 'undefined' ? column.style.row : 1}`,
              `is-column${typeof column.style !=='undefined' && typeof column.style.column !== 'undefined' ? column.style.column : 1}`,
              { 'is-align-left': typeof column.style !=='undefined' && column.style.align === 'left' },
              { 'is-align-center': typeof column.style !=='undefined' && column.style.align === 'center' },
              { 'is-align-right': typeof column.style !=='undefined' && column.style.align === 'right' },
            ]"
            v-for="(column, j) in row"
            :colspan="typeof column.style !=='undefined' && typeof column.style.row !== 'undefined' ? column.style.row : 1"
            :rowspan="typeof column.style !=='undefined' && typeof column.style.column !== 'undefined' ? column.style.column : 1"
          >
            <RichTextarea
              class="bh-excel-input"
              :readonly="!(column.style && column.style.readonly === false)"
              :msgDisabled="!(column.style && column.style.msgDisabled === false)"
              v-model="column.value"
            />
          </th>
        </tr>
      </thead>
      <tbody class="bh-excel-bd">
        <tr class="bh-excel-tr" v-for="(row, i) in tbody">
          <td
            :class="[
              'bh-excel-td',
              `is-row${typeof column.style !=='undefined' && typeof column.style.row !== 'undefined' ? column.style.row : 1}`,
              `is-column${typeof column.style !=='undefined' && typeof column.style.column !== 'undefined' ? column.style.column : 1}`,
              { 'is-active': i === currentCell.row && j === currentCell.col },
              { 'is-align-left': typeof column.style !=='undefined' && column.style.align === 'left' },
              { 'is-align-center': typeof column.style !=='undefined' && column.style.align === 'center' },
              { 'is-align-right': typeof column.style !=='undefined' && column.style.align === 'right' },
            ]"
            v-for="(column, j) in row"
            :colspan="typeof column.style !=='undefined' && typeof column.style.row !== 'undefined' ? column.style.row : 1"
            :rowspan="typeof column.style !=='undefined' && typeof column.style.column !== 'undefined' ? column.style.column : 1"
            @click="handleCellClick.call(this, i, j)"
          >
            <RichTextarea
              class="bh-excel-input"
              :readonly="column.style && column.style.readonly === true"
              :msgDisabled="!(column.style && column.style.msgDisabled === false)"
              :msgOptions="msgOptions"
              v-model="column.value"
            />
          </td>
        </tr>
      </tbody>
    </table>
    <textarea class="bh-excel-clipboard" ref="textarea" v-model="textarea">
    </textarea>
  </div>
</template>

<script>
import RichTextarea from './rich_textarea';

const excel2arr = (value) => {
  let arr = []; let i = 0; let tempArr = [];
  tempArr = encodeURIComponent(value).split('%09');
  console.log(tempArr);
  tempArr.forEach((item, index) => {
    let temp;
    if (/^%22(.*?)%22$/.test(item)) {
      item = item.replace(/^%22/g, '').replace(/%22$/g, '');
    } else if (/%0A/.test(item)) {
      temp = item.split('%0A');
      item = temp[0];
      if (index < tempArr.length - 1 || tempArr.length % 2 !== 0) {
        arr[i+1] = [temp[1]];
      }
    }
    if (arr[i]) {
      arr[i] = [...arr[i], decodeURIComponent(item)];
    } else {
      arr[i] = [decodeURIComponent(item)];
    }
    if (temp) {
      i++;
    }
  });
  return arr;
};

export default {
  name: '',
  components: { RichTextarea },
  props: {
    thead: {
      type: Array,
      default: () => [],
    },
    tbody: {
      type: Array,
      default: () => [],
    },
    msgOptions: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      textarea: '',
      $table: null,
      $textarea: null, // 临时粘贴框
      currentCell: {
        row: -1,
        col: -1,
      },
      tableWidth: 0,
    };
  },
  computed: {
    clipboard() {
      console.log(this.textarea);
      return excel2arr(this.textarea);
    },
    cellWidth() {
      let widthNum = 0;
      let rowCells = [];
      let rowWidthTotal = 0;
      if (this.thead[this.thead.length - 1]) {
        rowCells = this.thead[this.thead.length - 1];
      }
      rowWidthTotal = rowCells.reduce((sum, b) => {
        if (b.style && b.style.width) {
          widthNum += 1;
          return sum + b.style.width;
        } else {
          return sum;
        }
      }, 0);
      return (this.tableWidth - rowWidthTotal) / (rowCells.length - widthNum);
    },
  },
  watch: {
    textarea(val) {
      console.log(val);
    },
  },
  mounted() {
    this.$table = this.$refs.table;
    this.$textarea = this.$refs.textarea;
    this.tableWidth = this.$table.clientWidth;
    document.addEventListener('keydown', this.handleKeydown);
  },
  methods: {
    handleCellClick(i, j) {
      this.currentCell.row = i;
      this.currentCell.col = j;
      this.$emit('currentCellChange', this.currentCell);
    },
    handleKeydown(ev) {
      // console.log(ev);
      let target, targetRow, targetCol;
      if (ev.ctrlKey === true
        && ev.key === 'v'
        && this.currentCell.row >= 0
        && this.currentCell.col >= 0
      ) {
        target = ev.target;
        targetRow = this.currentCell.row;
        targetCol = this.currentCell.col;
        this.$textarea.focus();
        setTimeout(() => {
          console.log(this.clipboard);
          this.clipboard.forEach((row, i) => {
            row.forEach((column, j) => {
              if (this.tbody[i + targetRow] && this.tbody[i + targetRow][j + targetCol]) {
                const dataSource = this.tbody[i + targetRow][j + targetCol];
                // 排除只读
                if (dataSource.style && dataSource.style.readonly === true) {
                  return;
                }
                dataSource.value = column;
              }
            });
          });
          target.focus();
        });
      }
    },
    label(val) {
      return val.replace(/\n/g, '<br/>');
    },
  },
  destroyed() {
    document.removeEventListener('keydown', this.handleKeydown);
  },
};
</script>

<style lang="scss">
@import './excel.scss';
</style>
