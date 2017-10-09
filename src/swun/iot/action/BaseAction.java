package swun.iot.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import swun.iot.common.UserInfo;
import swun.iot.service.ServiceManager;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	
//	创建各种属性
//	封装ServletManager对象的属性
	protected ServiceManager serviceManager;
	protected UserInfo userInfo;
	protected String result;
	protected Map<String, String> cookies;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
//	实现setServletResponse方法，获得HttpServletResponse对象
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
//	实现setServletRequest方法，获得HttpServletRequest对象
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
//		为UserInfo对象的cookieUser和userRoot属性赋值
		userInfo.setCookieUser(getCookieValue("user"));
		userInfo.setRoot(userInfo.getRoot()+userInfo.getCookieUser());
		
	}
//	返回一个指定的Cookie值
	protected String getCookieValue(String name){
		Cookie cookies[] = request.getCookies();
		if (cookies!=null) {
//			扫描请求消息头的所有Cookie，以找到指定的Cookie
			for(Cookie cookie:cookies){
				if (!cookie.getName().equals(name))
					continue;
					//找到指定的cookie 返回Cookie值
					return cookie.getValue();
			}
		}
		//如果未找到指定的Cookie，则返回null.
		return null;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
//	通过Spring注入的方式来获得ServiceManager对象和UserInfo对象
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
//	保存Cookie
	protected void saveCookie(String name,String value,int maxAge){
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	
}
