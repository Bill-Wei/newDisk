package swun.iot.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TDirectories entity. @author MyEclipse Persistence Tools
 */

public class TDirectories implements java.io.Serializable {

	// Fields

	private Integer id;
	private String user;
	private String path;
	private String parantPath;
	private String dir;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TDirectories() {
	}

	/** full constructor */
	public TDirectories(Integer id, String user, String path,
			String parantPath, String dir, Date createTime) {
		this.id = id;
		this.user = user;
		this.path = path;
		this.parantPath = parantPath;
		this.dir = dir;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParantPath() {
		return this.parantPath;
	}

	public void setParantPath(String parantPath) {
		this.parantPath = parantPath;
	}

	public String getDir() {
		return this.dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}