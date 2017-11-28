<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<div id="left" class="left">
	<ul class="menu">
		<li><a href="userAction!listUser">用户查询</a></li>
		<li><a href="userAction!to_edit">用户新增</a></li>
		<li><a href="userAction!analyzeUser">用户分析</a></li>
	</ul>
	<div class="head-avatar">
		<img src="${ctx }/images/avatar.png">
		<h5>当前登录用户</h5>
	</div>
</div>
