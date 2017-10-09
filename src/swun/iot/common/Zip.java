package swun.iot.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.tools.zip.*;

public class Zip {
	//压缩指定目录，并通过zos参数返回
	private static void zipDirectory(ZipOutputStream zos,String dirName,
			String basePath) throws Exception{
		File dir = new File(dirName);
		if (dir.exists()) {
			//返回指定目录中的所有文件和目录
			File files[] = dir.listFiles();
			if (files.length>0) {
				//处理所有的上传文件
				for(File file:files){
					//如果是目录，递归压缩该目录
					if (file.isDirectory()) {
						zipDirectory(zos, file.getPath(), basePath+file.getName().substring(
								file.getName().lastIndexOf(File.separator)+1)+File.separator);
						//如果file是文件，将该文件加入到压缩输出流中
					}else {
						zipFile(zos,file.getPath(),basePath);
					}
					
				}
			}
			//添加空目录
			else {
				ZipEntry ze = new ZipEntry(basePath);
				zos.putNextEntry(ze);
			}
		}
	}
	//压缩指定文件
	private static void zipFile(ZipOutputStream zos, String filename,
			String basePath) throws Exception{
		File file = new File(filename);
		
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(filename);
			//建立ZipEntity对象来压缩文件，一个ZipEntity可以压缩一个文件
			ZipEntry ze = new ZipEntry(basePath+file.getName());
			
			zos.putNextEntry(ze);//将压缩文件添加到压缩包中
			
			byte[] buffer = new byte[8192];
			int count = 0;
			//开始压缩文件
			while ((count = fis.read(buffer))>0) {
				zos.write(buffer,0,count);//向压缩包中写入字节流
			}
			fis.close();
		}
		
	}
	
	
	public static void compress(OutputStream os,String... paths)throws Exception{
		//用压缩输出流封装os指定的输出流
		ZipOutputStream zos = new ZipOutputStream(os);
		//扫描所有的压缩路径
		for (String path:paths) {
			if (path.equals("")) {
				continue;
			}
			File file = new File(path);
			//如果改路径存在，开始压缩
			if (file.exists()) {
				//压缩目录
				if (file.isDirectory()) {
					zipDirectory(zos, file.getPath(), file.getName()+File.separator);
				}else {
					//压缩文件
					zipFile(zos, file.getPath(), "");
				}
			}
			zos.close();
		}
	}
	//压缩指定文件和目录（可以是多个文件和目录）
	public static void compress(String zipFilename,String... paths) throws Exception{
		compress(new FileOutputStream(zipFilename), paths);
	}
}
