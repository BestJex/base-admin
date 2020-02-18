package com.jex.data.entity;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户头像实体类
 * @author Jex
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "system_user_avatar")
public class UserAvatar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String realName;

    private String path;

    private String size;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    public UserAvatar(UserAvatar userAvatar,String realName, String path, String size) {
        this.id = ObjectUtil.isNotEmpty(userAvatar) ? userAvatar.getId() : null;
        this.realName = realName;
        this.path = path;
        this.size = size;
    }
}
