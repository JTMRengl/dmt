package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * TbRole generated by hbm2java
 */
public class TbRole implements java.io.Serializable {

	private Long id;
	private String name;
	private Boolean status;
	private String permissionString;
	private String remark;
	private String description;
	private Boolean mangerRole;
	private Long createById;
	private String createBy;
	private Date ctime;
	private Date utime;

	public TbRole() {
	}

	public TbRole(String name, Boolean status, String permissionString, String remark, String description,
			Boolean mangerRole, Long createById, String createBy, Date ctime, Date utime) {
		this.name = name;
		this.status = status;
		this.permissionString = permissionString;
		this.remark = remark;
		this.description = description;
		this.mangerRole = mangerRole;
		this.createById = createById;
		this.createBy = createBy;
		this.ctime = ctime;
		this.utime = utime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getPermissionString() {
		return this.permissionString;
	}

	public void setPermissionString(String permissionString) {
		this.permissionString = permissionString;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getMangerRole() {
		return this.mangerRole;
	}

	public void setMangerRole(Boolean mangerRole) {
		this.mangerRole = mangerRole;
	}

	public Long getCreateById() {
		return this.createById;
	}

	public void setCreateById(Long createById) {
		this.createById = createById;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getUtime() {
		return this.utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

}
