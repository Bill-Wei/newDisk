package swun.iot.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import swun.iot.common.UploadFile;

public class DownloadFileAction extends BaseAction {
	//创建属性
	private String name;
	private String path;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
			if (path != null && name != null) {
				//获得下载文件的本地路径
				String filename = userInfo.getUserRoot()+(File.separator.equals("\\")?
						path.replaceAll("/", "\\\\"):path)+name;
				//对下载文件名进行解码（针对非西欧字符）
				filename = URLDecoder.decode(filename,"UTF-8");
				File file = new File(filename);
				//如果文件存在 则开始下载
				if (file.exists()) {
					//设置下载文件所需的HTTP消息响应头
					response.setContentType("application/octet-stream");
					response.addHeader("Content-Disposition", "attachment;filename="+name);
					response.addHeader("Content-Length", String.valueOf(file.length()));
					
					//使用FileInputStream对象打开要下载的文件
					InputStream is = new FileInputStream(filename);
					OutputStream os = response.getOutputStream();
					byte[] buffer = new byte[8192];
					int count = 0;
					//开始向客户端输出下载文件的字节流（每次输出8k字节）
					while ((count = is.read(buffer))>0) {
						os.write(buffer,0,count);
					}
					os.close();
					is.close();
					
				}
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	
}
