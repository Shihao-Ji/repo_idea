package com.lagou.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Role_Resource_Relation {

    // 关系id
    private Integer id;

    // 资源id
    private Integer resourceId;

    // 角色id
    private Integer roleId;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    // 创建人
    private String createdBy;

    // 更新人
    private String updatedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
