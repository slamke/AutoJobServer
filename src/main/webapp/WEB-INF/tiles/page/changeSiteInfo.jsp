<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<br />
<div class="row">
	<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		<div id="errMess" style="color:#f00"></div>
		<form class="form-horizontal" id="chgInfoForm" role="form"
			onsubmit="return false;">
			<div class="form-group">
				<label for="siteNum" class="col-sm-2 control-label">工地编号</label>
				<div class="col-sm-10">
					<input type="text" class="form-control required" id="siteNum11"
						name="siteNum" placeholder="工地编号">
				</div>
			</div>
			<div class="form-group">
				<label for="siteName" class="col-sm-2 control-label">项目工地简称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="siteName"
						name="siteName" placeholder="项目工地简称">
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label">详细说明</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="description" id="description"
						placeholder="详细说明"><c:out value="${description}"></c:out></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10 " style="text-align:left">
					<button type="submit" class="btn btn-primary btn-lg" onclick="validateChangeSiteInfo(<%=request.getContextPath()%>/)">保存</button>
				</div>
			</div>
			<input type="hidden" id="oldnum" />
		</form>
		
	</div>
	<div class="col-md-1"></div>
</div>
<br />
<script type="text/javascript">
$(function(){
	var num=${num} ;
	
	var sitename="${sitename}";
		$("#siteNum11").val(num);
		 $("#siteName").val(sitename);
		$("#oldnum").val(num); 
});

</script>

