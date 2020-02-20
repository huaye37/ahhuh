package xyz.xhui.award.config.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import xyz.xhui.award.config.sysenum.RoleEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUtils {

    //过期时间
    private static Long expiryDate = 60L * 60L * 1000L;
    //签名
    private static String sign = "suibian";
    //前缀
    private static String prefix = "Bearer ";
    //请求头
    private static String header = "Authorization";

    public static String createToken(String username, RoleEnum roleEnum) {
        return prefix +
                Jwts.builder()
                        .signWith(SignatureAlgorithm.HS512, sign)
                        .setHeaderParam("typ", "JWT")
                        .claim("username", username)
                        .claim("role", roleEnum.toString())
                        .setExpiration(new Date(System.currentTimeMillis() + expiryDate))
                        .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(sign)
                .parseClaimsJws(token.replace(prefix, ""))
                .getBody();
    }

    public static String getUsername(String token) {
        return (String) parseToken(token).get("username");
    }

    public static RoleEnum getRoleEnum(String token) {
        return RoleEnum.valueOf((String) parseToken(token).get("role"));
    }


    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
        RoleEnum roleEnum = getRoleEnum(token);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
        return authorities;
    }

    public static Long getExpiryDate() {
        return expiryDate;
    }

    public static String getSign() {
        return sign;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getHeader() {
        return header;
    }
}
