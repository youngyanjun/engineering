/**
 * 并集
 */
export const unionSet = (arr1, arr2) => {
  return [...new Set([...arr1, ...arr2])];
};

/**
 * 差集
 */
export const differenceSet = (arr1, arr2) => {
  return [...new Set([...arr1].filter(item => !arr2.includes(item)))];
};

export default {
  unionSet,
  differenceSet,
};
