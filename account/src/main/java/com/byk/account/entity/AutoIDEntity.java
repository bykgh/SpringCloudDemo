package com.byk.account.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.lang.reflect.Field;

/**
 * id 和 乐观锁 自增长
 * @author  yikai.bi
 */
@MappedSuperclass
public class AutoIDEntity implements java.io.Serializable {
    /**
     *
     */
    private Log log = LogFactory.getLog(AutoIDEntity.class);
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer optimistic;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    @Version
    public Integer getOptimistic() {
        return this.optimistic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof AutoIDEntity)) {
            return false;
        }
        AutoIDEntity castOther = (AutoIDEntity) other;
        return new EqualsBuilder().append(id, castOther.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString() {
        Class<?> c = this.getClass();
        Field[] fields = c.getDeclaredFields();

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" {");

        int i = 1;
        for (Field fd : fields) {
            fd.setAccessible(true);
            sb.append(fd.getName());
            sb.append(":");
            try {
                sb.append(fd.get(this));
            } catch (IllegalAccessException e) {
                log.error(e,e);
            }
            if (i != fields.length) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("}");
        return sb.toString();
    }
}
