package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbCorporationContentReply generated by hbm2java
 */
public class TbCorporationContentReply implements java.io.Serializable {

	private Long id;
	private Long orgId;
	private Long contentId;
	private String ctime;
	private Long hfrId;
	private String content;
	private String hfrm;
	private Integer level;
	private Long parentId;
	private Long titleId;
	private String title;

	public TbCorporationContentReply() {
	}

	public TbCorporationContentReply(Long orgId, Long contentId, String ctime, Long hfrId, String content, String hfrm,
			Integer level, Long parentId, Long titleId, String title) {
		this.orgId = orgId;
		this.contentId = contentId;
		this.ctime = ctime;
		this.hfrId = hfrId;
		this.content = content;
		this.hfrm = hfrm;
		this.level = level;
		this.parentId = parentId;
		this.titleId = titleId;
		this.title = title;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getContentId() {
		return this.contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getCtime() {
		return this.ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public Long getHfrId() {
		return this.hfrId;
	}

	public void setHfrId(Long hfrId) {
		this.hfrId = hfrId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHfrm() {
		return this.hfrm;
	}

	public void setHfrm(String hfrm) {
		this.hfrm = hfrm;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
