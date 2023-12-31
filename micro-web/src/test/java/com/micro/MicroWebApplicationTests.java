package com.micro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.properties.AliSmsProperties;
import com.micro.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = MicroWebApplication.class)
class MicroWebApplicationTests {

    @Resource
    private AliSmsProperties aliSmsProperties;
    @Test
    void contextLoads() {
        String host = "https://zidv2.market.alicloudapi.com";
        String path = "/idcheck/Post";
        String method = "POST";
        String appcode = "09783177d13c42ef92793f1e9523e598";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cardNo", "杨丰豪");
        bodys.put("realName", "140502199706031039");


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            获取response的body
            String info = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(info);
            System.out.println(info);
            System.out.println(jsonObject.getString("reason"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
