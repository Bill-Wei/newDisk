package swun.iot.service.interfaces;

import swun.iot.entity.TUsers;


public interface UserService {
//	向数据库中添加注册用户
	public void addUser(TUsers user)throws Exception;
//	校验登录用户是否合法
	public boolean verifyUser(TUsers user);
}
