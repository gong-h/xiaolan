package com.xiaolan.authority.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auth_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name", nullable = false, unique = true, length = 15)
    private String name;
    @Column(name = "description", nullable = false, length = 60)
    private String description;// 描述
    private String sn;// 英文标识，如:user,auth等
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Menu> menus = new ArrayList<Menu>();
    @Transient
    private boolean checked = false;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + ", menus=" + menus + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
