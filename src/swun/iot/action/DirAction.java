package swun.iot.action;

import java.util.List;

import swun.iot.common.UserInfo;
import swun.iot.entity.PDirInfo;
import swun.iot.service.interfaces.DirectoryService;

import com.opensymphony.xwork2.ModelDriven;

public class DirAction extends BaseAction implements ModelDriven<UserInfo> {

	private List<PDirInfo> dirInfo;
	
	
	public List<PDirInfo> getDirInfo() {
		return dirInfo;
	}

	public void setDirInfo(List<PDirInfo> dirInfo) {
		this.dirInfo = dirInfo;
	}

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
			dirInfo = directoryService.getDirInfo(userInfo.getCookieUser(), userInfo.getParentPath());
			
			return SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
	
	

}
