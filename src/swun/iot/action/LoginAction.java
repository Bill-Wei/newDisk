package swun.iot.action;

import javax.servlet.http.HttpSession;

import swun.iot.entity.TUsers;
import swun.iot.service.interfaces.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends BaseAction implements ModelDriven<TUsers> {
	
//	创建用户对象user
	private TUsers user = new TUsers();

//	实现getModel（）方法，以返回模型类的对象实例
	@Override
	public TUsers getModel() {
		return user;
	}

//	校验用户提交的验证码
	@Override
	public void validate() {
//		如果用户提交的验证码为空，则直接返回
		if ("".equals(user.getValidateCode())) {
			return;
		}
		//从Session中获得服务端生成的验证码
		Object obj = ActionContext.getContext().getSession().get("validation_code");
		System.out.println(obj.toString());
		String validationCode = (obj!=null)?obj.toString():"";
		//判断用户输入的校验码是否正确
		if (!validationCode.equalsIgnoreCase(user.getValidateCode())) {
			//如果用户的验证码不正确，添加字段错误
			if (user.getValidateCode()!=null) {
				this.addFieldError("validateCode", "验证码输入错误");
			}
		}
	}
	@Override
	//处理用户请求的execute方法
	public String execute()throws Exception{
		try{
			//通过serviceManager对象获得USerService对象
			UserService userService = serviceManager.getUserService();
			//校验登录用户是否合法
			if (userService.verifyUser(user)) {
				//将用户名保存在Cookie中24小时
				saveCookie("user", user.getUser(), 24*60*60);
				HttpSession session = request.getSession();
				//将用户名以key为username保存在session中，一遍下次访问时不需要再次登录
				session.setAttribute("uername", user.getUser());
				//设置session的有效时间
				session.setMaxInactiveInterval(60*60*3);
				return SUCCESS;
			}
		}catch(Exception e){
			
		}
		return ERROR;
	}

}
