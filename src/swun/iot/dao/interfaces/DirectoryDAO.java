package swun.iot.dao.interfaces;

import java.util.List;

import swun.iot.common.UserInfo;
import swun.iot.entity.PDirInfo;
import swun.iot.entity.TDirectories;

public interface DirectoryDAO {
	
//	持久化directory对象
	public void save(TDirectories directory);
//	删除目录信息
	public void delete(UserInfo userInfo,String path);
//	返回指定用户和路径的所有子目录信息
	public List<PDirInfo> getDirInfo(String user,String parentPath);
}
