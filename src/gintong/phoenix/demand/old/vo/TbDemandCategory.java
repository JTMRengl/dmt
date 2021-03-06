package gintong.phoenix.demand.old.vo;
// Generated 2016-5-31 14:15:04 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * TbDemandCategory generated by hbm2java
 */
public class TbDemandCategory implements java.io.Serializable {

	private Integer id;
	private Long demandId;
	private Long categoryId;
	private Date ctime;

	public TbDemandCategory() {
	}

	public TbDemandCategory(Date ctime) {
		this.ctime = ctime;
	}

	public TbDemandCategory(Long demandId, Long categoryId, Date ctime) {
		this.demandId = demandId;
		this.categoryId = categoryId;
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

	public Long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
