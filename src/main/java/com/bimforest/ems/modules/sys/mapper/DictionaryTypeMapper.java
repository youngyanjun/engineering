package com.bimforest.ems.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.modules.sys.entity.DictionaryType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.DictionaryTypeVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-11
 */
public interface DictionaryTypeMapper extends BaseMapper<DictionaryType> {
    //获取排序最大值
    int selectSortIdMax();

    int updateSortID(@Param("sortId") Integer sortId, @Param("id") String id);


    List<DictionaryTypeVO> selectDictionaryTypeList(@Param("id") String id);
}
