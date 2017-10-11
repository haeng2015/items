package com.pbms.pojo;

public class UserRole {
    private Integer id;

    private BoUser user;

    private BoRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BoUser getUser() {
        return user;
    }

    public void setUser(BoUser user) {
        this.user = user;
    }

    public BoRole getRole() {
        return role;
    }

    public void setRole(BoRole role) {
        this.role = role;
    }

}