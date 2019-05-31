package com.byk.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@MappedSuperclass

public class BaseEntity implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer optimistic;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
    @Column(columnDefinition = "NUMBER")
    public Long getId() {
        return this.id;
    }

    @Version
    @Column(columnDefinition = "NUMBER")
    public Integer getOptimistic() {
        return this.optimistic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof BaseEntity))
            return false;
        BaseEntity castOther = (BaseEntity) other;
        return new EqualsBuilder().append(id, castOther.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
}

