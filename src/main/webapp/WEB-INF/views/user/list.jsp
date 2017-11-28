<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />
<script src="${ctx }/plugins/jquery-3.2.1.min.js"></script>
</head>
<body>
	<s:include value="/common/header.jsp"></s:include>
	<div class="content">
		<s:include value="/common/left.jsp"></s:include>
		<div id="right" class="right">
			<div class="toolbar">
				<form action="userAction!listUser" method="post">
					<label for="name">用户名称:</label>
					<s:textfield id="name" name="userModel.name"></s:textfield>
					<button type="submit">查询</button>
				</form>
			</div>
			<table class="table">
				<thead>
					<tr>
						<td>编号</td>
						<td>用户名称</td>
						<td>邮箱</td>
						<td>性别</td>
						<td>创建时间</td>
						<td>更新时间</td>
						<td align="center">操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list.result }" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${item.name }</td>
							<td>${item.email }</td>
							<td><c:if test="${item.gender=='male' }">
								男
							</c:if> <c:if test="${item.gender=='famale' }">
								女
							</c:if></td>
							<td>${item.createTime }</td>
							<td>${item.updateTime }</td>
							<td align="center">
								<a href="userAction!to_edit?userModel.id=${item.id }">编辑</a>
								<a href="JavaScript:doDeleteUser('${item.id }','${item.name }');">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<ul id="pagination-demo" class="pagination-sm" style="float: right;"></ul>
			<p
				style="line-height: 70px; display: inline; padding-top: 0px; float: right;">共${totalPage}页,${list.totalCount }条记录</p>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/js/jquery.twbsPagination.js"></script>
	<script type="text/javascript" src="${ctx }/js/common.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#pagination-demo').twbsPagination({
	        totalPages: ${totalPage},
	        startPage: ${pageNo},
	        visiblePages: 4,
	        initiateStartPageClick:false,
	        onPageClick: function (event, page) {
	            $('#page-content').text('Page ' + page);
	            window.location.href="userAction!listUser?pageNo="+page;
	        }
	    });
	});
    //删除用户
    function doDeleteUser(userId, userName) {
        //提示是否确认删除
        var deleteConfirm = confirm("是否确认删除用户：" + userName + " ?");
        if (deleteConfirm == true) {
            document.location = "userAction!deleteUser?userId=" + userId;
            alert("用户删除成功！");
        }
    }
    
	</script>
</body>
</html>