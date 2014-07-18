package com.joe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AC0001 implements Serializable {
	private static final long serialVersionUID = 8725982387554688522L;
	
	private int id;
	private String badgeCode;
	private String desc; //for system path, use --> to indicate which the system operation
	private Date createDate;
	
	private Set<AC0001> children;
	private AC0001 parent;
	private Set<AC0002> permissions;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBadgeCode() {
		return badgeCode;
	}
	public void setBadgeCode(String badgeCode) {
		this.badgeCode = badgeCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@OneToMany(mappedBy="parent")
	public Set<AC0001> getChildren() {
		return children;
	}
	public void setChildren(Set<AC0001> children) {
		this.children = children;
	}
	@ManyToOne
	@JoinColumn(name="pId")
	public AC0001 getParent() {
		return parent;
	}
	public void setParent(AC0001 parent) {
		this.parent = parent;
	}
	@ManyToMany(
			mappedBy="assignmentObjects"
	)
	public Set<AC0002> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<AC0002> permissions) {
		this.permissions = permissions;
	}
	
}
