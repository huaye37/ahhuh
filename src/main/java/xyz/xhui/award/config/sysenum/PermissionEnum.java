package xyz.xhui.award.config.sysenum;

public enum PermissionEnum {
    PER_USER(1, "用户管理", RoleEnum.ROLE_ADMIN.getId());

    private Integer Id;
    private String name;
    private Integer roleId;

    PermissionEnum(Integer id, String name, Integer roleId) {
        Id = id;
        this.name = name;
        this.roleId = roleId;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Integer getRoleId() {
        return roleId;
    }
}