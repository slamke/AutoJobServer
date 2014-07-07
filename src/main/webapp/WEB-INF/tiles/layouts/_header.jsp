<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="header-banner">
	<div id="name-and-slogan">
		<h1 id="site-name">
			<a href="/" title="Home" rel="home"><span><span
					class="glyphicon glyphicon-th-large"></span> 泰伦智能工作管理系统</span></a>
		</h1>
		<h2 id="site-slogan">
			泰伦智能工作管理系统<br>China / 2013
		</h2>
	</div>
</div>
<div id="top_nav" class="navbar navbar-inverse">
	<div class="navbar-header">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/home">首页</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" id="loginfo1">

			<li class=""><a href="<%=request.getContextPath()%>/workInfo">工作查询</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right" id="loginfo">
		</ul>

	</div>
	
</div>
<script type="text/javascript">
	var username=$.cookie("username");
	
	var admin=$.cookie("admin");
	var login=$.cookie("login");
	if(login=="true"){		
		$("#loginfo").append('<li id="usernameli" class="active"><a>'+username+'</a></li>');
		if(admin=="true"){
			$("#loginfo1").append('<li><a href="<%=request.getContextPath()%>/siteInfo">工地信息管理</a></li>');
			$("#loginfo1").append('<li id="infoli"><a href="<%=request.getContextPath()%>/adminUser">管理用户</a></li>');
			$("#loginfo1").append('<li><a href="<%=request.getContextPath()%>/changeInfo?username='+username+'">修改信息</a></li>');
		}else{
			$("#loginfo1").append('<li><a href="<%=request.getContextPath()%>/changeInfo?username='+username+'">修改信息</a></li>');
		}
		$("#loginfo1").append('<li><a href="<%=request.getContextPath()%>/task/home?username='+username+'">任务单管理</a></li>');
		$("#loginfo1").append('<li><a href="<%=request.getContextPath()%>/task/toevaluate?username='+username+'">评价指标</a></li>');
		$("#loginfo1").append('<li><a href="<%=request.getContextPath()%>/urgency/list/undone?username='+username+'">紧急申报</a></li>');
		$("#loginfo1").append('<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">现场<span class="caret"></span></a><ul class="dropdown-menu" role="menu"><li><a href="jobInfo/realSituation">现场情况</a></li><li><a href="jobInfo/searchRealSituation">情况查询</a></li></ul></li>');
		//$("#loginfo1").append('<li><a href="jobInfo/searchRealSituation">情况查询</a></li>');
		$("#loginfo").append('<li><a href="<%=request.getContextPath()%>/logout">退出</a></li>');
	}
</script>
