package xyz.xhui.award.config.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.xhui.award.config.result.Result;
import xyz.xhui.award.config.result.ResultFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHttpUtils {
    public static void responseSuccess(HttpServletResponse response, Object data, String msg) throws IOException {
        responseData(response, data, msg, 200);
    }

    public static void responseData(HttpServletResponse response, Object data, String msg, Integer status) throws IOException {
        Result result = ResultFactory.buildResult(data, msg, status);
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(status);
        response.setContentType("application/json;charset=utf-8;");
        response.getWriter().write(mapper.writeValueAsString(result));
    }
}
