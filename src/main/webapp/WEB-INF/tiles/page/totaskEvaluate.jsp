<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
function chgTask(index){
	//alert(index);
	location.href="<%=request.getContextPath()%>/task/2chgTaskInfo?tasknum="+$("#tasknum"+index).text();
}
function deleteTask(index){
	$.ajax({
		url:"<%=request.getContextPath()%>/task/deleteTask",
		type: "POST",
		data:{
			tasknum: $("#tasknum"+index).text(),
		},
		success:function(data){
			console.log(data);
			if(data.hasOwnProperty("error")){
				alert(data.error);
			}else{
				alert(data.success);
				setTimeout(function(){
					getTaskPage(0);
				}, "1000");
			}
		},
		error:function(){
			alert("通信错误");
		},
	});
}
</script>
<br />
<div class="row">
	<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		<form class="form-horizontal" role="form" onsubmit="return false;">
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="tasknum" class="col-sm-4 control-label">任务单号</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="tasknum"
							placeholder="任务单号">
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label for="taskstaff" class="col-sm-4 control-label">任务负责人</label>
					<div class="col-sm-8">
						<select class="form-control" id="taskstaff" name="taskstaff">
							<option></option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-6">
					<label for="itemname" class="col-sm-4 control-label">项目名称</label>
					<div class="col-sm-8">
						<select class="form-control" id="itemname" name="itemname">
							<option></option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-6">
					<div class="col-sm-4"></div>
					<div class="col-sm-8 text-left">
						<button class="btn btn-primary"
				onclick="searchTask2(<%=request.getContextPath()%>/)">查询</button>
					  </div>
				</div>
			</div>
			
		</form>
		<br />
		<div id="evTable" class="table-responsive">
			<table class="table table-bordered table-hover table-striped">
				<caption>泰伦智能工作管理系统</caption>
				<thead>
					<tr>
						<th>派遣单编号</th>
						<th>主要负责人</th>
						<th>所属部门</th>
						<th>项目名称</th>
						<th>评价</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="row" id="hidden"></div>
		<div id="Pagination" class="flickr"></div>
	</div>
	<div class="col-md-1"></div>
</div>
<br />
<script>
$(function(){
	$("#evTable").toggle();
	getAllStaff();
	getAllItemName();
});

function evalTask(index){
	var num=$("#tasknum"+index).text();
	location.href="<%=request.getContextPath()%>/task/evaluate?tasknum="+num;
}
</script>
