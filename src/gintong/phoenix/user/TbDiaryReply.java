package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbDiaryReply generated by hbm2java
 */
public class TbDiaryReply implements java.io.Serializable {

	private Long id;
	private Long diaryId;
	private Long userId;
	private String userName;
	private Long parentId;
	private Long onepid;
	private String content;
	private String simpleContent;
	private String remark;
	private String ctime;
	private Integer level;
	private Integer status;
	private Integer del;
	private Integer shieldType;
	private String reason;
	private String cztime;

	public TbDiaryReply() {
	}

	public TbDiaryReply(Long diaryId, Long userId, String userName, Long parentId, Long onepid, String content,
			String simpleContent, String remark, String ctime, Integer level, Integer status, Integer del,
			Integer shieldType, String reason, String cztime) {
		this.diaryId = diaryId;
		this.userId = userId;
		this.userName = userName;
		this.parentId = parentId;
		this.onepid = onepid;
		this.content = content;
		this.simpleContent = simpleContent;
		this.remark = remark;
		this.ctime = ctime;
		this.level = level;
		this.status = status;
		this.del = del;
		this.shieldType = shieldType;
		this.reason = reason;
		this.cztime = cztime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDiaryId() {
		return this.diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getOnepid() {
		return this.onepid;
	}

	public void setOnepid(Long onepid) {
		this.onepid = onepid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSimpleContent() {
		return this.simpleContent;
	}

	public void setSimpleContent(String simpleContent) {
		this.simpleContent = simpleContent;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCtime() {
		return this.ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDel() {
		return this.del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Integer getShieldType() {
		return this.shieldType;
	}

	public void setShieldType(Integer shieldType) {
		this.shieldType = shieldType;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCztime() {
		return this.cztime;
	}

	public void setCztime(String cztime) {
		this.cztime = cztime;
	}

}