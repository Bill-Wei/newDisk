package swun.iot.dao;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import swun.iot.common.UserInfo;
import swun.iot.dao.interfaces.DirectoryDAO;
import swun.iot.entity.PDirInfo;
import swun.iot.entity.TDirectories;

public class DirectoryDAOImpl extends DaoSupport implements DirectoryDAO {

	public DirectoryDAOImpl(HibernateTemplate template) {
		super(template);
	}

	@Override
	public void save(TDirectories directory) {
		template.save(directory);
	}

	@Override
	public void delete(UserInfo userInfo, String path) {
//		删除t_directories表中指定用户和记录
		template.bulkUpdate("delete from TDirectories where user = ? and"
				+ "path = ?",new Object[]{userInfo.getCookieUser(),path});
//		删除t_directories表中的指定用户和路径的所有子目录的记录
		template.bulkUpdate("delete from TDirectories where user = ? and"
				+ "parentPath liake ?",new Object[]{userInfo.getCookieUser(),path+"%"});
	}
	
	@Override
	public List<PDirInfo> getDirInfo(String user, String parentPath) {
//		通过调用p_dir_info获得指定用户和路径中的所有子目录的信息
		List<PDirInfo> directories = template.findByNamedQueryAndNamedParam
				("myDirInfo", new String[]{"user","parentPath"},new Object[]{user,parentPath});
		return directories;
	}

}
