package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.sys.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.DictionaryVO;
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
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    List<DictionaryVO> selectDictionaryList(@Param("name") String name);
}
