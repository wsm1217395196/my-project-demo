package com.study.result;


import lombok.Data;

/**
 * 结果视图类
 */
@Data
public class ResultView {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    /**
     * 成功
     *
     * @return 结果视图
     */
    public static ResultView success() {
        return new ResultView();
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return 结果视图
     */
    public static ResultView success(Object data) {
        return new ResultView(data);
    }

    /**
     * 错误
     *
     * @param resultEnum 结果枚举
     * @return 结果视图
     */
    public static ResultView error(ResultEnum resultEnum) {
        return new ResultView(resultEnum.getCode(), resultEnum.getMsg());
    }

    /**
     * 自定义成功消息
     *
     * @param msg 成功消息
     * @return 结果视图
     */
    public static ResultView success(String msg, Object data) {
        ResultView resultView = new ResultView(ResultEnum.CODE_1.getCode(), msg);
        resultView.setData(data);
        return resultView;
    }

    /**
     * 自定义错误消息
     *
     * @param msg 错误消息
     * @return 结果视图
     */
    public static ResultView error(String msg) {
        return new ResultView(ResultEnum.CODE_2.getCode(), msg);
    }

    private ResultView() {
        this.code = ResultEnum.CODE_1.getCode();
        this.msg = ResultEnum.CODE_1.getMsg();
    }

    private ResultView(Object data) {
        this.data = data;
        this.code = ResultEnum.CODE_1.getCode();
        this.msg = ResultEnum.CODE_1.getMsg();
    }

    private ResultView(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
