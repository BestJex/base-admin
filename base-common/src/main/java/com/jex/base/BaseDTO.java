package com.jex.base;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Jex
 *
 */
@Getter
@Setter
public class BaseDTO  implements Serializable {

    private Boolean isDelete;

    private Timestamp createTime;

    private Timestamp updateTime;

    @Override
    public String toString() {
        return "BaseDTO{" +
                "isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
