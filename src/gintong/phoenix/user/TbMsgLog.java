package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * TbMsgLog generated by hbm2java
 */
public class TbMsgLog implements java.io.Serializable {

	private Long id;
	private Integer msgType;
	private Long userId;
	private String userName;
	private Long userOneId;
	private String userOne;
	private Long userTwoId;
	private String userTwo;
	private Long incId;
	private String incName;
	private String requestFlag;
	private Integer status;
	private Date ctime;
	private Date utime;
	private String remark;
	private String validate;

	public TbMsgLog() {
	}

	public TbMsgLog(Integer msgType, Long userId, String userName, Long userOneId, String userOne, Long userTwoId,
			String userTwo, Long incId, String incName, String requestFlag, Integer status, Date ctime, Date utime,
			String remark, String validate) {
		this.msgType = msgType;
		this.userId = userId;
		this.userName = userName;
		this.userOneId = userOneId;
		this.userOne = userOne;
		this.userTwoId = userTwoId;
		this.userTwo = userTwo;
		this.incId = incId;
		this.incName = incName;
		this.requestFlag = requestFlag;
		this.status = status;
		this.ctime = ctime;
		this.utime = utime;
		this.remark = remark;
		this.validate = validate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserOneId() {
		return this.userOneId;
	}

	public void setUserOneId(Long userOneId) {
		this.userOneId = userOneId;
	}

	public String getUserOne() {
		return this.userOne;
	}

	public void setUserOne(String userOne) {
		this.userOne = userOne;
	}

	public Long getUserTwoId() {
		return this.userTwoId;
	}

	public void setUserTwoId(Long userTwoId) {
		this.userTwoId = userTwoId;
	}

	public String getUserTwo() {
		return this.userTwo;
	}

	public void setUserTwo(String userTwo) {
		this.userTwo = userTwo;
	}

	public Long getIncId() {
		return this.incId;
	}

	public void setIncId(Long incId) {
		this.incId = incId;
	}

	public String getIncName() {
		return this.incName;
	}

	public void setIncName(String incName) {
		this.incName = incName;
	}

	public String getRequestFlag() {
		return this.requestFlag;
	}

	public void setRequestFlag(String requestFlag) {
		this.requestFlag = requestFlag;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getUtime() {
		return this.utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValidate() {
		return this.validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

}
