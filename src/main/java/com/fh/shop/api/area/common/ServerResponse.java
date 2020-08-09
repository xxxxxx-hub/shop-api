package com.fh.shop.api.area.common;

public class ServerResponse {

    private   int code;

    private   String msg;

    private   Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

   /*无参构造*/
    private void  ServerResponse(){

    }
    /*有参*/
    public ServerResponse (int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public     static ServerResponse success(){
        return  new ServerResponse(200,"ok",null);
    }


    public    static ServerResponse success(Object data){
        return new ServerResponse(200,"ok",data);
    }

    public    static ServerResponse error(){
        return new ServerResponse(201,"error",null);
    }

    /*使用内部的一个可以放常量的方法*/
    public    static ServerResponse error(int code, String msg){
        return new ServerResponse(code,msg,null);
    }


}
