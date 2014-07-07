<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <script type="text/javascript">
$(function(){
	<%-- var error="${error}";
	if(error!=""||error!="null"){
		alert(error);
	}
	var success="${success}";
	if(success!=""||success!=null){
		alert(success);
		setTimeout(function(){
			location.href="<%=request.getContextPath()%>/task/home";
		}, "500");
	} --%>
	var tasknum="${taskInfo.tasknum}";
	var taskstaff="${taskInfo.chgstaff}";
	var itemname="${taskInfo.itemname}";
	var file="${taskInfo.photo}";
	$("#tasknum").val(tasknum);
	$("#taskstaff").val(taskstaff);
	$("#itemname").val(itemname);
    //$("#file").val(file);
    //alert(file);
	getAllStaff();
	getAllItemName();
});
function validateTask() {
	var taskNum = $("#tasknum").val().trim();
	var taskstaff = $("#taskstaff").val().trim();
	var itemname = $("#itemname").val().trim();
	var file = $("#uploadfile").val().trim();
	if (taskNum == "" || taskNum == null) {
		return false;
	}
	if (taskstaff == "" || taskstaff == null) {
		return false;
	}
	if (itemname == "" || itemname == null) {
		return false;
	}
	if (file == "" || file == null) {
		return false;
	}
	return true;
}
 </script>
<br />
<div class="row">
	<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		<div class="btn-group">
  
</div>
		<form class="form-horizontal" role="form" action="task/chgTaskInfo" method="post" enctype="multipart/form-data" onsubmit="return validateTask()">
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="tasknum" class="col-sm-4 control-label">任务单号</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="tasknum"
							name="tasknum" placeholder="任务单号">
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
					<label for="file" class="col-sm-4 control-label">上传照片</label>
					<div class="col-sm-8">
						<input type="file" name="file" class="form-control"
							id="uploadfile" placeholder="上传照片">
					</div>
				</div>
			</div>
			<input type="submit" class="btn btn-primary" />
			<!-- <button class="btn btn-primary" onclick="history.back()">返回</button> -->
		</form>
		<br/>
		
	</div>
	<div class="col-md-1"></div>
</div>
<br/>
