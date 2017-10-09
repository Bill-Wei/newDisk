package swun.iot.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import swun.iot.common.UserInfo;
import swun.iot.dao.interfaces.FileDAO;
import swun.iot.entity.TFiles;

public class FileDAOImpl extends DaoSupport implements FileDAO {

	public FileDAOImpl(HibernateTemplate template) {
		super(template);
	}

	@Override
	public void save(TFiles file) {
		template.save(file);

	}

	@Override
	public void deleteFiles(UserInfo userInfo, String path) {
//		执行hql语句，删除 相应的记录
		template.bulkUpdate("delete from TFiles where user=? and path=?",
				new Object[]{userInfo.getCookieUser(),path});

	}
//	删除文件
	@Override
	public void delete(UserInfo userInfo, String file) {
		template.bulkUpdate("delete from TFiles where user = ? and concat(path,file)=?",//concat("aa","bb") 拼接字符串 “aabb”
				new Object[]{userInfo.getCookieUser(),file});

	}

	@Override
	public long getUserDiskSize(String userName) {
		List<Long> fileSize = template.find("select sum(size) from TFiles where user=?",userName);
		if (fileSize.size()>0) {
			return fileSize.get(0);
		}
		return 0;
	}

	@Override
	public List<TFiles> getFiles(String userName, String path) {
		return template.findByNamedParam("from TFiles where user = :user and path = :order by uploadTime",
				new String[]{"user","path"}, new Object[]{userName,path});
	}

}
