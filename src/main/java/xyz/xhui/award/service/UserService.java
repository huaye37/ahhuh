package xyz.xhui.award.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.xhui.award.config.exception.EntityFieldException;
import xyz.xhui.award.config.sysenum.RoleEnum;
import xyz.xhui.award.model.SysUser;

import java.util.Optional;


public interface UserService extends UserDetailsService {
    /**
     * 分页查询所有用户
     * @return
     */
    Page<SysUser> findAll(Integer pagenum, Integer pagesize);

    /**
     * 根据id查询用户
     * @return
     */
    Optional<SysUser> findById(Integer id);

    /**
     * 根据登录用户名查询
     * @param username
     * @return
     */
    SysUser findByUsernameEquals(String username);

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    SysUser save(SysUser sysUser) throws EntityFieldException;

    Boolean deleteById(Integer id);
}
