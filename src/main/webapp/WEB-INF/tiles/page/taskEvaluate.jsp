<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<br />
<div class="row">
<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		<form class="form-horizontal" role="form" action="task/saveevaluate" method="post">
			<div class="row">
				<div class="form-group col-sm-4">
					<label for="tasknum" class="col-sm-5 control-label">任务单号:</label>
					<div class="col-sm-7" align="left">
						<label class="control-label" ><c:out value="${taskInfo.num}"/></label>
						<input id="info_id"	name="info_id" type="hidden" class="form-control"
							value="${taskInfo.id}">
					</div>
				</div>
				<div class="form-group col-sm-4" align="left">
					<label for="taskstaff" class="col-sm-6 control-label">任务负责人:</label>
					<div class="col-sm-6">
						<label class="control-label"><c:out value="${taskInfo.chargeStaff.realname}"/></label>
					</div>
				</div>
				<div class="form-group col-sm-4" align="left">
					<label for="itemname" class="col-sm-5 control-label">项目名称:</label>
					<div class="col-sm-7">
						<label class="control-label"><c:out value="${taskInfo.info.sitename}"/></label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label for="tasknum" class="col-sm-4 control-label">是否按时完成工作：</label>
					<div class="col-sm-8">
						<div class="radio col-md-3">
							<label><input type="radio" name="scorefinish"
								id="optionsRadios1" value="5" checked>5分</label>
						</div>
						<div class="radio col-md-3">
							<label><input type="radio" name="scorefinish"
								id="optionsRadios2" value="3">3分</label>
						</div>
						<div class="radio col-md-3">
							<label><input type="radio" name="scorefinish"
								id="optionsRadios3" value="0">0分</label>
						</div>
					</div>
				</div>
				<div class="form-group col-sm-12">
					<label for="taskstaff" class="col-sm-4 control-label">照片是否符合要求：</label>
					<div class="col-sm-8">
						<div class="radio  col-md-3">
							<label><input type="radio" name="scorephoto"
								id="optionsRadios4" value="5" checked>5分</label>
						</div>
						<div class="radio  col-md-3">
							<label><input type="radio" name="scorephoto"
								id="optionsRadios5" value="3">3分</label>
						</div>
						<div class="radio  col-md-3">
							<label><input type="radio" name="scorephoto"
								id="optionsRadios6" value="0">0分</label>
						</div>
					</div>
				</div>
				<div class="form-group col-sm-12">
					<label for="itemname" class="col-sm-4 control-label">工作总结是否符合要求：</label>
					<div class="col-sm-8">
						<div class="radio  col-md-3">
							<label><input type="radio" name="scoreconclusion"
								id="optionsRadios7" value="5" checked>5分</label>
						</div>
						<div class="radio  col-md-3">
							<label><input type="radio" name="scoreconclusion"
								id="optionsRadios8" value="3">3分</label>
						</div>
						<div class="radio  col-md-3">
							<label><input type="radio" name="scoreconclusion"
								id="optionsRadios9" value="0">0分</label>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="tasknum"
								 value="${taskInfo.num}" checked>
			<br />
			<button type="submit" class="btn btn-primary">保存</button>
			<button class="btn btn-primary" onclick="history.back()">返回</button>
		</form>

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
	var success = "${success}";
	if (success != "") {
		$("#myModal").modal();
		setTimeout(function() {
			location.href = "task/toevaluate";
		},1500);
	}
</script>
