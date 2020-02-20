package xyz.xhui.award;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.xhui.award.config.sysenum.RoleEnum;
import xyz.xhui.award.config.utils.PasswordUtils;
import xyz.xhui.award.dao.UserDao;
import xyz.xhui.award.model.SysUser;
import xyz.xhui.award.config.utils.JwtUtils;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class AwardApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        System.out.println("*********************************");
        System.out.println(userDao.findById(1));
        Optional<SysUser> byId = userDao.findById(2);
        SysUser sysUser = byId.orElse(null);
        if (sysUser == null) {
            System.out.println("空值");
        } else {
            System.out.println(sysUser);
        }
        System.out.println("*********************************");
    }

    @Test
    void test1() {
        System.out.println(RoleEnum.getValue(0).toString());
        System.out.println(RoleEnum.values());
        System.out.println(RoleEnum.valueOf("ROLE_ADMIN"));
        System.out.println(RoleEnum.ROLE_HOUSEPARENT.toString());
    }

    @Test
    void test2() {
        String token = Jwts.builder()
                .setSubject("niceyoo")
                .claim("authorities", "admin")
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "tmax")
                .compact();
        System.out.println(token);
    }

    @Test
    void test3() {
        System.out.println(new BCryptPasswordEncoder().encode("1730090712"));
    }

    @Test
    void test4() {
        System.out.println(JwtUtils.createToken("qilihui", RoleEnum.ROLE_ADMIN));
    }

    @Test
    void test5() {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoicWlsaWh1aSIsInJvbGUiOiJST0xFX0FETUlOIiwiZXhwIjoxNTgxMDgzMzU2fQ.VTF1ds9Q02RPfqy1zixA5NQP9cpzONJ3Izxh8VIxI3ZWN9xUj8cAq7aol8Ya6ZodoA41ckFSFJkiiHyp6Vdwmw";
        System.out.println(JwtUtils.getUsername(token));
//        System.out.println(JwtUtils.getRoleEnum(token).toString());
    }

    @Test
    void test6() {
        System.out.println(PasswordUtils.encode("admin"));
        // $2a$10$dzpiNwZoE7b419uQngjaOOV/GWxy4amhLqGMjiLLysXWVi4GNmjke
    }

    @Test
    void test7() {

    }
}
