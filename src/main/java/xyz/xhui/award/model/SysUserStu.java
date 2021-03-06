package xyz.xhui.award.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user_stu")
public class SysUserStu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @OneToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private SysDept dept;

    @OneToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private SysGrade grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public SysGrade getGrade() {
        return grade;
    }

    public void setGrade(SysGrade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SysUserStu{" +
                "id=" + id +
                ", user=" + user +
                ", dept=" + dept +
                ", grade=" + grade +
                '}';
    }
}
