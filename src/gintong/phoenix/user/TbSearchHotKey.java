package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbSearchHotKey generated by hbm2java
 */
public class TbSearchHotKey implements java.io.Serializable {

	private Integer id;
	private Integer levelFirstId;
	private String hotKey;

	public TbSearchHotKey() {
	}

	public TbSearchHotKey(Integer levelFirstId, String hotKey) {
		this.levelFirstId = levelFirstId;
		this.hotKey = hotKey;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevelFirstId() {
		return this.levelFirstId;
	}

	public void setLevelFirstId(Integer levelFirstId) {
		this.levelFirstId = levelFirstId;
	}

	public String getHotKey() {
		return this.hotKey;
	}

	public void setHotKey(String hotKey) {
		this.hotKey = hotKey;
	}

}