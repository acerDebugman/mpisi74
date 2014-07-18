package com.joe.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class AC0002 implements Serializable {
	private static final long serialVersionUID = -4998887017183658443L;

	private int id;
	private String permissionName;
	private String desc; //use -->  map to system function and menu
	
	private Set<AC0001> assignmentObjects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ManyToMany
	@JoinTable(
			name="P_A",
			joinColumns={@JoinColumn(name="assignmentObjectId")},
			inverseJoinColumns={@JoinColumn(name="permissionId")}
	)
	public Set<AC0001> getAssignmentObjects() {
		return assignmentObjects;
	}

	public void setAssignmentObjects(Set<AC0001> assignmentObjects) {
		this.assignmentObjects = assignmentObjects;
	}
}