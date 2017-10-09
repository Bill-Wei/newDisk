package swun.iot.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.xml.sax.InputSource;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import swun.iot.common.MyFile;
import swun.iot.common.UploadFile;
import swun.iot.common.UserInfo;
import swun.iot.dao.interfaces.FileDAO;
import swun.iot.entity.TFiles;
import swun.iot.service.interfaces.FileService;

public class FileServiceImpl implements FileService{

//	创建属性fileDAO
	private FileDAO fileDAO;
	
//	构造方法，需要用过Spring注入的方式传入FileDAO对象
	public FileServiceImpl(FileDAO fileDAO) {
	this.fileDAO = fileDAO;
}
//	将一个上传文件保存在本地硬盘上
	private String saveFile(File uploadFile,String fn) throws Exception{
		File file = new File(fn);
		int index = 0;
		String filename = file.getName();
//		检测文件名是否重复，如果有重复，则修改上传文件的文件名
		while(file.exists()){
			int extIndex = filename.lastIndexOf(".");
			//判断上传文件是否有扩展名
			if (extIndex>0) {
				fn = filename.substring(0,extIndex)+"("+String.valueOf(index)+")"+
						filename.substring(extIndex);
			}else {
				fn = filename+"("+String.valueOf(index)+")";
			}
//			产生新的文件名
			fn = file.getPath().substring(0,file.getPath().lastIndexOf(file.getName()))+fn;
			file = new File(fn);
			index++;
		}
//		通过outputStream将文件写到本地硬盘上
		FileOutputStream fos = new FileOutputStream(fn);
		InputStream is = new FileInputStream(uploadFile);
		byte[] buffer = new byte[8192];
		int count = 0;
		while ((count = is.read(buffer))>0) {
			fos.write(buffer, 0, count);
		}
		fos.close();
		is.close();
//		返回最终上传文件名
		return file.getName();
	}
	
//	实现addFiles方法
	@Override
	public void addFiles(UploadFile uploadFile) throws Exception {
		int i = 0;
//		扫描上传的文件，并逐个处理这些文件
		for(File f:uploadFile.getUpload()){
			String currentPath = uploadFile.getUserInfo().getUserRoot()
					+(File.separator.equals("\\")?uploadFile.getUploadPath()
							.replaceAll("/", "\\\\"):uploadFile.getUploadPath());
//			将上传文件保存到本地硬盘
			String fn = saveFile(f, currentPath+uploadFile.getUploadFileName().get(i));
//			建立TFiles对象
			TFiles file = new TFiles();
//			为TFiles对象的属性赋值
			file.setUser(uploadFile.getUserInfo().getCookieUser());
			file.setFile(new File(fn).getName());
			file.setPath(uploadFile.getUploadPath());
			file.setSize(f.length());
			file.setUploadTime(new Date());
			fileDAO.save(file);
			i++;
		}
		
	}


//	删除文件
	@Override
	public void deleteFile(UserInfo userInfo, String file) {
//		删除数据库中的相关文件记录
		fileDAO.delete(userInfo, file);
//		删除本地硬盘中的文件
		MyFile.deleteAny(userInfo.getAbsolutePath(file));
	}
	
//		实现getUSerDiskSize方法
	@Override
	public long getUserDiskSize(String username) {
		
		return fileDAO.getUserDiskSize(username);
	}

//	实现getFile方法
	@Override
	public List<TFiles> getFiles(String username, String path) {
		
		return fileDAO.getFiles(username, path);
	}

}
