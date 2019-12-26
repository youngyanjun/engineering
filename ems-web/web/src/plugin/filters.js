const projectType = (value) => {
  switch (value) {
    case 'dept':
      return '公司部门';
    case 'engineering':
      return ' 项目部';
    case 'leader':
      return ' 领导层';
    default:
      return ' 未知类型';
  }
};

const dafault = (val) => {
  return val || '-';
};

export {
  dafault,
  projectType,
};
