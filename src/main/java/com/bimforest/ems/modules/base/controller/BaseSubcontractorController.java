package com.bimforest.ems.modules.base.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.MessageEnums;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.utils.CheckUtil;
import com.bimforest.ems.modules.base.entity.BaseSubcontractor;
import com.bimforest.ems.modules.sys.mapper.BaseSubcontractorMapper;
import com.bimforest.ems.modules.sys.service.SysEngineeringSubcontractService;
import com.bimforest.ems.modules.sys.vo.SubcontractorListVO;
import com.bimforest.ems.modules.sys.vo.SubcontractorVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 分包商表 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
@Api(description="分包商管理")
@RestController
@RequestMapping("/api/base/subcontractor/engineering-subcontract")
public class BaseSubcontractorController {
    @Autowired
    SysEngineeringSubcontractService sysEngineeringSubcontractService;
    @Autowired
    BaseSubcontractorMapper sysEngineeringSubcontractMapper;


    @ApiOperation(value = "分包商管理---新增分包商")
    @PostMapping("/insertUser")
    @OperationLog(module = "分包商管理", action = "新增分包商")
    @Transactional
    public CommonResponse insertUser(@RequestBody SubcontractorVO subcontractorVO) {
        //检查电话号码是否符合规则
        if (!CheckUtil.checkPhone(subcontractorVO.getPhone())) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2004.getCode(), MessageEnums.FIAL_SYS_2004.getMessage());
        }
        //判断企业名称和纳税识别号是否已经存在
        QueryWrapper queryWrapperName = new QueryWrapper();
        QueryWrapper queryWrapperCode = new QueryWrapper();
        queryWrapperName.eq("unit_name", subcontractorVO.getUnitName());
        queryWrapperCode.eq("tax_code", subcontractorVO.getTaxCode());
        if (sysEngineeringSubcontractService.count(queryWrapperName) != 0 | sysEngineeringSubcontractService.count(queryWrapperCode) != 0) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2010.getCode(), MessageEnums.FIAL_SYS_2010.getMessage());
        }

        BaseSubcontractor engineeringSubcontract = new BaseSubcontractor();
        engineeringSubcontract.setUnitName(subcontractorVO.getUnitName());
        engineeringSubcontract.setTaxCode(subcontractorVO.getTaxCode());
        engineeringSubcontract.setAddress(subcontractorVO.getAddress());
        engineeringSubcontract.setContact(subcontractorVO.getContact());
        engineeringSubcontract.setPhone(subcontractorVO.getPhone());
        engineeringSubcontract.setJuridicalPerson(subcontractorVO.getJuridicalPerson());
        engineeringSubcontract.setRemark(subcontractorVO.getRemark());

        sysEngineeringSubcontractMapper.insert(engineeringSubcontract);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    /**
     * 封包商列表查询
     *
     * @return
     */
    @ApiOperation(value = "分包商管理---分包商列表查询")
    @GetMapping("/selectSubcontractorList")
    public CommonResponse selectSubcontractorList(String subcontractorName,
                                                  @RequestParam(required = false, defaultValue = "10") @ApiParam("每页显示条数") int pageSize,
                                                  @RequestParam(required = false, defaultValue = "1") @ApiParam("当前页码") int pageNum) {
        Page page = new Page(pageNum, pageSize);
        IPage<SubcontractorListVO> userListVOIPage = sysEngineeringSubcontractService.selectList(page, subcontractorName);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, userListVOIPage);
    }

    /**
     * 根据ID修改分包商信息
     *
     * @return
     */
    @ApiOperation(value = "分包商管理---修改分包商信息")
    @PostMapping("/updateSubcontractorById")
    @OperationLog(module = "分包商管理", action = "修改分包商信息")
    @Transactional
    public CommonResponse updateSubcontractorById(@RequestBody SubcontractorVO Subcontractor) {
        BaseSubcontractor baseSubcontractor = new BaseSubcontractor();
        baseSubcontractor.setId(Subcontractor.getId());//设置id
        baseSubcontractor.setJuridicalPerson(Subcontractor.getJuridicalPerson());//设置企业法人
        baseSubcontractor.setUnitName(Subcontractor.getUnitName());//设置企业名称
        baseSubcontractor.setTaxCode(Subcontractor.getTaxCode());//设置纳税人识别号
        baseSubcontractor.setAddress(Subcontractor.getAddress());//设置地址
        baseSubcontractor.setPhone(Subcontractor.getPhone());// 设置联系电话
        baseSubcontractor.setContact(Subcontractor.getContact()); // 设置 联系人
        baseSubcontractor.setRemark(Subcontractor.getRemark()); // 设置备注

        if (sysEngineeringSubcontractService.updateById(baseSubcontractor)) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED);
    }

    /**
     * 根据ID查询封包商信息----分包商信息回显
     *
     * @return
     */
    @ApiOperation(value = "分包商管理---分包商信息回显")
    @GetMapping("/selectSubcontractorById")
    public CommonResponse selectSubcontractorById(String id) {
        QueryWrapper<BaseSubcontractor> queryWrapper = new QueryWrapper<>();//查询对象
        queryWrapper.eq(StringUtils.isNotEmpty(id), "id", id);
        BaseSubcontractor one = sysEngineeringSubcontractService.getOne(queryWrapper, false);//查询一条对象，多条不抛出异常
        return new CommonResponse(ResponseTypeEnums.SUCCESS, one);//返回一条对象
    }
    @OperationLog(module = "分包商管理",action = "删除分包商")
    @ApiOperation(value = "分包商管理---删除分包商信息")
    @GetMapping("/deleteSubcontractor")
    public CommonResponse deleteSubcontractor(String id) {
        sysEngineeringSubcontractService.deleteById(id);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);//返回一条对象
    }


}
