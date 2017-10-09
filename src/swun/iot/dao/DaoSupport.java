package swun.iot.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class DaoSupport {
//	创建属性template
	protected HibernateTemplate template;

//	构造方法，通过Spring注入的方式传入hibernateTemplate对象
	public DaoSupport(HibernateTemplate template) {
		this.template = template;
	}
	

}
