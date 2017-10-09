package swun.iot.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import swun.iot.common.MyFile;
import swun.iot.common.UserInfo;
import swun.iot.dao.interfaces.DirectoryDAO;
import swun.iot.dao.interfaces.FileDAO;
import swun.iot.entity.PDirInfo;
import swun.iot.entity.TDirectories;
import swun.iot.service.interfaces.DirectoryService;

public class DirectoryServiceImpl implements DirectoryService {

//	创建属性directoryDAO和fileDAO
	private DirectoryDAO directoryDAO;
	private FileDAO fileDAO;
	
	public DirectoryServiceImpl(DirectoryDAO directoryDAO, FileDAO fileDAO) {
		this.directoryDAO = directoryDAO;
		this.fileDAO = fileDAO;
	}

//	实现添加目录信息方法
	@Override
	public String addDirectory(UserInfo userInfo) throws Exception {
//		获得当前路径的本地目录
		String currentPath = userInfo.getUserRoot()+userInfo.getParentPath()
				+userInfo.getDir()+File.separator;
		currentPath = File.separator.equals("\\")?currentPath
				.replaceAll("/", "\\\\"):currentPath;
//		创建Directory对象，并初始化其属性
		TDirectories directory = new TDirectories();
		directory.setUser(userInfo.getCookieUser());
		directory.setDir(userInfo.getDir());
		directory.setParantPath(userInfo.getParentPath());
		directory.setPath(userInfo.getParentPath()+userInfo.getDir()+"/");
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		设置userinfo对象的time属性值
		userInfo.setTime(format.format(date));
		directory.setCreateTime(date);
		directoryDAO.save(directory);
		File dir = new File(currentPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		return "成功建立目录";
	}

//	删除目录信息
	@Override
	public void deleteDirectory(UserInfo userInfo, String path) {
//		删除t_directories表中相关目录信息
		directoryDAO.delete(userInfo, path);
//		删除t_files表中在指定目录下的文件信息
		fileDAO.delete(userInfo, path);
//		删除本地硬盘中的相应文件和目录信息
		MyFile.deleteAny(userInfo.getAbsolutePath(path));

	}

	@Override
	public List<PDirInfo> getDirInfo(String user, String parentPath) {
		return directoryDAO.getDirInfo(user, parentPath);
	}

}
