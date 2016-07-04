package gintong.parasol.directory.test.now;
// Generated 2016-5-26 15:08:34 by Hibernate Tools 4.3.1.Final

/**
 * TbDirectorySource generated by hbm2java
 */
public class TbDirectorySource implements java.io.Serializable {

	private long id;
	private long directoryId;
	private long appId;
	private long userId;
	private Long sourceId;
	private int sourceType;
	private String sourceUrl;
	private String sourceTitle;
	private String sourceData;
	private String invokeMethod;
	private long createAt;

	public TbDirectorySource() {
	}

	public TbDirectorySource(long id, long directoryId, long appId, long userId, long sourceId, int sourceType) {
		this.id = id;
		this.directoryId = directoryId;
		this.appId = appId;
		this.userId = userId;
		this.sourceId = sourceId;
		this.sourceType = sourceType;
	}

	public TbDirectorySource(long id, long directoryId, long appId, long userId, Long sourceId, int sourceType,
			String sourceUrl, String sourceTitle, String sourceData, String invokeMethod, long createAt) {
		this.id = id;
		this.directoryId = directoryId;
		this.appId = appId;
		this.userId = userId;
		this.sourceId = sourceId;
		this.sourceType = sourceType;
		this.sourceUrl = sourceUrl;
		this.sourceTitle = sourceTitle;
		this.sourceData = sourceData;
		this.invokeMethod = invokeMethod;
		this.createAt = createAt;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDirectoryId() {
		return this.directoryId;
	}

	public void setDirectoryId(long directoryId) {
		this.directoryId = directoryId;
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

	public Long getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public int getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceTitle() {
		return this.sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	public String getSourceData() {
		return this.sourceData;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}

	public String getInvokeMethod() {
		return this.invokeMethod;
	}

	public void setInvokeMethod(String invokeMethod) {
		this.invokeMethod = invokeMethod;
	}

	public Long getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(long createAt) {
		this.createAt = createAt;
	}

}