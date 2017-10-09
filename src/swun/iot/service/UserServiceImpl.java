package swun.iot.service;

import swun.iot.dao.interfaces.UserDAO;
import swun.iot.entity.TUsers;
import swun.iot.service.interfaces.UserService;

public class UserServiceImpl implements UserService {

//	创建属性userDAO
	private UserDAO userDAO;
//	构造方法，需要通过Spring注入的方式传入UserDAO对象
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
//	实现注册方法
	@Override
	public void addUser(TUsers user) throws Exception {
//		如果用户已经存在，抛出异常
		if (userDAO.exists(user)) {
			throw new Exception("<"+user+">已经存在！");
		}
//		向数据库中添加一个用户
		else {
			userDAO.save(user);
		}
	}



	@Override
	public boolean verifyUser(TUsers user) {
//		获取指定用户经过加密后的密码字符串
		String passwordMD5 = userDAO.getPasswordMD5(user);
		boolean result = false;
		try {
			result = (user.getPasswordMd5().equals(passwordMD5))?true:false;
		} catch (Exception e) {
		}
		return result;
	}
	
}
