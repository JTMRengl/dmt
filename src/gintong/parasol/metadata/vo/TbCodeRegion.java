package gintong.parasol.metadata.vo;
// Generated 2016-6-2 19:11:41 by Hibernate Tools 4.3.1.Final

/**
 * TbCodeRegion generated by hbm2java
 */
public class TbCodeRegion implements java.io.Serializable {

	private long id;
	private long parentId;
	private String numberCode;
	private String cname;
	private String ename;
	private Integer type;
	private Integer orders;
	private String tbId;

	public TbCodeRegion() {
	}

	public TbCodeRegion(long parentId, String numberCode, String cname, String ename, Integer type, Integer orders,
			String tbId) {
		this.parentId = parentId;
		this.numberCode = numberCode;
		this.cname = cname;
		this.ename = ename;
		this.type = type;
		this.orders = orders;
		this.tbId = tbId;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return this.parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getNumberCode() {
		return this.numberCode;
	}

	public void setNumberCode(String numberCode) {
		this.numberCode = numberCode;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getTbId() {
		return this.tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId;
	}

}
