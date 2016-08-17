package com.ysp.ssm.demo.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 基类，包含插入字段和更新字段
 */
@MappedSuperclass
public class BasicGenericModel implements Serializable {
    /**
     * 插入时的时间戳
     */

    @Column(name = "created_ts", columnDefinition = " TIMESTAMP  NOT NULL")
    public Date createdTs;

    /**
     * 更新时的时间戳
     */
    @Column(name = "updated_ts", columnDefinition = " TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date updatedTs;

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Date getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Date updatedTs) {
        this.updatedTs = updatedTs;
    }

}
