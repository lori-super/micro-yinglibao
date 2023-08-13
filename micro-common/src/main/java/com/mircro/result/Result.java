package com.mircro.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    // 1 成功 0 失败
    private Integer code;

    // 错误后，给前端返回的信息
    private String msg = "";

    //接收的对象
    private T data;


    public static <T> Result<T> sucess(){
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> fail(String msg){
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> sucess(T object){
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = object;
        return result;
    }
}
