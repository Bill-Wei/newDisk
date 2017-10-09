package swun.iot.dao.interfaces;

import java.util.List;

import swun.iot.common.UserInfo;
import swun.iot.entity.TFiles;

public interface FileDAO {
//	持久化file对象
	public void save(TFiles file);
//	删除指定用户和路径中的所有文件记录
	public void deleteFiles(UserInfo userInfo,String path);
//	删除指定用户和文件的记录
	public void delete(UserInfo userInfo,String file);
//	返回指定用户已使用的网络硬盘大小
	public long getUserDiskSize(String userName);
//	返回指定用户和路径中所有文件信息（不包括子目录中的文件）
	public List<TFiles> getFiles(String userName,String path);
}
