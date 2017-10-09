package swun.iot.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import swun.iot.common.UploadFile;
import swun.iot.service.interfaces.DirectoryService;
import swun.iot.service.interfaces.FileService;

public class DiskAction extends BaseAction {
	//创建属性
	private long diskSize;
	

	public long getDiskSize() {
		return diskSize;
	}


	@Override
	public String execute() throws Exception {
		
		try {
			FileService fileService = serviceManager.getFileService();
			diskSize = fileService.getUserDiskSize(userInfo.getCookieUser());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
	
	
}
