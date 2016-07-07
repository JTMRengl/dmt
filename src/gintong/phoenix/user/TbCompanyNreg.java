package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbCompanyNreg generated by hbm2java
 */
public class TbCompanyNreg implements java.io.Serializable {

	private Long id;
	private String key;
	private String name;
	private String corporationName;
	private String nregNo;
	private String regSteate;
	private String corporationEnterpriseType;
	private String address;
	private String paidupCapital;
	private String annualVerification;
	private String establishmentTime;
	private String approvalTime;
	private String legalRepresentative;
	private String registeredCapital;
	private String operatingPeriod;
	private String businessRangement;

	public TbCompanyNreg() {
	}

	public TbCompanyNreg(String key, String name, String corporationName, String nregNo, String regSteate,
			String corporationEnterpriseType, String address, String paidupCapital, String annualVerification,
			String establishmentTime, String approvalTime, String legalRepresentative, String registeredCapital,
			String operatingPeriod, String businessRangement) {
		this.key = key;
		this.name = name;
		this.corporationName = corporationName;
		this.nregNo = nregNo;
		this.regSteate = regSteate;
		this.corporationEnterpriseType = corporationEnterpriseType;
		this.address = address;
		this.paidupCapital = paidupCapital;
		this.annualVerification = annualVerification;
		this.establishmentTime = establishmentTime;
		this.approvalTime = approvalTime;
		this.legalRepresentative = legalRepresentative;
		this.registeredCapital = registeredCapital;
		this.operatingPeriod = operatingPeriod;
		this.businessRangement = businessRangement;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorporationName() {
		return this.corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getNregNo() {
		return this.nregNo;
	}

	public void setNregNo(String nregNo) {
		this.nregNo = nregNo;
	}

	public String getRegSteate() {
		return this.regSteate;
	}

	public void setRegSteate(String regSteate) {
		this.regSteate = regSteate;
	}

	public String getCorporationEnterpriseType() {
		return this.corporationEnterpriseType;
	}

	public void setCorporationEnterpriseType(String corporationEnterpriseType) {
		this.corporationEnterpriseType = corporationEnterpriseType;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaidupCapital() {
		return this.paidupCapital;
	}

	public void setPaidupCapital(String paidupCapital) {
		this.paidupCapital = paidupCapital;
	}

	public String getAnnualVerification() {
		return this.annualVerification;
	}

	public void setAnnualVerification(String annualVerification) {
		this.annualVerification = annualVerification;
	}

	public String getEstablishmentTime() {
		return this.establishmentTime;
	}

	public void setEstablishmentTime(String establishmentTime) {
		this.establishmentTime = establishmentTime;
	}

	public String getApprovalTime() {
		return this.approvalTime;
	}

	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getLegalRepresentative() {
		return this.legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getOperatingPeriod() {
		return this.operatingPeriod;
	}

	public void setOperatingPeriod(String operatingPeriod) {
		this.operatingPeriod = operatingPeriod;
	}

	public String getBusinessRangement() {
		return this.businessRangement;
	}

	public void setBusinessRangement(String businessRangement) {
		this.businessRangement = businessRangement;
	}

}