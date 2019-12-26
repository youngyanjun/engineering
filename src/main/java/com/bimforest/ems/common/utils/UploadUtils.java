package com.bimforest.ems.common.utils;

import com.bimforest.ems.common.constant.CommonConstant;
import com.bimforest.ems.modules.sys.vo.SysFileVO;
import com.bimforest.utils.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author max.zhang
 * @title: UploadUtils
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/39:47
 * @Copyright (C) 量树科技
 */
public class UploadUtils {

    public static SysFileVO upload(MultipartFile multipartFile, String uploadPath) throws Exception {
        // 原始名称
        String originalFilename = multipartFile.getOriginalFilename();

        SysFileVO sysFileVO = null;
        // 上传图片
        if (multipartFile != null && originalFilename != null && originalFilename.length() > 0) {
            sysFileVO = new SysFileVO();
            // 图片按日期目录存储在服务器上
            String dir = DateUtils.format(new Date(), "yyyy-MM-dd") + "/";
            // 存储图片的物理路径
            String path = uploadPath + dir;
            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("pc/common/upload file.mkdir: success");
                } else {
                    System.out.println("pc/common/upload file.mkdir: error");
                }
            }
            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新图片
            file = new File(path + newFileName);
            // 将内存中的数据写入磁盘
            multipartFile.transferTo(file);
            sysFileVO.setFileName(CommonConstant.FILE_URL + dir + newFileName);
            sysFileVO.setRealName(originalFilename);

        }
        return sysFileVO;
    }
}
