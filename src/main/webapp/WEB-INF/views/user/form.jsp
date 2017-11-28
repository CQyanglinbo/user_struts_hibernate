<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<script src="${ctx }/plugins/jquery-3.2.1.min.js"></script>
<script src="${ctx }/js/common.js"></script>
</head>
<body>
	<s:include value="/common/header.jsp"></s:include>
	<div class="content">
		<s:include value="/common/left.jsp"></s:include>
		<div id="right" class="right">
			<h4 class="form-title">
				<s:if test="%{userModel.id==null}">
					新增用户信息
				</s:if>
				<s:else>
					编辑用户信息
				</s:else>
			</h4>
			<s:form id="userForm" action="userAction!save" theme="simple">
				<!--隐藏字段(userId)编辑用户时使用  -->
				<s:hidden name="userModel.id" />
				<!-- 打印重复提交错误信息 -->
				<s:actionerror />
				<!--重复提交控制  -->
				<s:token></s:token>
				<div class="input-group">
					<label class="input-label">用户名称:</label>
					<s:textfield id="username" name="userModel.name" class="input"
						placeholder="请输入您的用户名！" required="true" />
				</div>
				<div class="input-group">
					<label class="input-label">登录密码:</label>
					<s:textfield id="pwd" name="userModel.password" class="input"
						placeholder="请输入您的密码" required="true" />
				</div>
				<div class="input-group">
					<label class="input-label">重复密码:</label>
					<s:textfield id="pwd1" name="userModel.repeatPassword" class="input"
						placeholder="请输入您的确认密码！" required="true" />
				</div>
				<div class="input-group">
					<label class="input-label">电子邮箱:</label>
					<s:textfield name="userModel.email" class="input"
						placeholder="请输入您的邮箱" required="true" />
				</div>
				<div class="input-group">
					<label class="radio-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
					<div class="radio-group">
						<s:radio label="性别" list="#{'male':'男','famale':'女' }"
							name="userModel.gender" />
					</div>
				</div>

				<div class="form-control clr">
					<hr>
					<button type="submit" id="saveUserBtn" onclick="return click1()">保存</button>
					<button type="reset">重置</button>
					<button type="button" id="returnUserBtn">返回</button>
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>