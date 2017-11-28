package online.qsx.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="t_user")

public class UserModel {
	//实体主键(自增长)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	//性别(枚举)
	public enum Gender{
		male,famale;
	}
	//用户名(不为空)
	@Column(length=100,nullable=false)
	private String name;
	//用户性别(不为空)
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private Gender gender=Gender.male;
	//用户登录密码(不为空)
	@Column(length=100,nullable=false)
	private String password;
	//再次输入登录密码
	@Transient //不作为数据库持久化字段
	private String passwordAgain;
	//邮箱
	@Column(length=200,nullable=true)
	private String email;
	
	//创建时间 (updatable=false 表示编辑后，不跟新此字段)
	@Column(nullable=true,length=19,updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss",iso=ISO.DATE)
	private Date createTime=new Date(System.currentTimeMillis());
	
	//最后更新时间 
	@Column(nullable=true,length=19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss",iso=ISO.DATE)
	private Date updateTime=new Date(System.currentTimeMillis());
	
	
	public UserModel() {
		super();
	}

	public UserModel(Long id, String name, Gender gender, String password, String passwordAgain, String email,
			Date updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.passwordAgain = passwordAgain;
		this.email = email;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
