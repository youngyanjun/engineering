package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-11
 */
public interface DictionaryService extends IService<Dictionary> {

    Dictionary selectOne(String id);

}
