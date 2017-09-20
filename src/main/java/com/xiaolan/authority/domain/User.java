package com.xiaolan.authority.domain;

import com.xiaolan.authority.domain.dto.MenuDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "auth_user")
public class User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 36)
    private String username;
    @Column(name = "nickname", nullable = false, length = 36)
    private String nickname;// 用户昵称
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "status", nullable = false)
    private Integer status;// 状态
    @Column(name = "createDate", updatable = false)
    private Date createDate = new Date();// 创建时间
    @Column(name = "isAdmin")
    private Integer isAdmin;// 是否是管理员
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;
    @Column(name = "phone", length = 15)
    private String phone;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<Role>();
    @Transient
    private Collection<MenuDto> menuTree;

    public User() {
    }

    public User(String username, String nickname, String password, Integer status, boolean enabled, String phone) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.status = status;
        this.enabled = enabled;
        this.phone = phone;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        for (Role role : this.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", isAdmin=" + isAdmin +
                ", enabled=" + enabled +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                ", menuTree=" + menuTree +
                '}';
    }

    public Collection<MenuDto> getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(Collection<MenuDto> menuTree) {
        this.menuTree = menuTree;
    }
}
