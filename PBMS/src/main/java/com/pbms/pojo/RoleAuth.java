package com.pbms.pojo;

public class RoleAuth {
    private Integer id;

    private BoRole role;

    private BoAuth auth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BoRole getRole() {
        return role;
    }

    public void setRole(BoRole role) {
        this.role = role;
    }

    public BoAuth getAuth() {
        return auth;
    }

    public void setAuth(BoAuth auth) {
        this.auth = auth;
    }

}