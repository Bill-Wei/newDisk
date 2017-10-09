package swun.iot.common;

import java.io.File;

public class UserInfo {
	
	private String cookieUser;
	private String root;
	private String userRoot;
	private String dir;
	private String parentPath;
	private String time;
	public String getCookieUser() {
		return cookieUser;
	}
	public void setCookieUser(String cookieUser) {
		this.cookieUser = cookieUser;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getUserRoot() {
		return userRoot;
	}
	public void setUserRoot(String userRoot) {
		this.userRoot = userRoot;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
//	返回path路径对应于网络硬盘根目录的本地路径
	public String getAbsolutePath(String path){
		String absolutePath = userRoot+
				(File.separator.equals("\\")?path.replaceAll("/", "\\\\"):path);
		return absolutePath;
	}
	
}
