package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.sys.entity.Dictionary;
import com.bimforest.ems.modules.sys.mapper.DictionaryMapper;
import com.bimforest.ems.modules.sys.vo.DictionaryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-11
 */
@Api(description = "自定义字段")
@RestController
@RequestMapping("/api/sys/dictionary")
public class DictionaryController {

    @Autowired
    DictionaryMapper dictionaryMapper;

    @ApiOperation(value = "查询自定义字段")
    @GetMapping("/selectList")
    public CommonResponse selectDictionaryList( String name) {
        List<DictionaryVO> dictionaryVOS = dictionaryMapper.selectDictionaryList(name);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,dictionaryVOS);
    }
    @ApiOperation(value = "修改类型")
    @GetMapping("/updateDictionaryChild")
    public CommonResponse updateDictionaryChild(@RequestParam String id,@RequestParam  String dictionaryChild ,@RequestParam String name) {
        Dictionary dictionary = new Dictionary();
        dictionary.setDictionaryName(name);
        dictionary.setId(id);
        dictionary.setDictionaryChild(dictionaryChild);
        dictionaryMapper.updateById(dictionary);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

}


