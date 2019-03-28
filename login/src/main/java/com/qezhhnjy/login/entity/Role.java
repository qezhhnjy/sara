package com.qezhhnjy.login.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author fuzy
 * create time 19-3-26-下午8:41
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Integer roleId;

    @Column(nullable = false, unique = true)
    private String role;

    @Column
    private String description;

    @Column
    private Boolean available = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "null"))},
            inverseJoinColumns = {@JoinColumn(name = "permissionId", foreignKey = @ForeignKey(name = "null"))})
    private List<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "null"))},
            inverseJoinColumns = {@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "null"))})
    private List<User> users;
}
