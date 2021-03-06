package gintong.permission.test.now;
// Generated 2016-5-28 10:05:04 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * TbPermission generated by hbm2java
 */
public class TbPermission implements java.io.Serializable {

	private Long perId;
	private long resId;
	private short resType;
	private String resAccRule;
	private long resOwnerId;
	private int publicFlag;
	private Integer connectFlag;
	private Integer shareFlag;
	private Date perTime;
	private long appId;

	public TbPermission() {
	}

	public TbPermission(long resId, short resType, String resAccRule, long resOwnerId, int publicFlag, Date perTime) {
		this.resId = resId;
		this.resType = resType;
		this.resAccRule = resAccRule;
		this.resOwnerId = resOwnerId;
		this.publicFlag = publicFlag;
		this.perTime = perTime;
	}

	public TbPermission(long resId, short resType, String resAccRule, long resOwnerId, int publicFlag,
			Integer connectFlag, Integer shareFlag, Date perTime, long appId) {
		this.resId = resId;
		this.resType = resType;
		this.resAccRule = resAccRule;
		this.resOwnerId = resOwnerId;
		this.publicFlag = publicFlag;
		this.connectFlag = connectFlag;
		this.shareFlag = shareFlag;
		this.perTime = perTime;
		this.appId = appId;
	}

	public Long getPerId() {
		return this.perId;
	}

	public void setPerId(Long perId) {
		this.perId = perId;
	}

	public long getResId() {
		return this.resId;
	}

	public void setResId(long resId) {
		this.resId = resId;
	}

	public short getResType() {
		return this.resType;
	}

	public void setResType(short resType) {
		this.resType = resType;
	}

	public String getResAccRule() {
		return this.resAccRule;
	}

	public void setResAccRule(String resAccRule) {
		this.resAccRule = resAccRule;
	}

	public long getResOwnerId() {
		return this.resOwnerId;
	}

	public void setResOwnerId(long resOwnerId) {
		this.resOwnerId = resOwnerId;
	}

	public int getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(int publicFlag) {
		this.publicFlag = publicFlag;
	}

	public Integer getConnectFlag() {
		return this.connectFlag;
	}

	public void setConnectFlag(Integer connectFlag) {
		this.connectFlag = connectFlag;
	}

	public Integer getShareFlag() {
		return this.shareFlag;
	}

	public void setShareFlag(Integer shareFlag) {
		this.shareFlag = shareFlag;
	}

	public Date getPerTime() {
		return this.perTime;
	}

	public void setPerTime(Date perTime) {
		this.perTime = perTime;
	}

	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

}
