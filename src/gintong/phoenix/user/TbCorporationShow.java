package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbCorporationShow generated by hbm2java
 */
public class TbCorporationShow implements java.io.Serializable {

	private Long id;
	private String picId;
	private String fileds;
	private long corpId;

	public TbCorporationShow() {
	}

	public TbCorporationShow(long corpId) {
		this.corpId = corpId;
	}

	public TbCorporationShow(String picId, String fileds, long corpId) {
		this.picId = picId;
		this.fileds = fileds;
		this.corpId = corpId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicId() {
		return this.picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getFileds() {
		return this.fileds;
	}

	public void setFileds(String fileds) {
		this.fileds = fileds;
	}

	public long getCorpId() {
		return this.corpId;
	}

	public void setCorpId(long corpId) {
		this.corpId = corpId;
	}

}