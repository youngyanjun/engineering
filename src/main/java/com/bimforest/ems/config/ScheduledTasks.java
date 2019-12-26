package com.bimforest.ems.config;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bimforest.ems.common.utils.HttpUtils;
import com.bimforest.ems.modules.construction.entity.WengineeringBaseInfo;
import com.bimforest.ems.modules.construction.service.WengineeringBaseInfoService;
import com.bimforest.ems.modules.sys.entity.SysWeatherForecast;
import com.bimforest.ems.modules.sys.service.SysWeatherForecastService;
import com.bimforest.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author max.zhang
 * @Description: 定时任务
 * @date 2019/12/11 14:33
 */
@Slf4j
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    SysWeatherForecastService sysWeatherForecastService;
    @Autowired
    private WengineeringBaseInfoService wengineeringBaseInfoService;

    @Value("${web.weather-forecast}")
    private String weatherForecastKey;

    /**
     * 　* @description: 每天凌晨1点执行一次,同步天气
     * 　* @author max.zhang
     * 　* @date 2019/5/7 17:49
     */
    @Scheduled(cron = "0 21 10 * * ?")
    public void syncDepartmentAndUser() {
        // 查找所有的工程，轮循工程，从墨迹天气获取天气预报信息
        List<WengineeringBaseInfo> wengineeringBaseInfos = wengineeringBaseInfoService.list();
        if (CollectionUtil.isNotEmpty(wengineeringBaseInfos)) {
            for (WengineeringBaseInfo wengineeringBaseInfo : wengineeringBaseInfos) {
                if (StrUtils.isNotBlank(wengineeringBaseInfo.getLatitude()) && StrUtils.isNotBlank(wengineeringBaseInfo.getLongitude())) {
                    //String str = getWeatherForecase(wengineeringBaseInfo.getLatitude(), wengineeringBaseInfo.getLongitude());
                    String str="{\"code\":0,\"data\":{\"city\":{\"cityId\":50,\"counname\":\"中国\",\"ianatimezone\":\"Asia\\/Shanghai\",\"name\":\"闵行区\",\"pname\":\"上海市\",\"secondaryname\":\"上海市\",\"timezone\":\"8\"},\"forecast\":[{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"2\",\"conditionNight\":\"阴\",\"humidity\":\"58\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2019-12-05 13:00:00\",\"moonset\":\"2019-12-05 00:45:00\",\"predictDate\":\"2019-12-05\",\"sunrise\":\"2019-12-05 06:38:00\",\"sunset\":\"2019-12-05 16:51:00\",\"tempDay\":\"13\",\"tempNight\":\"5\",\"updatetime\":\"2019-12-05 23:06:00\",\"windDegreesDay\":\"0\",\"windDegreesNight\":\"45\",\"windDirDay\":\"北风\",\"windDirNight\":\"东北风\",\"windLevelDay\":\"3-4\",\"windLevelNight\":\"4-5\",\"windSpeedDay\":\"5.6\",\"windSpeedNight\":\"8.05\"},{\"conditionDay\":\"阴\",\"conditionIdDay\":\"2\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"humidity\":\"61\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2019-12-06 13:29:00\",\"moonset\":\"2019-12-06 00:45:00\",\"predictDate\":\"2019-12-06\",\"sunrise\":\"2019-12-06 06:39:00\",\"sunset\":\"2019-12-06 16:51:00\",\"tempDay\":\"12\",\"tempNight\":\"2\",\"updatetime\":\"2019-12-06 13:07:00\",\"windDegreesDay\":\"45\",\"windDegreesNight\":\"315\",\"windDirDay\":\"东北风\",\"windDirNight\":\"西北风\",\"windLevelDay\":\"4-5\",\"windLevelNight\":\"3-4\",\"windSpeedDay\":\"8.05\",\"windSpeedNight\":\"5.6\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"humidity\":\"60\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2019-12-07 13:59:00\",\"moonset\":\"2019-12-07 01:38:00\",\"predictDate\":\"2019-12-07\",\"sunrise\":\"2019-12-07 06:39:00\",\"sunset\":\"2019-12-07 16:51:00\",\"tempDay\":\"11\",\"tempNight\":\"1\",\"updatetime\":\"2019-12-06 13:07:00\",\"windDegreesDay\":\"0\",\"windDegreesNight\":\"315\",\"windDirDay\":\"北风\",\"windDirNight\":\"西北风\",\"windLevelDay\":\"3-4\",\"windLevelNight\":\"3-4\",\"windSpeedDay\":\"5.6\",\"windSpeedNight\":\"5.6\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"humidity\":\"59\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2019-12-08 14:29:00\",\"moonset\":\"2019-12-08 02:32:00\",\"predictDate\":\"2019-12-08\",\"sunrise\":\"2019-12-08 06:40:00\",\"sunset\":\"2019-12-08 16:51:00\",\"tempDay\":\"13\",\"tempNight\":\"3\",\"updatetime\":\"2019-12-06 13:07:00\",\"windDegreesDay\":\"0\",\"windDegreesNight\":\"45\",\"windDirDay\":\"北风\",\"windDirNight\":\"东北风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"4.4\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"humidity\":\"64\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2019-12-09 15:01:00\",\"moonset\":\"2019-12-09 03:27:00\",\"predictDate\":\"2019-12-09\",\"sunrise\":\"2019-12-09 06:41:00\",\"sunset\":\"2019-12-09 16:52:00\",\"tempDay\":\"15\",\"tempNight\":\"6\",\"updatetime\":\"2019-12-06 13:07:00\",\"windDegreesDay\":\"180\",\"windDegreesNight\":\"225\",\"windDirDay\":\"南风\",\"windDirNight\":\"西南风\",\"windLevelDay\":\"3-4\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"5.6\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"humidity\":\"60\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2019-12-10 15:36:00\",\"moonset\":\"2019-12-10 04:24:00\",\"predictDate\":\"2019-12-10\",\"sunrise\":\"2019-12-10 06:42:00\",\"sunset\":\"2019-12-10 16:52:00\",\"tempDay\":\"16\",\"tempNight\":\"6\",\"updatetime\":\"2019-12-06 13:07:00\",\"windDegreesDay\":\"270\",\"windDegreesNight\":\"90\",\"windDirDay\":\"西风\",\"windDirNight\":\"东风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"4.4\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"阴\",\"conditionIdDay\":\"2\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"humidity\":\"60\",\"moonphase\":\"WaxingGibbous\",\"moonrise\":\"2019-12-11 16:16:00\",\"moonset\":\"2019-12-11 05:23:00\",\"predictDate\":\"2019-12-11\",\"sunrise\":\"2019-12-11 06:42:00\",\"sunset\":\"2019-12-11 16:52:00\",\"tempDay\":\"13\",\"tempNight\":\"4\",\"updatetime\":\"2019-12-06 13:07:00\",\"windDegreesDay\":\"45\",\"windDegreesNight\":\"45\",\"windDirDay\":\"东北风\",\"windDirNight\":\"东北风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3-4\",\"windSpeedDay\":\"4.4\",\"windSpeedNight\":\"5.6\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"humidity\":\"63\",\"moonphase\":\"Full\",\"moonrise\":\"2019-12-12 17:02:00\",\"moonset\":\"2019-12-12 06:24:00\",\"predictDate\":\"2019-12-12\",\"sunrise\":\"2019-12-12 06:43:00\",\"sunset\":\"2019-12-12 16:52:00\",\"tempDay\":\"13\",\"tempNight\":\"5\",\"updatetime\":\"2019-12-06 13:07:00\",\"windDegreesDay\":\"45\",\"windDegreesNight\":\"135\",\"windDirDay\":\"东北风\",\"windDirNight\":\"东南风\",\"windLevelDay\":\"3-4\",\"windLevelNight\":\"3-4\",\"windSpeedDay\":\"5.6\",\"windSpeedNight\":\"5.6\"},{\"conditionDay\":\"阴\",\"conditionIdDay\":\"2\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"humidity\":\"65\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2019-12-13 17:54:00\",\"moonset\":\"2019-12-13 07:25:00\",\"predictDate\":\"2019-12-13\",\"sunrise\":\"2019-12-13 06:44:00\",\"sunset\":\"2019-12-13 16:52:00\",\"tempDay\":\"15\",\"tempNight\":\"9\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"90\",\"windDegreesNight\":\"135\",\"windDirDay\":\"东风\",\"windDirNight\":\"东南风\",\"windLevelDay\":\"3-4\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"5.6\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"阴\",\"conditionIdDay\":\"2\",\"conditionIdNight\":\"2\",\"conditionNight\":\"阴\",\"humidity\":\"70\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2019-12-14 18:53:00\",\"moonset\":\"2019-12-14 08:24:00\",\"predictDate\":\"2019-12-14\",\"sunrise\":\"2019-12-14 06:44:00\",\"sunset\":\"2019-12-14 16:53:00\",\"tempDay\":\"16\",\"tempNight\":\"10\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"135\",\"windDegreesNight\":\"135\",\"windDirDay\":\"东南风\",\"windDirNight\":\"东南风\",\"windLevelDay\":\"3-4\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"5.6\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"humidity\":\"79\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2019-12-15 19:56:00\",\"moonset\":\"2019-12-15 09:19:00\",\"predictDate\":\"2019-12-15\",\"sunrise\":\"2019-12-15 06:45:00\",\"sunset\":\"2019-12-15 16:53:00\",\"tempDay\":\"18\",\"tempNight\":\"13\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"180\",\"windDegreesNight\":\"135\",\"windDirDay\":\"南风\",\"windDirNight\":\"东南风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"4.4\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"雨\",\"conditionIdDay\":\"8\",\"conditionIdNight\":\"8\",\"conditionNight\":\"雨\",\"humidity\":\"80\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2019-12-16 21:01:00\",\"moonset\":\"2019-12-16 10:09:00\",\"predictDate\":\"2019-12-16\",\"sunrise\":\"2019-12-16 06:46:00\",\"sunset\":\"2019-12-16 16:53:00\",\"tempDay\":\"16\",\"tempNight\":\"14\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"90\",\"windDegreesNight\":\"135\",\"windDirDay\":\"东风\",\"windDirNight\":\"东南风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"4.4\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"雨\",\"conditionIdDay\":\"8\",\"conditionIdNight\":\"8\",\"conditionNight\":\"雨\",\"humidity\":\"80\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2019-12-17 22:07:00\",\"moonset\":\"2019-12-17 10:54:00\",\"predictDate\":\"2019-12-17\",\"sunrise\":\"2019-12-17 06:46:00\",\"sunset\":\"2019-12-17 16:54:00\",\"tempDay\":\"16\",\"tempNight\":\"14\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"90\",\"windDegreesNight\":\"180\",\"windDirDay\":\"东风\",\"windDirNight\":\"南风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"4.4\",\"windSpeedNight\":\"4.4\"},{\"conditionDay\":\"雨\",\"conditionIdDay\":\"8\",\"conditionIdNight\":\"8\",\"conditionNight\":\"雨\",\"humidity\":\"71\",\"moonphase\":\"WaningGibbous\",\"moonrise\":\"2019-12-18 23:13:00\",\"moonset\":\"2019-12-18 11:35:00\",\"predictDate\":\"2019-12-18\",\"sunrise\":\"2019-12-18 06:47:00\",\"sunset\":\"2019-12-18 16:54:00\",\"tempDay\":\"15\",\"tempNight\":\"6\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"315\",\"windDegreesNight\":\"315\",\"windDirDay\":\"西北风\",\"windDirNight\":\"西北风\",\"windLevelDay\":\"4-5\",\"windLevelNight\":\"4-5\",\"windSpeedDay\":\"8.05\",\"windSpeedNight\":\"8.05\"},{\"conditionDay\":\"多云\",\"conditionIdDay\":\"1\",\"conditionIdNight\":\"31\",\"conditionNight\":\"多云\",\"humidity\":\"67\",\"moonphase\":\"Last\",\"moonrise\":\"2019-12-19 00:17:00\",\"moonset\":\"2019-12-19 12:12:00\",\"predictDate\":\"2019-12-19\",\"sunrise\":\"2019-12-19 06:47:00\",\"sunset\":\"2019-12-19 16:54:00\",\"tempDay\":\"7\",\"tempNight\":\"3\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"315\",\"windDegreesNight\":\"0\",\"windDirDay\":\"西北风\",\"windDirNight\":\"北风\",\"windLevelDay\":\"4-5\",\"windLevelNight\":\"3-4\",\"windSpeedDay\":\"8.05\",\"windSpeedNight\":\"5.6\"},{\"conditionDay\":\"晴\",\"conditionIdDay\":\"0\",\"conditionIdNight\":\"30\",\"conditionNight\":\"晴\",\"humidity\":\"54\",\"moonphase\":\"WaningCrescent\",\"moonrise\":\"2019-12-20 00:17:00\",\"moonset\":\"2019-12-20 12:48:00\",\"predictDate\":\"2019-12-20\",\"sunrise\":\"2019-12-20 06:48:00\",\"sunset\":\"2019-12-20 16:55:00\",\"tempDay\":\"7\",\"tempNight\":\"3\",\"updatetime\":\"2019-12-06 12:15:00\",\"windDegreesDay\":\"45\",\"windDegreesNight\":\"45\",\"windDirDay\":\"东北风\",\"windDirNight\":\"东北风\",\"windLevelDay\":\"3\",\"windLevelNight\":\"3\",\"windSpeedDay\":\"4.4\",\"windSpeedNight\":\"4.4\"}]},\"msg\":\"success\",\"rc\":{\"c\":0,\"p\":\"success\"}}";
                    log.info("syncDepartmentAndUser:{}", str);
                    // 解析从墨迹天气获取的数据保存到数据库sys_weather_forecast中
                    JSONObject result = JSONObject.parseObject(str);
                    if (StrUtils.isNotBlank(str)) {
                        int code = result.getIntValue("code");
                        if (code == 0) {
                            JSONObject data = result.getJSONObject("data");
                            JSONObject city = data.getJSONObject("city");
                            int cityId = city.getIntValue("cityId");
                            JSONArray forcast = data.getJSONArray("forecast");
                            if (forcast != null && forcast.size() != 0) {
                                List<SysWeatherForecast> sysWeatherForecasts = new LinkedList<>();
                                SysWeatherForecast sysWeatherForecast = null;
                                for (int i = 0; i < forcast.size(); i++) {
                                    JSONObject jo = forcast.getJSONObject(i);
                                    sysWeatherForecast = new SysWeatherForecast();
                                    sysWeatherForecast.setPredictDate(jo.getDate("predictDate"));
                                    sysWeatherForecast.setTempDay(jo.getString("tempNight") + "-" + jo.getString("tempDay")+"℃");
                                    sysWeatherForecast.setConditionDay(jo.getString("conditionDay"));
                                    sysWeatherForecast.setUpdatetime(jo.getDate("updatetime"));
                                    sysWeatherForecast.setCityId(cityId);
                                    sysWeatherForecast.setWindDirDay(jo.getString("windDirDay")+jo.getString("windLevelDay")+"级");
                                    sysWeatherForecast.setWindSpeedDay(jo.getString("windSpeedDay"));
                                    sysWeatherForecast.setLatitude(wengineeringBaseInfo.getLatitude());
                                    sysWeatherForecast.setLongitude(wengineeringBaseInfo.getLongitude());
                                    sysWeatherForecasts.add(sysWeatherForecast);
                                }
                                sysWeatherForecastService.insertBatch(sysWeatherForecasts);
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * @Description: 从墨迹天气获取天气预报信息
     * @param lat 纬度
     * @param lon 经度
     * @return java.lang.String
     * @author max.zhang
     * @date 2019/12/10 14:59
     */
    public String getWeatherForecase(String lat, String lon) {
        String host = "http://aliv8.data.moji.com";
        String path = "/whapi/json/aliweather/forecast15days";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + weatherForecastKey);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("lat", lat);
        bodys.put("lon", lon);
        bodys.put("token", "7538f7246218bdbf795b329ab09cc524");
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
