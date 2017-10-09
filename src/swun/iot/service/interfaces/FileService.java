package swun.iot.service.interfaces;

import java.util.List;

import swun.iot.common.UploadFile;
import swun.iot.common.UserInfo;
import swun.iot.entity.TFiles;

public interface FileService {
//	向数据库添加多个上传文件信息，并将上传文件保存在本地硬盘中
	public void addFiles(UploadFile uploadFile) throws Exception;
//	从数据库和本地硬盘删除指定文件
	public void deleteFile(UserInfo userInfo,String file);
//	返回指定用户已经使用的网络硬盘大小
	public long getUserDiskSize(String username);
//	返回指定用户和目录中的所有文件详细信息（不包括子目录中的文件）
	public List<TFiles> getFiles(String username,String path);
	
}
