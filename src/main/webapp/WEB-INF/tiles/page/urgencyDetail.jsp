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
		<form class="form-horizontal" action="urgency/save" method="post" role="form">
			<div class="row">
				<div class="form-group col-sm-12">
					<label for="tasknum" class="col-sm-3 control-label">申报标题:</label>
					<div class="col-sm-5" align="left">
						<label class="control-label"><c:out value="${urgencyInfo.title}"/></label>
						<input id="info_id"	name="info_id" type="hidden" class="form-control"
							value="${urgencyInfo.id}">
					</div>
				<!-- </div>
				<div class="form-group col-sm-4" align="left"> -->
					<label class="col-sm-2 control-label">申报人:</label>
					<div class="col-sm-2">
						<label class="control-label"><c:out value="${urgencyInfo.staff.realname}"/></label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label class="col-sm-3 control-label">申报内容:</label>
					<div class="col-sm-9 " align="left" style="word-break:break-all">
						<span class="control-label"><c:out value="${urgencyInfo.content}"/></span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label class="col-sm-3 control-label">申报图片:</label>
					<div class="col-sm-9" align="left">
						<a href="<c:url value='${urgencyInfo.photo.path}'/>" class="ceraBox" title="${urgencyInfo.photo.description}">
				<img src="<c:url value='${urgencyInfo.photo.path}'/>" style="height: 150px; width: 150px" />
				</a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label class="col-sm-3 control-label">处理意见:</label>
					<div class="col-sm-9">
						<textarea class="form-control" rows="3" placeholder="处理意见"
						id="result" name="result"><c:out value="${urgencyInfo.result}"/></textarea>
					</div>
				</div>
			</div>
			<input type="hidden" value="${urgencyInfo.id}" name="id"/>
			<br />
			<div id="submit">
				<button id="submit" type="submit" class="btn btn-primary">保存</button>
			</div>
			
			<!-- <button class="btn btn-primary" onclick="history.back()">返回</button> -->
		</form>

	</div>
	<div class="col-md-1"></div>
</div>
<br />
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
      	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
         <c:out value="${success}"/>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
	$(function(){
		var status="${urgencyInfo.state}";
		if(status=="true"){
			$("#submit").show();
			$("#result").attr("disabled","disabled");
		}
		var success="${success}";
		if(success.length!=0){
			$("#myModal").modal();
			setTimeout(function(){
				location.href="urgency/list/undone";
			},1000);
		}
		
		 window.addEvent('domready', function(){
				$$('a.ceraBox').cerabox();
			});  
	});
</script>