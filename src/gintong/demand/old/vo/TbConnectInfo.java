package gintong.demand.old.vo;
// Generated 2016-5-25 14:28:32 by Hibernate Tools 4.3.1.Final

/**
 * TbConnectInfo generated by hbm2java
 */
public class TbConnectInfo implements java.io.Serializable {

	private Long id;
	private Long demandId;
	private String tag;
	private Integer connType;
	private Long connId;
	private String connName;
	private Long ownerId;
	private String owner;
	private String requirementtype;
	private String career;
	private String company;
	private String address;
	private String hy;
	private String columnPath;
	private Integer columnType;
	private String url;
	private String picPath;
	private Integer allasso;

	public TbConnectInfo() {
	}

	public TbConnectInfo(Long demandId, String tag, Integer connType, Long connId, String connName, Long ownerId,
			String owner, String requirementtype, String career, String company, String address, String hy,
			String columnPath, Integer columnType, String url, String picPath, Integer allasso) {
		this.demandId = demandId;
		this.tag = tag;
		this.connType = connType;
		this.connId = connId;
		this.connName = connName;
		this.ownerId = ownerId;
		this.owner = owner;
		this.requirementtype = requirementtype;
		this.career = career;
		this.company = company;
		this.address = address;
		this.hy = hy;
		this.columnPath = columnPath;
		this.columnType = columnType;
		this.url = url;
		this.picPath = picPath;
		this.allasso = allasso;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getConnType() {
		return this.connType;
	}

	public void setConnType(Integer connType) {
		this.connType = connType;
	}

	public Long getConnId() {
		return this.connId;
	}

	public void setConnId(Long connId) {
		this.connId = connId;
	}

	public String getConnName() {
		return this.connName;
	}

	public void setConnName(String connName) {
		this.connName = connName;
	}

	public Long getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRequirementtype() {
		return this.requirementtype;
	}

	public void setRequirementtype(String requirementtype) {
		this.requirementtype = requirementtype;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHy() {
		return this.hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	public String getColumnPath() {
		return this.columnPath;
	}

	public void setColumnPath(String columnPath) {
		this.columnPath = columnPath;
	}

	public Integer getColumnType() {
		return this.columnType;
	}

	public void setColumnType(Integer columnType) {
		this.columnType = columnType;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Integer getAllasso() {
		return this.allasso;
	}

	public void setAllasso(Integer allasso) {
		this.allasso = allasso;
	}

}
