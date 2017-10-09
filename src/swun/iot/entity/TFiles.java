package swun.iot.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TFiles entity. @author MyEclipse Persistence Tools
 */

public class TFiles implements java.io.Serializable {

	// Fields

	private Integer id;
	private String user;
	private String file;
	private String path;
	private Long size;
	private Date uploadTime;
	private String time;//表示格式化uploadTime后的时间字符串

	// Constructors

	/** default constructor */
	public TFiles() {
	}

	/** full constructor */
	public TFiles(Integer id, String user, String file, String path, Long size,
			Date uploadTime) {
		this.id = id;
		this.user = user;
		this.file = file;
		this.path = path;
		this.size = size;
		this.uploadTime = uploadTime;
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

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getSize() {
		return this.size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(uploadTime);
	}
	

}