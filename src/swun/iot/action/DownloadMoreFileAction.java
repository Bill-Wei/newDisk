package swun.iot.action;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

import swun.iot.common.Zip;



public class DownloadMoreFileAction extends BaseAction {
	//创建属性
	private String[] names;//封装多个要下载的文件名，目录和属性
	private String path;
	
	//客户端发送过来的多个文件和目录是以逗号分隔的字符串，因此，需要在setNames方法
	//中对其拆分
	public void setNames(String names) {
		this.names = names.split(";");
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
			if (path != null && names != null) {
				//设置下载文件需要的http响应头
				response.setContentType("application/octet-stream");
				if (path.equals("/")) {
					//如果当前路径是根目录，下载文件名为“网络硬盘根目录.zip”
					response.addHeader("Content-Disposition",
							"attachment;filename="+URLEncoder.encode("网络硬盘根目录.zip","UTF-8"));
				}else if (path.length()>0) {
					String[] array = path.split("/");
					//如果当前路径不是根目录，则使用当前目录名作为下载文件名
					if (array.length>1) {
						String zipName =  array[array.length-1]+".zip";
						response.addHeader("Content-Disposition", "attachment;filename="+zipName);
					}else {
						return null;
					}
				}else {
					return null;
				}
				//对要下载的文件和目录下载扫描，并处理每一个下载项
				for (int i = 0; i < names.length; i++) {
					String name = names[i];
					if (!name.equals("")) {
						//获得下载的本地路径
						String filename = userInfo.getUserRoot()+(File.separator.equals("\\")?
								path.replaceAll("/", "\\\\"):path)+name;
						//对本地路径解码
						filename = URLDecoder.decode(filename,"UTF-8");
						names[i] = filename;
					}
				}
				//将要下载的文件或目录压缩成zip文件
				Zip.compress(response.getOutputStream(), names);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
}
