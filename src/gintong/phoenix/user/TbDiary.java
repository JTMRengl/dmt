package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * TbDiary generated by hbm2java
 */
public class TbDiary implements java.io.Serializable {

	private Long id;
	private Long fbrid;
	private String fname;
	private Long titleId;
	private String titleName;
	private String type;
	private Long requirementTypeId;
	private String content;
	private Date ctime;
	private Long infoVisible;
	private Integer status;
	private Integer shieldType;
	private String reason;
	private String oldContent;
	private String imgPath;
	private Integer diaryStatus;
	private String cztime;
	private String creator;

	public TbDiary() {
	}

	public TbDiary(Long fbrid, String fname, Long titleId, String titleName, String type, Long requirementTypeId,
			String content, Date ctime, Long infoVisible, Integer status, Integer shieldType, String reason,
			String oldContent, String imgPath, Integer diaryStatus, String cztime, String creator) {
		this.fbrid = fbrid;
		this.fname = fname;
		this.titleId = titleId;
		this.titleName = titleName;
		this.type = type;
		this.requirementTypeId = requirementTypeId;
		this.content = content;
		this.ctime = ctime;
		this.infoVisible = infoVisible;
		this.status = status;
		this.shieldType = shieldType;
		this.reason = reason;
		this.oldContent = oldContent;
		this.imgPath = imgPath;
		this.diaryStatus = diaryStatus;
		this.cztime = cztime;
		this.creator = creator;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFbrid() {
		return this.fbrid;
	}

	public void setFbrid(Long fbrid) {
		this.fbrid = fbrid;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Long getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return this.titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getRequirementTypeId() {
		return this.requirementTypeId;
	}

	public void setRequirementTypeId(Long requirementTypeId) {
		this.requirementTypeId = requirementTypeId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Long getInfoVisible() {
		return this.infoVisible;
	}

	public void setInfoVisible(Long infoVisible) {
		this.infoVisible = infoVisible;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getOldContent() {
		return this.oldContent;
	}

	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getDiaryStatus() {
		return this.diaryStatus;
	}

	public void setDiaryStatus(Integer diaryStatus) {
		this.diaryStatus = diaryStatus;
	}

	public String getCztime() {
		return this.cztime;
	}

	public void setCztime(String cztime) {
		this.cztime = cztime;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
