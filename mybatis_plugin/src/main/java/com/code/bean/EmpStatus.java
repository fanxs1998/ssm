package com.code.bean;

/**
 * @author:fanxs
 * @Date: 2021/12/6 0:33
 * description:
 */
public enum  EmpStatus {
    LOGIN(200, "用户登录"),LOGOUT(300, "用户登出"),REMOVE(400, "用户移除");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;
    EmpStatus(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    /**
     * 按照状态码返回枚举对象
     * @param code
     * @return
     */
    public static EmpStatus getEmpStatusByCode(Integer code) {
        switch (code) {
            case 100:
                return LOGIN;
            case 300:
                return REMOVE;
            default:
                return LOGOUT;
        }
    }
}
