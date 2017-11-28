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
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css" />
<script src="${ctx }/plugins/jquery-3.2.1.min.js"></script>
</head>
<body>
    <!-- 利用 struts 的 s:include 标签引入公用的 header.jsp 文件 -->
    <s:include value="/common/header.jsp" />
    <div class="content">
        <!-- 引入左侧菜单 left.jsp 文件 -->
        <s:include value="/common/left.jsp" />
        <div id="right" class="right">
            <div style="width: 300px; height: 300px; float: left;">
                <canvas id="userGenderChartCanvas" width="300px" height="300px;"></canvas>
            </div>
            <div style="width: 500px; height: 450px; float: left;">
                <canvas id="userCreateChartCanvas" width="500px" height="450px"></canvas>
            </div>
        </div>
    </div>
    <!-- 引入 jQuery 库 -->
    <script src="${ctx}/plugins/jquery-3.2.1.min.js"></script>
    <!-- 引入 Chart.js 库 -->
    <script src="${ctx}/js/Chart.min.js"></script>
    <!-- 引入自定义公用 js 库 -->
    <script src="${ctx}/js/common.js"></script>
    <script type="text/javascript">
    // 用户性别统计参数（饼状图）
    var userGenderChartConfig = {
        type: 'pie',
        data: {
            labels: ${userGenderData.name},
            datasets: [{
                data: ${userGenderData.value},
                backgroundColor: ["#FF6384", "#36A2EB"]
            }]
        },
        options: {
            title: {
                display: true,
                text: '用户性别分布统计'
            },
            responsive: true
        }
    };

    // 用户创建日期统计参数（折线图）
    var userCreateChartConfig = {
        type: 'line',
        data: {
            labels: ${userCreateData.name},
            datasets: [{
                label: "创建日期",
                fill: false,
                data: ${userCreateData.value}
            }]
        },
        options: {
            title: {
                display: true,
                text: '用户创建日期统计'
            },
            responsive: true
        }
    };

    $(function() {
        // 生成用户性别统计图
        var userGenderChartCtx = document.getElementById("userGenderChartCanvas").getContext("2d");
        var userGenderChartCanvas = new Chart(userGenderChartCtx, userGenderChartConfig);

        // 生成用户创建日期统计图            
        var userCreateChartCtx = document.getElementById("userCreateChartCanvas").getContext("2d");
        var userCreateChartCanvas = new Chart(userCreateChartCtx, userCreateChartConfig);
    });
    </script>
</body>
</html>