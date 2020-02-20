package xyz.xhui.award.config.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private Object data;
    private Map<String, Object> meta = new HashMap<String, Object>();

    public Result(Object data, String msg, Integer status) {
        this.data = data;
        this.meta.put("msg", msg);
        this.meta.put("status", status);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }
}
