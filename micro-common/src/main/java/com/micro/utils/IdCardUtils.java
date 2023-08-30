package com.micro.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class IdCardUtils {
    public static JSONObject IdCardApprove(String cardNo, String realName, String appcode){
        log.info("实名认证，名字：{}，身份证号：{}", realName, cardNo);
        String host = "https://zidv2.market.alicloudapi.com";
        String path = "/idcheck/Post";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cardNo", cardNo);
        bodys.put("realName", realName);


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            获取response的body
            return JSON.parseObject(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
