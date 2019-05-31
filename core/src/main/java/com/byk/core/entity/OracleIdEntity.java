package com.byk.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@MappedSuperclass
public class OracleIdEntity implements java.io.Serializable{

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
        if (!(other instanceof OracleIdEntity))
            return false;
        OracleIdEntity castOther = (OracleIdEntity) other;
        return new EqualsBuilder().append(id, castOther.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
}
