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
		<h3>下班填单详细信息</h3>
		<hr />
		<label class="col-sm-6">项目工地简称：${siteName}</label> 
		<label class="col-sm-6">用户名：${userName}</label> 
		<label class="col-sm-6">记录类型：${type}</label> 
		<label class="col-sm-6">填报日期：${date}</label>
		<hr />
		<div class="table-responsive">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th style="text-align:center">项目</th>
						<th style="text-align:center">文字说明</th>
						<th style="text-align:center">照片</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>情况简述</td>
						<td style="width:27em;"><p><span class="truncate_style">${brief}</span></p></td>
						<td>
							<div class="row">
								<c:choose>
								<c:when test="${!empty briefList}">
									<c:forEach var="photo" items="${briefList}" varStatus="status">
										<c:if test="${(status.index%2)==0}">
											</div>
											<p> </p>
											<div class="row">
										</c:if>
										<div class="col-md-6">
											<a href="<c:url value="${photo.path}"/>" class="ceraBox" title="${photo.description}"> 
											<img src="<c:url value="${photo.path}"/>" style="height: 100px; width: 150px" />
											</a>
										</div>
									</c:forEach>
								</c:when>
								</c:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td>改进意见</td>
						<td style="width:27em"><span class="truncate_style">${improvement}</span></td>
						<td>
							<div class="row">
								<c:choose>
								<c:when test="${!empty improveList}">
									<c:forEach var="photo" items="${improveList}" varStatus="status">
										<c:if test="${(status.index%2)==0}">
											</div>
											<p> </p>
											<div class="row">
										</c:if>
										<div class="col-md-6">
											<a href="<c:url value="${photo.path}"/>" class="ceraBox"
												title="${photo.description}"> <img
												src="<c:url value="${photo.path}"/>"
												style="height: 100px; width: 150px" />
											</a>
										</div>
									</c:forEach>
								</c:when>
								</c:choose>
							</div>
						</td>
					</tr>
					<tr>
						<td>问题描述</td>
						<td style="width:27em"><span class="truncate_style">${problem}</span></td>
						<td>
							<div class="row">
								<c:choose>
								<c:when test="${!empty problemList}">
									<c:forEach var="photo" items="${problemList}" varStatus="status">
										<c:if test="${(status.index%2)==0}">
											</div>
											<p> </p>
											<div class="row">
										</c:if>
										<div class="col-md-6">
											<a href="<c:url value="${photo.path}"/>" class="ceraBox"
												title="${photo.description}"> <img
												src="<c:url value="${photo.path}"/>"
												style="height: 100px; width: 150px" />
											</a>
										</div>
									</c:forEach>
								</c:when>
								</c:choose>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-1"></div>
</div>
<br />

<script type="text/javascript">  
    $(document).ready(function(){  
        $('.truncate_style').truncate({  
            width: 'auto',  
            after: '…',  
            center: false,  
            addclass: 'highlight',  
            addtitle: true  
        });  
    });  
    
    window.addEvent('domready', function(){
		$$(' a.ceraBox').cerabox();
	});
</script> 

