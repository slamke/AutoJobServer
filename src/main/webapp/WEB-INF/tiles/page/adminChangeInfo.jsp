<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<div class="row">

	<div class="col-md-3"></div>
	<div class="col-md-6">
		<ul class="nav nav-tabs">
			<li><a href="#info" data-toggle="tab">修改基本信息</a></li>
			<li><a href="#password" data-toggle="tab">修改密码</a></li>

		</ul>
		<br/>
		<!-- Tab panes -->
		<form class="form-horizontal" role="form" id="chgform" onsubmit="return false;">
			<div class="tab-content">
				
				<div class="tab-pane active" id="info">
					<div class="form-group">
						<label for="username" class="col-sm-3 control-label">用户名</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="用户名" value="${staff.username}">
						</div>
					</div>
					<div class="form-group">
						<label for="realname" class="col-sm-3 control-label">真实姓名</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="realname"
								id="realname" placeholder="真实姓名" value="${staff.realname}">
						</div>
					</div>
					<div class="form-group">
						<label for="telephone" class="col-sm-3 control-label">电话</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="telephone"
								id="telephone" placeholder="电话" value="${staff.tel}">
						</div>
					</div>
					<div class="form-group">
						<label for="department" class="col-sm-3 control-label">部门</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="department"
								id="department" placeholder="部门" value="${staff.department}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-3">
							<button class="btn btn-primary"
								onclick="adminSavechange(<%=request.getContextPath()%>/,'${staff.username}')">保存</button>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="password">
					<div class="form-group">
						<label for="newcode" class="col-sm-3 control-label">新密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" name="newcode"
								id="newcode" placeholder="新密码">
						</div>
					</div>
					<div class="form-group">
						<label for="newcode2" class="col-sm-3 control-label">确认密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" name="newcode2"
								id="newcode2" placeholder="确认密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-3">
							<button class="btn btn-primary"
								onclick="adminChangePsw(<%=request.getContextPath()%>/,'${staff.username}')">保存</button>
						</div>
					</div>
				</div>

			</div>

		</form>
	</div>
	<div class="col-md-3"></div>
</div>
