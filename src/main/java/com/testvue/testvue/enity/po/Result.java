package com.testvue.testvue.enity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;

    private T  data;

    public  final static Result success(){
        Result result=new Result<>();
        result.setCode(1);
        result.setMsg("");
        result.setData("null");
        return result;
    }
    public static <T> Result<T> success(T t)
    {
        Result<T> result=new Result<T>();
        result.setData(t);
        result.setCode(1);
        result.setMsg("");
        return result;

    }
    public static Result error(String msg,Integer code)
    {
        Result result=new Result();
        result.setMsg(msg);
        result.setCode(code);
        result.setData("null");
        return result;

    }





}
