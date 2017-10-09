package swun.iot.service;

import swun.iot.service.interfaces.DirectoryService;
import swun.iot.service.interfaces.FileService;
import swun.iot.service.interfaces.UserService;

public class ServiceManager {
//	封装三个Service类对象实例的属性
	private UserService userService;
	private DirectoryService directoryService;
	private FileService fileService;
	
//	getter和setter方法
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public DirectoryService getDirectoryService() {
		return directoryService;
	}
	public void setDirectoryService(DirectoryService directoryService) {
		this.directoryService = directoryService;
	}
	public FileService getFileService() {
		return fileService;
	}
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
	
}
