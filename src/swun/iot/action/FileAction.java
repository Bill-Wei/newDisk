package swun.iot.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import swun.iot.common.UploadFile;
import swun.iot.entity.TFiles;
import swun.iot.service.interfaces.DirectoryService;
import swun.iot.service.interfaces.FileService;

public class FileAction extends BaseAction {
	//创建属性
	private List<TFiles> files;
	private String path;
	


	public List<TFiles> getFiles() {
		return files;
	}



	public void setFiles(List<TFiles> files) {
		this.files = files;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	@Override
	public String execute() throws Exception {
		
		try {
			FileService fileService = serviceManager.getFileService();
			files = fileService.getFiles(userInfo.getCookieUser(), path);
			return SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
	
	
}
