package gintong.parasol.metadata.vo;
// Generated 2016-6-2 19:11:41 by Hibernate Tools 4.3.1.Final

/**
 * TbTagSource generated by hbm2java
 */
public class TbTagSource implements java.io.Serializable {

	private long id;
	private long tagId;
	private long appId;
	private long userId;
	private long sourceId;
	private Long sourceType;
	private Long createAt;

	public TbTagSource() {
	}

	public TbTagSource(long id, long tagId, long appId, long userId, long sourceId) {
		this.id = id;
		this.tagId = tagId;
		this.appId = appId;
		this.userId = userId;
		this.sourceId = sourceId;
	}

	public TbTagSource(long id, long tagId, long appId, long userId, long sourceId, Long sourceType, Long createAt) {
		this.id = id;
		this.tagId = tagId;
		this.appId = appId;
		this.userId = userId;
		this.sourceId = sourceId;
		this.sourceType = sourceType;
		this.createAt = createAt;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTagId() {
		return this.tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
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

	public long getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(Long sourceType) {
		this.sourceType = sourceType;
	}

	public Long getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Long createAt) {
		this.createAt = createAt;
	}

}
