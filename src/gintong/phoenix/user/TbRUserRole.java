package gintong.phoenix.user;
// Generated 2016-7-7 15:43:20 by Hibernate Tools 5.1.0.Beta1

/**
 * TbRUserRole generated by hbm2java
 */
public class TbRUserRole implements java.io.Serializable {

	private Long id;
	private Long userId;
	private Long roleId;

	public TbRUserRole() {
	}

	public TbRUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
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

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
