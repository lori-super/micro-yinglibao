package com.mircro.phone;

import com.mircro.exception.PhoneFormatException;
import org.apache.dubbo.common.utils.StringUtils;

public class CommonUtils {

    public static String hidePhoneNumber(String phone){
        if (StringUtils.isBlank(phone)) {
            throw new PhoneFormatException("存在空手机号");
        }
        phone = phone.trim();
        if (phone.length() != 11){
            throw new PhoneFormatException("存在手机号长度不等于11");
        }

        return phone.substring(0, 3) + "******" + phone.substring(9, 11);
    }
}
