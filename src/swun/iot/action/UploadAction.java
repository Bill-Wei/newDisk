package swun.iot.action;


import swun.iot.common.UploadFile;
import swun.iot.service.interfaces.FileService;

import com.opensymphony.xwork2.ModelDriven;

public class UploadAction extends BaseAction implements ModelDriven<UploadFile> {
	
//	创建用户对象user
	private UploadFile uploadFile = new UploadFile();

//	实现getModel（）方法，以返回模型类的对象实例
	@Override
	public UploadFile getModel() {
		return uploadFile;
	}

	//处理用户请求的execute方法
	public String execute() throws Exception{
		try{
			//设置上传文件过程中要使用的UserInfo对象
			uploadFile.setUserInfo(userInfo);
			//通过serviceManager对象获得FileService对象
			FileService fileService = serviceManager.getFileService();
				fileService.addFiles(uploadFile);
				return SUCCESS;
		}catch(Exception e){
			
		}
		return ERROR;
	}

}
