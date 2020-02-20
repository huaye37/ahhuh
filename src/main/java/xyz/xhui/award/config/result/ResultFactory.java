package xyz.xhui.award.config.result;

public class ResultFactory {

    public static Result buildResult(Object data, String msg, Integer status) {
        return new Result(data, msg, status);
    }

    public static Result buildSuccessResult(Object data, String msg) {
        return buildResult(data, msg, StatusCode.SUCCESS.getCode());
    }

    public static Result buildFailResult(Object data, String msg) {
        return buildResult(null, msg, StatusCode.FAIL.getCode());
    }
}
