package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbRUserGroup generated by hbm2java
 */
public class TbRUserGroup implements java.io.Serializable {

	private Long id;
	private Long groupId;
	private Long RUserId;

	public TbRUserGroup() {
	}

	public TbRUserGroup(Long groupId, Long RUserId) {
		this.groupId = groupId;
		this.RUserId = RUserId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getRUserId() {
		return this.RUserId;
	}

	public void setRUserId(Long RUserId) {
		this.RUserId = RUserId;
	}

}
