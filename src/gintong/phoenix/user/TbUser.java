package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * TbUser generated by hbm2java
 */
public class TbUser implements java.io.Serializable {

	private Long id;
	private Long uid;
	private Integer test;
	private Integer status;
	private String activationCode;
	private String userName;
	private String password;
	private String salt;
	private String name;
	private Integer sex;
	private String email;
	private String mobile;
	private Boolean country;
	private String province;
	private String city;
	private String industry;
	private String companyName;
	private String companyJob;
	private String phone;
	private Date ctime;
	private String ipRegistered;
	private String remark;
	private Integer isOnline;
	private String picPath;
	private Date lastLogintime;
	private Long remainPoints;
	private Long remainMoney;
	private Boolean virtual;
	private Long corpId;
	private String level;
	private Integer isOneLogin;
	private Integer isFirstSet;
	private Integer isReceiveEmail;
	private Integer shieldStatus;
	private Integer type;
	private String nameIndex;
	private String nameIndexAll;
	private String nameFirst;
	private String regFrom;
	private String peopleId;
	private String county;
	private Integer recommendMark;
	private Boolean isSecretary;
	private String shortName;
	private String orgType;
	private Integer auth;
	private String industryIds;
	private String intro;
	private String isListing;
	private String stkcd;
	private String careIndustryIds;
	private String careIndustryNames;
	private Integer userStatus;
	private String mobileAreaCode;
	private String qqlogin;
	private String sinalogin;
	private String nemail;
	private String organNumber;
	private Integer organStatus;
	private Integer organType;

	public TbUser() {
	}

	public TbUser(Long uid, Integer test, Integer status, String activationCode, String userName, String password,
			String salt, String name, Integer sex, String email, String mobile, Boolean country, String province,
			String city, String industry, String companyName, String companyJob, String phone, Date ctime,
			String ipRegistered, String remark, Integer isOnline, String picPath, Date lastLogintime, Long remainPoints,
			Long remainMoney, Boolean virtual, Long corpId, String level, Integer isOneLogin, Integer isFirstSet,
			Integer isReceiveEmail, Integer shieldStatus, Integer type, String nameIndex, String nameIndexAll,
			String nameFirst, String regFrom, String peopleId, String county, Integer recommendMark,
			Boolean isSecretary, String shortName, String orgType, Integer auth, String industryIds, String intro,
			String isListing, String stkcd, String careIndustryIds, String careIndustryNames, Integer userStatus,
			String mobileAreaCode, String qqlogin, String sinalogin, String nemail, String organNumber,
			Integer organStatus, Integer organType) {
		this.uid = uid;
		this.test = test;
		this.status = status;
		this.activationCode = activationCode;
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.mobile = mobile;
		this.country = country;
		this.province = province;
		this.city = city;
		this.industry = industry;
		this.companyName = companyName;
		this.companyJob = companyJob;
		this.phone = phone;
		this.ctime = ctime;
		this.ipRegistered = ipRegistered;
		this.remark = remark;
		this.isOnline = isOnline;
		this.picPath = picPath;
		this.lastLogintime = lastLogintime;
		this.remainPoints = remainPoints;
		this.remainMoney = remainMoney;
		this.virtual = virtual;
		this.corpId = corpId;
		this.level = level;
		this.isOneLogin = isOneLogin;
		this.isFirstSet = isFirstSet;
		this.isReceiveEmail = isReceiveEmail;
		this.shieldStatus = shieldStatus;
		this.type = type;
		this.nameIndex = nameIndex;
		this.nameIndexAll = nameIndexAll;
		this.nameFirst = nameFirst;
		this.regFrom = regFrom;
		this.peopleId = peopleId;
		this.county = county;
		this.recommendMark = recommendMark;
		this.isSecretary = isSecretary;
		this.shortName = shortName;
		this.orgType = orgType;
		this.auth = auth;
		this.industryIds = industryIds;
		this.intro = intro;
		this.isListing = isListing;
		this.stkcd = stkcd;
		this.careIndustryIds = careIndustryIds;
		this.careIndustryNames = careIndustryNames;
		this.userStatus = userStatus;
		this.mobileAreaCode = mobileAreaCode;
		this.qqlogin = qqlogin;
		this.sinalogin = sinalogin;
		this.nemail = nemail;
		this.organNumber = organNumber;
		this.organStatus = organStatus;
		this.organType = organType;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getTest() {
		return this.test;
	}

	public void setTest(Integer test) {
		this.test = test;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getCountry() {
		return this.country;
	}

	public void setCountry(Boolean country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyJob() {
		return this.companyJob;
	}

	public void setCompanyJob(String companyJob) {
		this.companyJob = companyJob;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getIpRegistered() {
		return this.ipRegistered;
	}

	public void setIpRegistered(String ipRegistered) {
		this.ipRegistered = ipRegistered;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Date getLastLogintime() {
		return this.lastLogintime;
	}

	public void setLastLogintime(Date lastLogintime) {
		this.lastLogintime = lastLogintime;
	}

	public Long getRemainPoints() {
		return this.remainPoints;
	}

	public void setRemainPoints(Long remainPoints) {
		this.remainPoints = remainPoints;
	}

	public Long getRemainMoney() {
		return this.remainMoney;
	}

	public void setRemainMoney(Long remainMoney) {
		this.remainMoney = remainMoney;
	}

	public Boolean getVirtual() {
		return this.virtual;
	}

	public void setVirtual(Boolean virtual) {
		this.virtual = virtual;
	}

	public Long getCorpId() {
		return this.corpId;
	}

	public void setCorpId(Long corpId) {
		this.corpId = corpId;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getIsOneLogin() {
		return this.isOneLogin;
	}

	public void setIsOneLogin(Integer isOneLogin) {
		this.isOneLogin = isOneLogin;
	}

	public Integer getIsFirstSet() {
		return this.isFirstSet;
	}

	public void setIsFirstSet(Integer isFirstSet) {
		this.isFirstSet = isFirstSet;
	}

	public Integer getIsReceiveEmail() {
		return this.isReceiveEmail;
	}

	public void setIsReceiveEmail(Integer isReceiveEmail) {
		this.isReceiveEmail = isReceiveEmail;
	}

	public Integer getShieldStatus() {
		return this.shieldStatus;
	}

	public void setShieldStatus(Integer shieldStatus) {
		this.shieldStatus = shieldStatus;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getNameFirst() {
		return this.nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getRegFrom() {
		return this.regFrom;
	}

	public void setRegFrom(String regFrom) {
		this.regFrom = regFrom;
	}

	public String getPeopleId() {
		return this.peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Integer getRecommendMark() {
		return this.recommendMark;
	}

	public void setRecommendMark(Integer recommendMark) {
		this.recommendMark = recommendMark;
	}

	public Boolean getIsSecretary() {
		return this.isSecretary;
	}

	public void setIsSecretary(Boolean isSecretary) {
		this.isSecretary = isSecretary;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Integer getAuth() {
		return this.auth;
	}

	public void setAuth(Integer auth) {
		this.auth = auth;
	}

	public String getIndustryIds() {
		return this.industryIds;
	}

	public void setIndustryIds(String industryIds) {
		this.industryIds = industryIds;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getIsListing() {
		return this.isListing;
	}

	public void setIsListing(String isListing) {
		this.isListing = isListing;
	}

	public String getStkcd() {
		return this.stkcd;
	}

	public void setStkcd(String stkcd) {
		this.stkcd = stkcd;
	}

	public String getCareIndustryIds() {
		return this.careIndustryIds;
	}

	public void setCareIndustryIds(String careIndustryIds) {
		this.careIndustryIds = careIndustryIds;
	}

	public String getCareIndustryNames() {
		return this.careIndustryNames;
	}

	public void setCareIndustryNames(String careIndustryNames) {
		this.careIndustryNames = careIndustryNames;
	}

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getMobileAreaCode() {
		return this.mobileAreaCode;
	}

	public void setMobileAreaCode(String mobileAreaCode) {
		this.mobileAreaCode = mobileAreaCode;
	}

	public String getQqlogin() {
		return this.qqlogin;
	}

	public void setQqlogin(String qqlogin) {
		this.qqlogin = qqlogin;
	}

	public String getSinalogin() {
		return this.sinalogin;
	}

	public void setSinalogin(String sinalogin) {
		this.sinalogin = sinalogin;
	}

	public String getNemail() {
		return this.nemail;
	}

	public void setNemail(String nemail) {
		this.nemail = nemail;
	}

	public String getOrganNumber() {
		return this.organNumber;
	}

	public void setOrganNumber(String organNumber) {
		this.organNumber = organNumber;
	}

	public Integer getOrganStatus() {
		return this.organStatus;
	}

	public void setOrganStatus(Integer organStatus) {
		this.organStatus = organStatus;
	}

	public Integer getOrganType() {
		return this.organType;
	}

	public void setOrganType(Integer organType) {
		this.organType = organType;
	}

}