package com.study.result;

import lombok.Getter;

/**
 * 结果枚举
 */
@Getter
public enum ResultEnum {
    /**
     * 操作成功
     */
    CODE_1(1, "操作成功！"),
    /**
     * 操作失败
     */
    CODE_2(2, "操作失败！"),

    /**
     * token无效,禁止访问!
     */
    CODE_401(401, "token无效,禁止访问!"),
    /**
     * 授权失败,禁止访问！
     */
    CODE_403(403, "授权失败,禁止访问！"),

    /**
     * 请求超时，请稍后再试！
     */
    CODE_504(504, "请求超时，请稍后再试！"),

    /**
     * 服务器数据异常
     */
    CODE_666(666, "服务器数据异常！");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
