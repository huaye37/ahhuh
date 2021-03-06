package xyz.xhui.award.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user_admin")
public class SysUserAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

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

    @Override
    public String toString() {
        return "SysUserAdmin{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
