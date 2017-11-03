package swun.iot.action;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.hibernate.dialect.FirebirdDialect;

import swun.iot.entity.TUsers;
import swun.iot.service.interfaces.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends BaseAction implements ModelDriven<TUsers> {
	
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
		String validationCode = (obj!=null)?obj.toString():"";
		//判断用户输入的校验码是否正确
		if (!validationCode.equalsIgnoreCase(user.getValidateCode())) {
			//如果用户的验证码不正确，添加字段错误
			if (user.getValidateCode()!=null) {
				this.addFieldError("validateCode", "验证码输入错误");
			}
		}
	}
	
	//处理用户请求的execute方法
	public String execute()throws Exception{
		try{
			//通过serviceManager对象获得USerService对象
			UserService userService = serviceManager.getUserService();
			userService.addUser(user);
			File dir = new File(userInfo.getRoot()+user.getUser());
			if (!dir.exists()) {
				dir.mkdir();
			}
			result = "<"+user.getUser()+">注册成功!";
			return SUCCESS;
			
		}catch(Exception e){
			result = e.getMessage();
			e.printStackTrace();
			return ERROR;
		}
		
	}

}
