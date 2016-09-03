package com.ysp.ssm.demo.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: shipeng.yu
 * @time: 16/9/3-下午9:55
 * @version: 1.0
 * @description: 基类, 包含插入字段和更新字段, 及ID
 */
@MappedSuperclass
public class BasicModel extends BasicGenericModel implements Serializable {
    /**
     * 主键自增长
     */
    @Id
    @GeneratedValue
    public Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
