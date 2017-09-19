package com.xiaolan.authority.domain.dto;


import com.xiaolan.authority.domain.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单显示
 **/
public class MenuDto {
    private Integer id;
    private String href;
    private String name;
    private Integer orderNum;
    private Integer pid;
    private Integer type;
    private String menuid;
    private List<MenuDto> sonMenus;

    public MenuDto() {
    }

    public MenuDto(Menu menu) {
        this.id = menu.getId();
        this.href = menu.getHref();
        this.name = menu.getName();
        this.orderNum = menu.getOrderNum();
        this.pid = menu.getPid();
        this.type = menu.getType();
        this.menuid = menu.getMenuid();
    }

    public MenuDto(Integer id, String href, String name, Integer orderNum, Integer pid, Integer type, String menuid) {
        this.id = id;
        this.href = href;
        this.name = name;
        this.orderNum = orderNum;
        this.pid = pid;
        this.type = type;
        this.menuid = menuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public List<MenuDto> getSonMenus() {
        return sonMenus;
    }

    public void setSonMenus(List<MenuDto> sonMenus) {
        this.sonMenus = sonMenus;
    }

    public void putMenuDto(MenuDto dto) {
        if (this.sonMenus == null) this.sonMenus = new ArrayList<MenuDto>();
        this.sonMenus.add(dto);
    }

    public void removeMenuDto(MenuDto dto){
        if (this.sonMenus == null) this.sonMenus = new ArrayList<MenuDto>();
        this.sonMenus.remove(dto);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MenuDto) {
            return this.getId().equals(((Menu) obj).getId());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "id=" + id +
                ", href='" + href + '\'' +
                ", name='" + name + '\'' +
                ", orderNum=" + orderNum +
                ", pid=" + pid +
                ", type=" + type +
                ", menuid='" + menuid + '\'' +
                ", sonMenus=" + sonMenus +
                '}';
    }
}
