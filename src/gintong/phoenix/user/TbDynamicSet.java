package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbDynamicSet generated by hbm2java
 */
public class TbDynamicSet implements java.io.Serializable {

	private Long id;
	private Long userId;
	private Byte sendAllRequirement;
	private Byte sendAddRequirement;
	private Byte sendShareRequirement;
	private Byte sendAttentionRequirement;
	private Byte sendReplyRequirement;
	private Byte sendReplyAttentionRequirement;
	private Byte sendAllFriend;
	private Byte sendAddFriend;
	private Byte sendReferralsFriend;
	private Byte sendAllCor;
	private Byte sendAttentionCor;
	private Byte sendShareCor;
	private Byte sendAllDiary;
	private Byte sendAddDiary;
	private Byte sendReplyDiary;
	private Byte receiveAllRequirement;
	private Byte receiveAddRequirement;
	private Byte receiveShareRequirement;
	private Byte receiveAttentionRequirement;
	private Byte receiveReplyRequirement;
	private Byte receiveReplyAttentionRequirement;
	private Byte receiveAllFriend;
	private Byte receiveAddFriend;
	private Byte receiveReferralsFriend;
	private Byte receiveAllCor;
	private Byte receiveAttentionCor;
	private Byte receiveShareCor;
	private Byte receiveAllDiary;
	private Byte receiveAddDiary;
	private Byte receiveReplyDiary;

	public TbDynamicSet() {
	}

	public TbDynamicSet(Long userId, Byte sendAllRequirement, Byte sendAddRequirement, Byte sendShareRequirement,
			Byte sendAttentionRequirement, Byte sendReplyRequirement, Byte sendReplyAttentionRequirement,
			Byte sendAllFriend, Byte sendAddFriend, Byte sendReferralsFriend, Byte sendAllCor, Byte sendAttentionCor,
			Byte sendShareCor, Byte sendAllDiary, Byte sendAddDiary, Byte sendReplyDiary, Byte receiveAllRequirement,
			Byte receiveAddRequirement, Byte receiveShareRequirement, Byte receiveAttentionRequirement,
			Byte receiveReplyRequirement, Byte receiveReplyAttentionRequirement, Byte receiveAllFriend,
			Byte receiveAddFriend, Byte receiveReferralsFriend, Byte receiveAllCor, Byte receiveAttentionCor,
			Byte receiveShareCor, Byte receiveAllDiary, Byte receiveAddDiary, Byte receiveReplyDiary) {
		this.userId = userId;
		this.sendAllRequirement = sendAllRequirement;
		this.sendAddRequirement = sendAddRequirement;
		this.sendShareRequirement = sendShareRequirement;
		this.sendAttentionRequirement = sendAttentionRequirement;
		this.sendReplyRequirement = sendReplyRequirement;
		this.sendReplyAttentionRequirement = sendReplyAttentionRequirement;
		this.sendAllFriend = sendAllFriend;
		this.sendAddFriend = sendAddFriend;
		this.sendReferralsFriend = sendReferralsFriend;
		this.sendAllCor = sendAllCor;
		this.sendAttentionCor = sendAttentionCor;
		this.sendShareCor = sendShareCor;
		this.sendAllDiary = sendAllDiary;
		this.sendAddDiary = sendAddDiary;
		this.sendReplyDiary = sendReplyDiary;
		this.receiveAllRequirement = receiveAllRequirement;
		this.receiveAddRequirement = receiveAddRequirement;
		this.receiveShareRequirement = receiveShareRequirement;
		this.receiveAttentionRequirement = receiveAttentionRequirement;
		this.receiveReplyRequirement = receiveReplyRequirement;
		this.receiveReplyAttentionRequirement = receiveReplyAttentionRequirement;
		this.receiveAllFriend = receiveAllFriend;
		this.receiveAddFriend = receiveAddFriend;
		this.receiveReferralsFriend = receiveReferralsFriend;
		this.receiveAllCor = receiveAllCor;
		this.receiveAttentionCor = receiveAttentionCor;
		this.receiveShareCor = receiveShareCor;
		this.receiveAllDiary = receiveAllDiary;
		this.receiveAddDiary = receiveAddDiary;
		this.receiveReplyDiary = receiveReplyDiary;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Byte getSendAllRequirement() {
		return this.sendAllRequirement;
	}

	public void setSendAllRequirement(Byte sendAllRequirement) {
		this.sendAllRequirement = sendAllRequirement;
	}

	public Byte getSendAddRequirement() {
		return this.sendAddRequirement;
	}

	public void setSendAddRequirement(Byte sendAddRequirement) {
		this.sendAddRequirement = sendAddRequirement;
	}

	public Byte getSendShareRequirement() {
		return this.sendShareRequirement;
	}

	public void setSendShareRequirement(Byte sendShareRequirement) {
		this.sendShareRequirement = sendShareRequirement;
	}

	public Byte getSendAttentionRequirement() {
		return this.sendAttentionRequirement;
	}

	public void setSendAttentionRequirement(Byte sendAttentionRequirement) {
		this.sendAttentionRequirement = sendAttentionRequirement;
	}

	public Byte getSendReplyRequirement() {
		return this.sendReplyRequirement;
	}

	public void setSendReplyRequirement(Byte sendReplyRequirement) {
		this.sendReplyRequirement = sendReplyRequirement;
	}

	public Byte getSendReplyAttentionRequirement() {
		return this.sendReplyAttentionRequirement;
	}

	public void setSendReplyAttentionRequirement(Byte sendReplyAttentionRequirement) {
		this.sendReplyAttentionRequirement = sendReplyAttentionRequirement;
	}

	public Byte getSendAllFriend() {
		return this.sendAllFriend;
	}

	public void setSendAllFriend(Byte sendAllFriend) {
		this.sendAllFriend = sendAllFriend;
	}

	public Byte getSendAddFriend() {
		return this.sendAddFriend;
	}

	public void setSendAddFriend(Byte sendAddFriend) {
		this.sendAddFriend = sendAddFriend;
	}

	public Byte getSendReferralsFriend() {
		return this.sendReferralsFriend;
	}

	public void setSendReferralsFriend(Byte sendReferralsFriend) {
		this.sendReferralsFriend = sendReferralsFriend;
	}

	public Byte getSendAllCor() {
		return this.sendAllCor;
	}

	public void setSendAllCor(Byte sendAllCor) {
		this.sendAllCor = sendAllCor;
	}

	public Byte getSendAttentionCor() {
		return this.sendAttentionCor;
	}

	public void setSendAttentionCor(Byte sendAttentionCor) {
		this.sendAttentionCor = sendAttentionCor;
	}

	public Byte getSendShareCor() {
		return this.sendShareCor;
	}

	public void setSendShareCor(Byte sendShareCor) {
		this.sendShareCor = sendShareCor;
	}

	public Byte getSendAllDiary() {
		return this.sendAllDiary;
	}

	public void setSendAllDiary(Byte sendAllDiary) {
		this.sendAllDiary = sendAllDiary;
	}

	public Byte getSendAddDiary() {
		return this.sendAddDiary;
	}

	public void setSendAddDiary(Byte sendAddDiary) {
		this.sendAddDiary = sendAddDiary;
	}

	public Byte getSendReplyDiary() {
		return this.sendReplyDiary;
	}

	public void setSendReplyDiary(Byte sendReplyDiary) {
		this.sendReplyDiary = sendReplyDiary;
	}

	public Byte getReceiveAllRequirement() {
		return this.receiveAllRequirement;
	}

	public void setReceiveAllRequirement(Byte receiveAllRequirement) {
		this.receiveAllRequirement = receiveAllRequirement;
	}

	public Byte getReceiveAddRequirement() {
		return this.receiveAddRequirement;
	}

	public void setReceiveAddRequirement(Byte receiveAddRequirement) {
		this.receiveAddRequirement = receiveAddRequirement;
	}

	public Byte getReceiveShareRequirement() {
		return this.receiveShareRequirement;
	}

	public void setReceiveShareRequirement(Byte receiveShareRequirement) {
		this.receiveShareRequirement = receiveShareRequirement;
	}

	public Byte getReceiveAttentionRequirement() {
		return this.receiveAttentionRequirement;
	}

	public void setReceiveAttentionRequirement(Byte receiveAttentionRequirement) {
		this.receiveAttentionRequirement = receiveAttentionRequirement;
	}

	public Byte getReceiveReplyRequirement() {
		return this.receiveReplyRequirement;
	}

	public void setReceiveReplyRequirement(Byte receiveReplyRequirement) {
		this.receiveReplyRequirement = receiveReplyRequirement;
	}

	public Byte getReceiveReplyAttentionRequirement() {
		return this.receiveReplyAttentionRequirement;
	}

	public void setReceiveReplyAttentionRequirement(Byte receiveReplyAttentionRequirement) {
		this.receiveReplyAttentionRequirement = receiveReplyAttentionRequirement;
	}

	public Byte getReceiveAllFriend() {
		return this.receiveAllFriend;
	}

	public void setReceiveAllFriend(Byte receiveAllFriend) {
		this.receiveAllFriend = receiveAllFriend;
	}

	public Byte getReceiveAddFriend() {
		return this.receiveAddFriend;
	}

	public void setReceiveAddFriend(Byte receiveAddFriend) {
		this.receiveAddFriend = receiveAddFriend;
	}

	public Byte getReceiveReferralsFriend() {
		return this.receiveReferralsFriend;
	}

	public void setReceiveReferralsFriend(Byte receiveReferralsFriend) {
		this.receiveReferralsFriend = receiveReferralsFriend;
	}

	public Byte getReceiveAllCor() {
		return this.receiveAllCor;
	}

	public void setReceiveAllCor(Byte receiveAllCor) {
		this.receiveAllCor = receiveAllCor;
	}

	public Byte getReceiveAttentionCor() {
		return this.receiveAttentionCor;
	}

	public void setReceiveAttentionCor(Byte receiveAttentionCor) {
		this.receiveAttentionCor = receiveAttentionCor;
	}

	public Byte getReceiveShareCor() {
		return this.receiveShareCor;
	}

	public void setReceiveShareCor(Byte receiveShareCor) {
		this.receiveShareCor = receiveShareCor;
	}

	public Byte getReceiveAllDiary() {
		return this.receiveAllDiary;
	}

	public void setReceiveAllDiary(Byte receiveAllDiary) {
		this.receiveAllDiary = receiveAllDiary;
	}

	public Byte getReceiveAddDiary() {
		return this.receiveAddDiary;
	}

	public void setReceiveAddDiary(Byte receiveAddDiary) {
		this.receiveAddDiary = receiveAddDiary;
	}

	public Byte getReceiveReplyDiary() {
		return this.receiveReplyDiary;
	}

	public void setReceiveReplyDiary(Byte receiveReplyDiary) {
		this.receiveReplyDiary = receiveReplyDiary;
	}

}