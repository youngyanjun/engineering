package com.bimforest.ems.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-12-16 10:35
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class SMSTest {
    /***
     * 采用特定规则截取字符串
     *
     */


    public void TestStr() {
        String sms1 = "下周<SMS>11111</SMS><SMS>22222</SMS> 项目需要你的协助,,,<SMS>33333</SMS>";
        String sms2 = "<SMS>11111</SMS><SMS>22222</SMS> 项目需要你的协助,,,<SMS>33333</SMS>";
        String sms3 = "下周<SMS>11111</SMS><SMS>22222</SMS> <SMS>33333</SMS>";
      /*  StrTruncationUtil.subStr(sms1);
        StrTruncationUtil.subStr(sms2);
        StrTruncationUtil.subStr(sms3);*/

        //如果字符串为空则返回null
        if (sms1 == null) {
            log.info("null");
        }
        List<String> ret = new ArrayList<>();
        int ch = 0, start, end;
        while (ch < sms1.length()) {
            // 索引出现负数，说明在源字符串指定位置之后已经没有 <SMS> 或者 </SMS>
            start = sms1.indexOf("<SMS>", ch);
            end = sms1.indexOf("</SMS>", ch);
            // substring 内部索引禁止出现负数
            if (start == -1 || end == -1) {
                break;
            }
            String tmp = sms1.substring(start + 1, end);
            //保存上一次截取时的索引
            ch = end + 1;
            ret.add(tmp);
        }

        log.info("list:" + ret.toString());
    }

}


