package com.bimforest.ems.modules.department.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.department.vo.GetOrgEngUserVO;
import com.bimforest.ems.modules.department.vo.ReportListVO;
import com.bimforest.ems.modules.department.vo.SubmitDepartmentMonthReportVO;
import com.bimforest.ems.modules.department.entity.ReportList;
import com.bimforest.ems.modules.department.mapper.ReportListMapper;
import com.bimforest.ems.modules.department.service.ReportListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.management.VO.SelectRawDataListVO;
import com.bimforest.ems.modules.sys.entity.SysMessage;
import com.bimforest.ems.modules.sys.mapper.SysMessageMapper;
import com.bimforest.ems.pojo.DateWeekly;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-12
 */
@Slf4j
@Service
public class ReportReportListServiceImpl extends ServiceImpl<ReportListMapper, ReportList> implements ReportListService {
    @Autowired
    ReportListMapper reportListMapper;
    @Autowired
    SysMessageMapper messageMapper;

    @Override
    public IPage<ReportListVO> getReportList(Page page, String organizationId, Integer reportType, String reportName) {


        return reportListMapper.getReportList(page, organizationId, reportType, reportName);
    }

    @Override
    public List<DateWeekly> selectListWeeklyById(String organizationId) {

        return reportListMapper.selectListWeeklyById(organizationId);
    }

    @Override
    public String getId(Integer reportType, Integer weeklyMonth) {
        return reportListMapper.getId(reportType, weeklyMonth);
    }

    /**
     * 查询当年已经存在的月报的月份
     *
     * @param organizationId
     * @param year
     * @return
     */
    @Override
    public List<Integer> selectListMonthByOrganizationType(String organizationId, String year) {

        return reportListMapper.selectListMonthByOrganizationType(organizationId, year);
    }

    @Override
    public int submitReport(String reportId, Integer status) {
        return reportListMapper.updateStatusByReport(reportId, status);
    }

    /**
     * 发送"@"信息
     *
     * @return
     */
    @Override
    public int sm(SubmitDepartmentMonthReportVO reportVO) {
        //发送信息
        List<String> list = reportVO.getSmList();
        for (int i = 0; i < list.size(); i++) {

            String str = list.get(i);
            if (str == null) {
                continue;
            }
            int ch = 0, start, end;
            while (ch < str.length()) {
                // 索引出现负数，说明在源字符串指定位置之后已经没有 "<custom-userid>" 或者 "</custom-userid>"
                start = str.indexOf("<custom-userid>", ch);
                end = str.indexOf("</custom-userid>", ch);
                // substring 内部索引禁止出现负数
                if (start == -1 || end == -1) {
                    break;
                }
                String tmp = str.substring(start + 15, end);
                //保存上一次截取时的索引
                //保存信息,
                SysMessage message = new SysMessage();
                message.setReceiverId(tmp);
                message.setContent(str);
                message.setReadFlag(false);
                message.setBizId(reportVO.getReportId());
                messageMapper.insert(message);
                ch = end + 1;

            }
        }
        return 0;
    }

    /**
     * 插入部门报表列表
     *
     * @param reportList
     * @return
     */
    @Override
    public int insert(ReportList reportList) {

        return reportListMapper.insert(reportList);
    }

    @Override
    public List<GetOrgEngUserVO> getOrgEngUser(String organizationType, String engineeringId) {

        return reportListMapper.getOrgEngUser(organizationType, engineeringId);
    }

    @Override
    public List<String> getEngId(String organizationType) {

        return reportListMapper.getEngId(organizationType);
    }

    /**
     * 项管中心原始数据列表
     * @param rawDataList
     * @return
     */
    @Override
    public IPage<SelectRawDataListVO> getRawDataList(SelectRawDataListVO rawDataList) {
        Page page = new Page(rawDataList.getPageNum(), rawDataList.getPageSize());
        return  reportListMapper.getRawDataList(page,rawDataList);
    }
}
