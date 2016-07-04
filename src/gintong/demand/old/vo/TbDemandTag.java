package gintong.demand.old.vo;
// Generated 2016-5-25 14:28:32 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * TbDemandTag generated by hbm2java
 */
public class TbDemandTag implements java.io.Serializable {

	private Integer id;
	private Long demandId;
	private Long tagId;
	private Date ctime;

	public TbDemandTag() {
	}

	public TbDemandTag(Date ctime) {
		this.ctime = ctime;
	}

	public TbDemandTag(Long demandId, Long tagId, Date ctime) {
		this.demandId = demandId;
		this.tagId = tagId;
		this.ctime = ctime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public Long getTagId() {
		return this.tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
