package xyz.xhui.award.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.award.model.SysUserStu;

public interface UserStuDao extends JpaRepository<SysUserStu, Integer> {
}
