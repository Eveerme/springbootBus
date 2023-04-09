package com.chen.sbbus.utils;

import lombok.Data;

@Data
public class R {
    private Boolean status;//状态
    private Object data;//数据
    public R(){
    }
    public R(Boolean status){
        this.status=status;
    }
    public R(Object data){
        this.data = data;
    }
    public R(Boolean status , Object data){
        this.status=status;
        this.data = data;
    }
}
