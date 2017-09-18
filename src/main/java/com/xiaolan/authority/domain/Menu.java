package com.xiaolan.authority.domain;

import javax.persistence.*;

@Entity
@Table(name = "auth_menu")
public class Menu {
    private Integer id;
    private String name;// 菜单的名称，中文显示名称
    private String sn;// 菜单的唯一英文标识，如:user,auth等
    private Integer orderNum;// 菜单的顺序
    private Integer display = 1;// 是否显示，0表示不显示，1表示显示
    private String href;// 菜单的链接地址
    private Integer pid;// 上一级菜单
    private String pname;
    private String psn;// 父类的sn
    private String icon;// 菜单的图标
    private Integer type;// 1：一级导航菜单；2：二级导航菜单 0:权限菜单
    private String handle;// 操作
    private String menuid;
    private Integer displayno;

    private boolean checked = false;

    public Menu() {
    }

    public Menu(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(name = "orderNum")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "display")
    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    @Column(name = "href", length = 150)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Column(name = "pid")
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    @Column(name = "icon", length = 100)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "handle")
    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Column(name = "menuid", length = 10)
    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public Integer getDisplayno() {
        return displayno;
    }

    public void setDisplayno(Integer displayno) {
        this.displayno = displayno;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Menu) {
            return this.getId().equals(((Menu) obj).getId());
        } else {
            return false;
        }
    }

    @Transient
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", pid=" + pid +
                ", displayno=" + displayno +
                ", checked=" + checked +
                '}';
    }
}
