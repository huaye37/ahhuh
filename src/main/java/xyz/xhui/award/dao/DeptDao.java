package xyz.xhui.award.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.award.model.SysDept;

public interface DeptDao extends JpaRepository<SysDept, Integer> {
}
