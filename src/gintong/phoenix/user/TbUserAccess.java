package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * TbUserAccess generated by hbm2java
 */
public class TbUserAccess implements java.io.Serializable {

	private Long id;
	private Long userId;
	private Long accessUserId;
	private String relation;
	private Date ctime;

	public TbUserAccess() {
	}

	public TbUserAccess(Long userId, Long accessUserId, String relation, Date ctime) {
		this.userId = userId;
		this.accessUserId = accessUserId;
		this.relation = relation;
		this.ctime = ctime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAccessUserId() {
		return this.accessUserId;
	}

	public void setAccessUserId(Long accessUserId) {
		this.accessUserId = accessUserId;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
