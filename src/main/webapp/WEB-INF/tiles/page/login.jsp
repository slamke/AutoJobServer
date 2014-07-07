<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<br />
<div class="row">
	<div class="col-md-3" style="text-align: center">
		<!-- <img src="assets/img/talent-logo.png" style="width: 80%; height: 60%"> -->
	</div>
	<div class="col-md-6" style="text-align: center">
		<h1>登录</h1>
		<p id="errmess" style="color: #red"></p>
	</div>
	<div class="col-md-3"></div>

</div>
<br />
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<form id="loginForm" class="form-horizontal" role="form" method="post"
			onsubmit="return false;" style="text-align: center">
			<div class="form-group">
				<label for="username" class="col-sm-3 control-label">用户名： </label>
				<div class="col-sm-6">
					<input type="text" id="username" name="username"
						class="form-control" placeholder="用户名">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">密码： </label>
				<div class="col-sm-6">
					<input type="password" id="password" name="password"
						class="form-control" placeholder="密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-10" style="text-align: left">
					<button id="login_button" class="btn btn-lg btn-primary">登录</button>
					<button type="reset" class="btn btn-lg btn-primary">取消</button>
				</div>
			</div>
		</form>

	</div>
	<div class="col-md-3"></div>
</div>
<script>
$(function(){
	$('#login_button').click(function(){
		mlogin(<%=request.getContextPath()%>/);
	});
	$('#top_nav').hide();
	
});
</script>