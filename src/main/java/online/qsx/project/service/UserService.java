package online.qsx.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.qsx.project.dao.UserDao;
import online.qsx.project.model.ChartData;
import online.qsx.project.model.Page;
import online.qsx.project.model.UserModel;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	/**
	 * 模糊查询，获取用户的信息并分页
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @return
	 */
	@Transactional
	public Page<UserModel> getUsers(int pageNo,int pageSize,String userName){
		return userDao.findUserPage(pageNo, pageSize, userName);
	}
	/**
	 * 得到总页数
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	@Transactional
	public long getTotalPages(int totalCount,int pageSize){
		return userDao.getTotalPages(totalCount, pageSize);
	}
	/**
	 * 根据userId得到用户
	 * @param userId
	 * @return
	 */
	@Transactional
	public UserModel getUser(Long userId){
		return userDao.getUser(userId);
	}
	/**
	 * 保存用户
	 * @param user
	 */
	@Transactional
	public void saveUser(UserModel user){
		userDao.saveUser(user);
	}
	/**
	 * 根据userId删除用户
	 * @param userId
	 */
	@Transactional
	public void deleteUser(Long userId){
		userDao.deleteUser(userId);
	}
	@Transactional
	public ChartData getUserGenderData(){
		//获取根据性别分组统计数据,得到的数据有两组，一组是男性的数据，一组是女性的数据
		List genderNumberList=userDao.getUserGenderNumbers();
		int genderNumberCount=genderNumberList.size();
		//建立两个数组
		String[] names=new String[genderNumberCount];
		String[] values=new String[genderNumberCount];
		//遍历统计数据，转换成 Chart.js 所需要的数据格式
		for(int i=0;i<genderNumberCount;i++){
			Object[] genderNumbers=(Object[]) genderNumberList.get(i);
			//性别标签
			names[i]="\""+String.valueOf(genderNumbers[1])+"\"";
			//性别对应的数量标签
			values[i]="\""+String.valueOf(genderNumbers[0])+"\"";
		}
		ChartData userGenderData=new ChartData();
		userGenderData.setName("["+String.join(",", names)+"]");
		userGenderData.setValue("["+String.join(",", values)+"]");
		return userGenderData;
	}
	/**
	 * 获取用户创建日期统计数据
	 * @return
	 */
	@Transactional
	public ChartData getUserCreateData() {
	    //获取根据创建日期分组统计数据
	    List createDateNumberList = userDao.getUserCreateDateNumbers();
	    int createDateNumberCount = createDateNumberList.size();
	    String[] names = new String[createDateNumberCount];
	    String[] values = new String[createDateNumberCount];

	    //遍历统计数据，转换成 Chart.js 所需要的数据格式
	    for (int i = 0, n = createDateNumberCount; i < n; i++) {
	        Object[] createDateNumbers = (Object[]) createDateNumberList.get(i);
	        //日期标签，如：2017-03-21
	        names[i] = "\"" + String.valueOf(createDateNumbers[1]) + "\"";
	        //日期对应的创建用户数量
	        values[i] = String.valueOf(createDateNumbers[0]);
	    }
	    ChartData userCreateDateData = new ChartData();
	    userCreateDateData.setName("[" + String.join(",", names) + "]");
	    userCreateDateData.setValue("[" + String.join(",", values) + "]");

	    return userCreateDateData;
	}
}
