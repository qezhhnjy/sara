package com.qezhhnjy.login.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author fuzy
 * create time 19-3-26-下午9:36
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue
    private Integer permissionId;

    @Column(nullable = false)
    private String name;

    //资源类型
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;

    @Column
    private String url;

    @Column
    private String permission;

    @Column
    private Long parentId;

    @Column
    private String parentIds;

    @Column
    private Boolean available = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "permissionId", foreignKey = @ForeignKey(name = "null"))},
            inverseJoinColumns = {@JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "null"))})
    private List<Role> roles;
}
