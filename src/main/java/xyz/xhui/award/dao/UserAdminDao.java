package xyz.xhui.award.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.award.model.SysUserAdmin;

public interface UserAdminDao  extends JpaRepository<SysUserAdmin, Integer> {
}
