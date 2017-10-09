package swun.iot.service.interfaces;

import java.util.List;

import swun.iot.common.UserInfo;
import swun.iot.entity.PDirInfo;

public interface DirectoryService {
//	向数据库中添加目录信息
	public String  addDirectory(UserInfo userInfo) throws Exception;
//	删除目录信息(包括数据库中的记录和本地硬盘中的文件）
	public void deleteDirectory(UserInfo userInfo,String path);
//	返回指定用户和路径的所有子目录信息
	public List<PDirInfo> getDirInfo(String user,String parentPath);
}
