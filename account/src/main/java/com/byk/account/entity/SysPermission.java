package com.byk.account.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

/**
 * 资源表 实体类
 * @author yikai.bi
 */
@Entity
@Table(name="sys_permission")
@EntityListeners(AuditingEntityListener.class)
public class SysPermission extends AutoIDEntity{

    /**
     * 名称
     */
    private String name;
    /**
     * 类型：
     * 菜单：1，按钮：2
     */
    private Integer type;
    /**
     * 权限编码
     */
    private String code;
    /**
     * url
     */
    private String url;
    /**
     * 父资源id,给它初始值 0
     * 新增和修改页面上默认的父资源id
     */
    private Long parentId = 0L ;

    /**
     * 图标
     */
    private String icon;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createDate;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateDate;


    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    @Column(name="code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Column(name="url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name="parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Column(name="icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name="remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name="create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name="update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
