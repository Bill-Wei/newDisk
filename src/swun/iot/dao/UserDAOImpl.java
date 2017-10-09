package swun.iot.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import swun.iot.dao.interfaces.UserDAO;
import swun.iot.entity.TUsers;

public class UserDAOImpl extends DaoSupport implements UserDAO {

//	构造方法，通过DAOSupport类的构造方法传入一个HibernateTemplate对象
	public UserDAOImpl(HibernateTemplate template) {
		super(template);
	}

	@Override
	public void save(TUsers user) {
		template.save(user);
	}

	@Override
	public boolean exists(TUsers user) {
//		根据是否成功返回密码来判定用户是否存在
		return (getPasswordMD5(user)!=null)?true:false;

	}

	@Override
	public String getPasswordMD5(TUsers user) {
//		定义hql
		String hql = "select passwordMD5 from TUser where user = ?";
//		使用find方法执行hql语句，并查找指定用户
		List<String> passwordMD5 = template.find(hql,user.getUser());
//		如果存在 则返回经过加密的密码字符串
		if (passwordMD5.size()>0) {
			return passwordMD5.get(0);
		}
		return null;
	}
}
