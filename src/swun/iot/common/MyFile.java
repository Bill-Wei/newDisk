package swun.iot.common;

import java.io.File;

public class MyFile {
	public static void deleteAny(String path){
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for(File myFile:files){
					if (myFile.isDirectory()) {
						deleteAny(myFile.getPath());//递归
					}else {
						myFile.delete();
					}
				}
			}
		}
	}
}
