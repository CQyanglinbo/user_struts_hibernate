package online.qsx.project.dao;

import java.util.ArrayList;
import java.util.List;




import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import online.qsx.project.model.Page;
import online.qsx.project.model.UserModel;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * 模糊查询并分页
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @return
	 */
	public Page<UserModel> findUserPage(int pageNo,int pageSize,String userName){
		List<UserModel> result=new ArrayList<UserModel>();
		//hibernate 的查询条件
		Session session=getCurrentSession();
		
		@SuppressWarnings("deprecation")
		Criteria criteria=session.createCriteria(UserModel.class);
		Page<UserModel> page=new Page<UserModel>();
		//如果用户名不为空，则根据用户名称进行模糊查询
		if(userName!=null){
			criteria.add(Restrictions.like("name", "%"+userName+"%"));
		}
		//分类下的数据总数，用于计算有多少页数
		int totalCount=criteria.list().size();
		//设置分页查询条件
		criteria.setFirstResult((pageNo-1)*pageSize);
		criteria.setMaxResults(pageSize);
		//查询结果
		result=criteria.list();
		
		page.setResult(result);
		page.setTotalCount(totalCount);
		
		return page;
	}
	/**
	 * 根据pageSize和totalCount计算总页数，默认值为-1
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	public long getTotalPages(int totalCount,int pageSize){
		if(totalCount<0){
			return -1;
		}
		long count=totalCount/pageSize;
		if(totalCount%pageSize>0){
			count++;
		}
		return count;
	}
	/**
	 * 根据UserId ,获取指定用户
	 * @param userId
	 * @return
	 */
	public UserModel getUser(Long userId){
		UserModel user=getCurrentSession().get(UserModel.class, userId);
		return user;
	}
	/**
	 * 保存用户
	 * @param contact
	 */
	public void saveUser(UserModel contact){
		Session session=getCurrentSession();
		//存在则更新，不存在则新增
		session.saveOrUpdate(contact);
		//同步更新到数据库
		session.flush();
	}
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deleteUser(Long userId){
		Session session=getCurrentSession();
		//删除前先得到要删除的user
		session.delete(getUser(userId));
		//同步更新到数据库
		session.flush();
	}
	/**
	  * 获取根据性别分组统计数据
	  * @return
	  */
	 public List getUserGenderNumbers() {
	     //根据 gender 分组统计用户性别人数
	     String hql = "select count(*) as number,gender from UserModel group by gender";
	     Query query = getCurrentSession().createQuery(hql);
	     return query.list();
	 }

	 /**
	  * 获取根据创建日期分组统计数据
	  * @return
	  */
	 public List getUserCreateDateNumbers() {
	     //根据 createTime 分组统计用户创建日期人数（ 利用 MySQL 提供的 DATE_FORMAT 函数对 createTime 进行格式化）
	     String hql = "select count(*) as number,DATE_FORMAT(createTime,'%Y-%m-%d') from UserModel group by DATE_FORMAT(createTime,'%Y-%m-%d')";
	     Query query = getCurrentSession().createQuery(hql);
	     return query.list();
	 }
	
	private Session getCurrentSession(){
		//获取当前线程中的Session,如果不存在，则创建一个Session并绑定到当前线程中
		return sessionFactory.getCurrentSession();
	}
}
