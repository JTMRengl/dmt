package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * TbGroup generated by hbm2java
 */
public class TbGroup implements java.io.Serializable {

	private Long id;
	private String name;
	private Long userId;
	private Date ctime;
	private Date utime;
	private String remark;
	private Integer virtual;

	public TbGroup() {
	}

	public TbGroup(String name, Long userId, Date ctime, Date utime, String remark, Integer virtual) {
		this.name = name;
		this.userId = userId;
		this.ctime = ctime;
		this.utime = utime;
		this.remark = remark;
		this.virtual = virtual;
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

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getVirtual() {
		return this.virtual;
	}

	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}

}