package online.qsx.project.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import online.qsx.project.model.ChartData;
import online.qsx.project.model.Page;
import online.qsx.project.model.UserModel;
import online.qsx.project.service.UserService;

@Component("userAction")
public class UserAction {
	// 默认每页显示的记录数
	private static final int DEFAULT_PAGE_SIZE = 5;
	@Autowired
	private UserService userService;
	// 用户分页数据
	private Page<UserModel> list;
	// 总页数
	private int totalPage;
	// 页号
	private int pageNo = 1;
	// 接受form表单传送过来的数据
	private UserModel userModel = new UserModel();
	// 删除时使用
	private long userId;
	//用户统计
	private ChartData userGenderData;
	private ChartData userCreateData;

	public String listUser() {
		if (userModel.getName() != null) {
			// 模糊查询
			list = userService.getUsers(pageNo, DEFAULT_PAGE_SIZE, userModel.getName());
			totalPage = (int) userService.getTotalPages(list.getTotalCount(), DEFAULT_PAGE_SIZE);
		} else {
			// 初始化遍历
			list = userService.getUsers(pageNo, DEFAULT_PAGE_SIZE, null);
			totalPage = (int) userService.getTotalPages(list.getTotalCount(), DEFAULT_PAGE_SIZE);
		}
		return "success";
	}

	// 删除用户
	public String deleteUser() {
		userService.deleteUser(userId);
		return "delete";
	}

	// 去新增页面
	public String to_edit() {
		return "edit";
	}

	// 编辑用户
	public String save() {
		Long id = userModel.getId();
		if (id != null) {
			UserModel user = new UserModel(userModel.getId(), userModel.getName(), userModel.getGender(),
					userModel.getPassword(), userModel.getPasswordAgain(), userModel.getEmail(), new Date());
			userService.saveUser(user);
		} else {
			userService.saveUser(userModel);
		}
		return "save";
	}
	//用户统计分析
	public String analyzeUser() {
		userGenderData = userService.getUserGenderData();
		userCreateData = userService.getUserCreateData();
		return "chart";
	}

	public Page<UserModel> getList() {
		return list;
	}

	public void setList(Page<UserModel> list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public ChartData getUserGenderData() {
		return userGenderData;
	}

	public void setUserGenderData(ChartData userGenderData) {
		this.userGenderData = userGenderData;
	}

	public ChartData getUserCreateData() {
		return userCreateData;
	}

	public void setUserCreateData(ChartData userCreateData) {
		this.userCreateData = userCreateData;
	}

}
