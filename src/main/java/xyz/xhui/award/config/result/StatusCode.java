package xyz.xhui.award.config.result;

public enum StatusCode {
    SUCCESS(200),
    FAIL(400);

    private Integer code;

    StatusCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
