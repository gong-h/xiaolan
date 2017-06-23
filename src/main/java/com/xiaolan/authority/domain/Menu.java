package com.xiaolan.authority.domain;

import javax.persistence.*;

@Entity
@Table(name = "auth_menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;// 菜单的名称，中文显示名称
	private String sn;// 菜单的唯一英文标识，如:user,auth等
	private Integer orderNum;// 菜单的顺序
	private Integer display;// 是否显示，0表示不显示，1表示显示
	private String href;// 菜单的链接地址
	private Integer pid;// 上一级菜单
	private String pname;
	private String psn;// 父类的sn
	private String icon;// 菜单的图标
	private String type;// 菜单类型，1：导航菜单；2：权限菜单

	private String handle;// 操作

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", href=" + href + "]";
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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}
}
