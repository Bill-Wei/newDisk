package swun.iot.action;

import swun.iot.common.UserInfo;
import swun.iot.service.interfaces.DirectoryService;

import com.opensymphony.xwork2.ModelDriven;

public class CreateDirAction extends BaseAction implements ModelDriven<UserInfo> {

	@Override
	public UserInfo getModel() {
		return userInfo;
	}

	@Override
	public String execute() throws Exception {
		try {
			//通过serviceManager类的getDirectoryService
			//方法获得DirectoryService对象
			DirectoryService directoryService = serviceManager.getDirectoryService();
			//添加目录信息 ，并返回处理结果
			setResult(directoryService.addDirectory(userInfo));
			return SUCCESS;
		} catch (Exception e) {
			setResult("建立目录失败");
		}
		return ERROR;
	}
	
	

}
