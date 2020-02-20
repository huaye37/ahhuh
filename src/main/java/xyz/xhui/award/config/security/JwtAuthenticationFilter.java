package xyz.xhui.award.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.xhui.award.config.utils.JwtUtils;
import xyz.xhui.award.config.utils.MyHttpUtils;
import xyz.xhui.award.config.utils.PasswordUtils;
import xyz.xhui.award.model.SysUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    protected JwtAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super.setFilterProcessesUrl(defaultFilterProcessesUrl);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SysUser sysUser = ((JwtUser) authResult.getPrincipal()).getSysUser();
        String token = JwtUtils.createToken(authResult.getName(), sysUser.getRole());
        PasswordUtils.hiddenPassword(sysUser);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", sysUser);
        map.put("token", token);
        MyHttpUtils.responseSuccess(response, map, "登陆成功");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        String msg = null;
        if (!request.getMethod().equals("POST")) {
            msg = "请求类型错误";
        } else {
            msg = request.getParameter("username") != null && request.getParameter("password") != null ? "用户名或密码错误" : "参数错误";
        }
        MyHttpUtils.responseData(response, null, msg, HttpServletResponse.SC_UNAUTHORIZED);
    }
}
