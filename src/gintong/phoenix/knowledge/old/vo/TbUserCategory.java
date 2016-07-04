package gintong.phoenix.knowledge.old.vo;


// Generated 2016-6-5 9:54:44 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * TbUserCategory generated by hbm2java
 */
public class TbUserCategory implements java.io.Serializable {

	private Long id;
	private Long userId;
	private String categoryName;
	private String sortid;
	private Date createtime;
	private String pathName;
	private Long parentId;
	private Short categoryType;

	public TbUserCategory() {
	}

	public TbUserCategory(Long userId, String categoryName, String sortid, Date createtime, String pathName,
			Long parentId, Short categoryType) {
		this.userId = userId;
		this.categoryName = categoryName;
		this.sortid = sortid;
		this.createtime = createtime;
		this.pathName = pathName;
		this.parentId = parentId;
		this.categoryType = categoryType;
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

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSortid() {
		return this.sortid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPathName() {
		return this.pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Short getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(Short categoryType) {
		this.categoryType = categoryType;
	}

}