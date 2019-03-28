package com.qezhhnjy.login.entity;

import com.qezhhnjy.login.util.Phone;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fuzy
 * create time 19-3-24-上午11:21
 */

@Entity
@Data
@Accessors(chain = true)
@Table(name = "user")
public class User {

    /*
        // 声明一个策略通用生成器
        @GenericGenerator(name = "generator", strategy = "native")
        // 用generator属性指定要使用的策略生成器
        @GeneratedValue(generator = "generator")
    */
    @Id
    @GeneratedValue
    private Integer userId;
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 16, message = "用户名必须在6-16位之间")
    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String realName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 32, message = "密码必须在6-32位之间")
    @Column(nullable = false)
    private String password;

    @Column
    @Email(message = "请输入正确的邮箱信息")
    private String email;

    @Column
    @Phone
    private String phone;

    @Column
    private String weChat;

    @Column
    private String aliPay;

    @Column
    @Range(min = 1, max = 150, message = "年龄输入有误")
    private Integer age;

    @Column(columnDefinition = "enum('male','female','secret') default 'secret'")
    private String gender;

    private String salt;

    @Column
    private byte state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "null"))},
            inverseJoinColumns = {@JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "null"))})
    private List<Role> roles;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expire;

    /**
     * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
     *
     * @return
     */
    public String getCredentialsSalt() {
        return username + salt;
    }
}
