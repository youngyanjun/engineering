package com.bimforest.ems.modules.construction.service;

import com.bimforest.ems.modules.construction.vo.ImageProgressVO;
import com.bimforest.ems.modules.construction.vo.InsertImageProgressVO;
import com.bimforest.ems.modules.construction.entity.MengineeringImageProgress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
public interface MengineeringImageProgressService extends IService<MengineeringImageProgress> {

    int insetImageProgress(InsertImageProgressVO imageProgress);

    int updateImageProgress(ImageProgressVO progress);

    List<Integer> selectMonthByEngineering(String organizationId);

    InsertImageProgressVO selectOneImageProgress(String id);

}
