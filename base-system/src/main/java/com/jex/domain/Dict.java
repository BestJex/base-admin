package com.jex.domain;

import lombok.Getter;
import lombok.Setter;
import com.jex.base.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author jex
* @version 1.0
*/
@Entity
@Getter
@Setter
@Table(name="dict")
public class Dict implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    @NotBlank
    private String name;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @OneToMany(mappedBy = "dict",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<DictDetail> dictDetails;

    public @interface Update {}
}