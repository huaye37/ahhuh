package xyz.xhui.award.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.award.model.SysUserTutor;

public interface UserTutorDao extends JpaRepository<SysUserTutor, Integer> {
}
