package com.micro.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;

import static com.aliyun.teautil.Common.assertAsString;


@Slf4j
public class SmsCodeUtil {

    static Client createClient(String accessKeyId, String accessKeySecret, String endPoint) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint(endPoint);
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static SendSmsResponse sendSms(String accessKeyId, String accessKeySecret, String endPoint, String code){

        SendSmsResponse sendSmsResponse = null;
        try {
            Client client = createClient(accessKeyId, accessKeySecret, endPoint);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName("热潮博客")
                    .setTemplateCode("SMS_462650643")
                    .setPhoneNumbers("15383461957")
                    .setTemplateParam("{\"code\":\""+ code +"\"}");
            RuntimeOptions runtime = new RuntimeOptions();
            // 复制代码运行请自行打印 API 的返回值
            sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);

        } catch (TeaException error) {
            // 如有需要，请打印 error
            System.out.println(error.message);
            assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            System.out.println(_error.getMessage());
            assertAsString(error.message);
        }
        return sendSmsResponse;
    }


}
