package gintong.parasol.directory.test.now;
// Generated 2016-5-26 15:08:34 by Hibernate Tools 4.3.1.Final

/**
 * TbDirectory generated by hbm2java
 */
public class TbDirectory implements java.io.Serializable {

	private long id;
	private long pid;
	private long appId;
	private long userId;
	private String name;
	private String nameIndex;
	private String nameIndexAll;
	private String remark;
	private String numberCode;
	private Integer orderNo;
	private long typeId;
	private Long updateAt;

	public TbDirectory() {
	}

	public TbDirectory(long id, long pid, long appId, long userId, String name, long typeId) {
		this.id = id;
		this.pid = pid;
		this.appId = appId;
		this.userId = userId;
		this.name = name;
		this.typeId = typeId;
	}

	public TbDirectory(long id, long pid, long appId, long userId, String name, String nameIndex, String nameIndexAll,
			String remark, String numberCode, Integer orderNo, long typeId, Long updateAt) {
		this.id = id;
		this.pid = pid;
		this.appId = appId;
		this.userId = userId;
		this.name = name;
		this.nameIndex = nameIndex;
		this.nameIndexAll = nameIndexAll;
		this.remark = remark;
		this.numberCode = numberCode;
		this.orderNo = orderNo;
		this.typeId = typeId;
		this.updateAt = updateAt;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameIndex() {
		return this.nameIndex;
	}

	public void setNameIndex(String nameIndex) {
		this.nameIndex = nameIndex;
	}

	public String getNameIndexAll() {
		return this.nameIndexAll;
	}

	public void setNameIndexAll(String nameIndexAll) {
		this.nameIndexAll = nameIndexAll;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNumberCode() {
		return this.numberCode;
	}

	public void setNumberCode(String numberCode) {
		this.numberCode = numberCode;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public Long getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Long updateAt) {
		this.updateAt = updateAt;
	}

}
