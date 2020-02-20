package xyz.xhui.award.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.award.model.SysUserUnion;

public interface UserUnionDao extends JpaRepository<SysUserUnion, Integer> {
}
