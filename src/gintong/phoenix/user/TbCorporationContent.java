package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbCorporationContent generated by hbm2java
 */
public class TbCorporationContent implements java.io.Serializable {

	private Long id;
	private String title;
	private String content;
	private String ctime;
	private String utime;
	private Integer type;
	private String categoryIds;
	private String categoryNames;
	private Integer status;
	private Long orgId;

	public TbCorporationContent() {
	}

	public TbCorporationContent(String title, String content, String ctime, String utime, Integer type,
			String categoryIds, String categoryNames, Integer status, Long orgId) {
		this.title = title;
		this.content = content;
		this.ctime = ctime;
		this.utime = utime;
		this.type = type;
		this.categoryIds = categoryIds;
		this.categoryNames = categoryNames;
		this.status = status;
		this.orgId = orgId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCategoryIds() {
		return this.categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getCategoryNames() {
		return this.categoryNames;
	}

	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}