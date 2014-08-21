package entity;

public class FingerSiteUserIdInfo {
	private int id; 
	private int userId; 
	private String branchSite;
	
	private MP1001 employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBranchSite() {
		return branchSite;
	}

	public void setBranchSite(String branchSite) {
		this.branchSite = branchSite;
	}

	public MP1001 getEmployee() {
		return employee;
	}

	public void setEmployee(MP1001 employee) {
		this.employee = employee;
	}
}