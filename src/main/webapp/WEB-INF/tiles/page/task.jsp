<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <link href="<c:url value='/assets/css/cerabox.css'/>" rel="stylesheet"  media="screen" />
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
	<div class="col-md-1" ></div>
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
					<label for="state" class="col-sm-4 control-label">完工状态</label>
					<div class="col-sm-8">
						<select class="form-control" id="state" name="state">
							<option ></option>
							<option >已完工</option>
							<option>未完工</option>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<button class="btn btn-primary"
					onclick="searchTask(<%=request.getContextPath()%>/,'<%=request.getContextPath()%>/task/paginationTask')">查询</button>
				<button class="btn btn-primary"
					onclick="addTask(<%=request.getContextPath()%>/)">增加</button>
			</div>
			<br/>
	
		</form>
	<br />
	<div id="taskTable" class="table-responsive">
		<table class="table table-bordered table-hover table-striped">
			<caption>泰伦智能工作管理系统</caption>
			<thead>
				<tr>
					<th>任务单编号</th>
					<th>项目名称</th>
					<th>主要负责人</th>
					<th>查看派遣单照片</th>
					<th>完工状态</th>
					<th class="text-center"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div class="row" id="hidden"></div>
	<div id="Pagination" class="flickr"></div>
</div>
</div>
	<div class="col-md-1"></div>
</div>
<br />
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
						<c:if test="${success!=null||success!=''}">
				        	<c:out value="${success}"/>
				        </c:if>
				        <c:if test="${error!=null||error!=''}">
				        	<c:out value="${error}"/>
				        </c:if>
				<%-- <c:out value="${success}" /> --%>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	$("#taskTable").hide();
	getAllStaff();
	getAllItemName();
	var success = "${success}";
	if (success != "") {
		$("#myModal").modal();
		success = "";
	}
	var error="${error}";
	if(error!=""){
		$("#myModal").modal();
		/* setTimeout(function() {
			location.href = "task/addTask";
		},1500); */
	}
	
});
</script>
<!-- <script type="text/javascript">
	window.addEvent('domready', function(){
		$$('a.ceraBox').cerabox();
	});
</script> -->
