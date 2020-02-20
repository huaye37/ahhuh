package xyz.xhui.award.config.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.xhui.award.model.*;

public class PasswordUtils {
    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static void hiddenPassword(SysUser user) {
        user.setPassword("******");
    }

    public static void hiddenPassword(SysUserAdmin userAdmin) {
        userAdmin.getUser().setPassword("******");
    }

    public static void hiddenPassword(SysUserStu userStu) {
        userStu.getUser().setPassword("******");
    }

    public static void hiddenPassword(SysUserTutor userTutor) {
        userTutor.getUser().setPassword("******");
    }

    public static void hiddenPassword(SysUserUnion userUnion) {
        userUnion.getUser().setPassword("******");
    }
}
