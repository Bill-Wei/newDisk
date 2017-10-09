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

public class DeleteAction extends BaseAction {
	//创建属性
	private String[] paths;
	

	public void setPaths(String paths) {
		this.paths = paths.split(";");
	}

	@Override
	public String execute() throws Exception {
		
		try {
			DirectoryService directoryService = serviceManager.getDirectoryService();
			FileService fileService = serviceManager.getFileService();
			for (String path : paths) {
				if (path!=null) {
					//删除目录
					if (path.charAt(path.length()-1)=='/') {
						directoryService.deleteDirectory(userInfo, path);
					}
					//删除文件
					else {
						fileService.deleteFile(userInfo, path);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
}
