package xyz.xhui.award.service;

import xyz.xhui.award.model.SysDept;

import java.util.List;
import java.util.Optional;

public interface DeptService {
    /**
     * 添加系部
     * @param dept
     * @return
     */
    SysDept save(SysDept dept);

    /**
     * 查询所有系部
     * @return
     */
    List<SysDept> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Optional<SysDept> findById(Integer id);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean deleteById(Integer id);
}
