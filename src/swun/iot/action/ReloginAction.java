package swun.iot.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;

import swun.iot.common.UploadFile;
import swun.iot.entity.TFiles;
import swun.iot.service.interfaces.DirectoryService;
import swun.iot.service.interfaces.FileService;

public class ReloginAction extends BaseAction {

	@Override
	public String execute() throws Exception {
		
		try {
			Cookie cookie = new Cookie("JSESSIONID", "");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);

			return SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
}
