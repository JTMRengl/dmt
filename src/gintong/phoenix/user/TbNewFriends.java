package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbNewFriends generated by hbm2java
 */
public class TbNewFriends implements java.io.Serializable {

	private Long id;
	private Long creatorId;
	private Long userId;
	private Long peopleId;
	private Integer status;
	private Integer type;
	private Integer mate;
	private String ctime;
	private String utime;

	public TbNewFriends() {
	}

	public TbNewFriends(Long creatorId, Long userId, Long peopleId, Integer status, Integer type, Integer mate,
			String ctime, String utime) {
		this.creatorId = creatorId;
		this.userId = userId;
		this.peopleId = peopleId;
		this.status = status;
		this.type = type;
		this.mate = mate;
		this.ctime = ctime;
		this.utime = utime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPeopleId() {
		return this.peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMate() {
		return this.mate;
	}

	public void setMate(Integer mate) {
		this.mate = mate;
	}

	public String getCtime() {
		return this.ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getUtime() {
		return this.utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

}
