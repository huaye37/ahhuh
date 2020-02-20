package xyz.xhui.award.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xhui.award.model.SysUser;

import java.util.List;

public interface UserDao extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {
    List<SysUser> findByUsernameEquals(String username);
}
