package com.remind.persistence;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author lenovo
 */
public class BaseDomain implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    private Boolean isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean delete) {
        isDelete = delete;
    }
}
