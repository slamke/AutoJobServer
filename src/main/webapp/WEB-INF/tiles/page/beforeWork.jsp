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
		<h3>上班签到详细信息</h3>
		<hr />
		<div class="row">
			<div class="col-md-4">
			<a href="<c:url value='${photoBeforeWork}'/>" class="ceraBox" title="${photoBeforeWorkDes}">
				<img src="<c:url value='${photoBeforeWork}'/>" style="height: 150px; width: 150px" />
			</a>
			</div>
			<div class="col-md-8">
				<label class="col-sm-12 text-left">项目工地简称：${siteName} </label> 
				<label class="col-sm-12 text-left">用户名：${userName}</label>
				<label class="col-sm-12 text-left">记录类型：${type}</label> 
				<label class="col-sm-12 text-left">填报日期：${date}</label>
			</div>
		</div>
			<hr/>
			<br/>
			
			<p>开工前照片</p>
			<div class="row">
			<c:choose>
			<c:when test="${!empty photoPathList}">
				<c:forEach var="photo" items="${photoPathList}" varStatus="status">
					<c:if test="${(status.index%4)==0}">
						</div>
						<hr/>
						<div class="row">
					</c:if>
					<div class="col-md-3">
						<a href="<c:url value="${photo.path}"/>" class="ceraBox" title="${photo.description}"> 
							<img src="<c:url value="${photo.path}"/>" style="height: 200px; width: 200px" />
						</a>
					</div>
				</c:forEach>
			</c:when>
			</c:choose>
			</div>
	</div>
	<div class="col-md-1"></div>
</div>
<br />
<script type="text/javascript">
	window.addEvent('domready', function(){
		$$('a.ceraBox').cerabox();
	});
</script>

