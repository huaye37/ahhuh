package xyz.xhui.award.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.award.model.SysGrade;

public interface GradeDao extends JpaRepository<SysGrade, Integer> {
}
