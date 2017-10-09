package swun.iot.dao.interfaces;

import swun.iot.entity.TUsers;

public interface UserDAO {

//	持久化USer对象
	public void save(TUsers user);
//	判断指定用户是否存在
	public boolean exists(TUsers user);
//	返回指定用户经加密的密码字符串，如果用户不存在 则返回null
	public String getPasswordMD5(TUsers user);
}
