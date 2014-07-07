<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row">
	<div class="col-md-1" style="text-align: center"></div>
	<div class="col-md-10" style="text-align: center">
		<ul class="nav nav-tabs nav-justified">
			<li class="active"><a href="#condition" data-toggle="tab">情况概述</a></li>
			<li><a href="#opinion" data-toggle="tab">改进意见</a></li>
			<li><a href="#problem" data-toggle="tab">问题描述</a></li>
		</ul>
		<br />
		<div class="tab-content">
			<div class="tab-pane active" id="condition">
				<div class="row text-left" style="margin-left:30px" >
					简要描述：<c:out value="${jobInfo.brief}"></c:out> 
				</div>
				<br />
				<hr/>
				
				<div class="row">
					<c:forEach var="photoinfo" items="${photoInfoList}">
						<c:if test="${photoinfo.type.equals('情况简述')}">
							<c:if test="${photoinfo.subtype==0}">
								<p>开工前照片(拍摄时间：<c:out value="${photoinfo.time}"></c:out>)</p>
								<img src="<c:url value= '${photoinfo.path}'/>" />
							</c:if>
							
						</c:if>
					</c:forEach>
					<hr/>
					<hr/>
					<c:forEach var="photoinfo" items="${photoInfoList}">
						<c:if test="${photoinfo.type.equals('情况简述')}">
							
							<c:if test="${photoinfo.subtype==1}">
								<p>完工后照片(拍摄时间：<c:out value="${photoinfo.time}"></c:out>)</p>
								<img src="${photoinfo.path}" />
							</c:if>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="tab-pane " id="opinion">
				<div class="row text-left" style="margin-left:30px" >
					简要描述：<c:out value="${jobInfo.improvement}"/>
					<br />
				</div>
				<br />
				<hr/>
				<div class="row">
					<c:forEach var="photoinfo" items="${photoInfoList}">
						<c:if test="${photoinfo.type.equals('改进意见')}">
							<c:if test="${photoinfo.subtype==0}">
								<p>开工前照片(拍摄时间：<c:out value="${photoinfo.time}"></c:out>)</p>
								<img src="<c:url value= '${photoinfo.path}'/>" />
							</c:if>
							
						</c:if>
					</c:forEach>
					<hr/>
					<c:forEach var="photoinfo" items="${photoInfoList}">
						<c:if test="${photoinfo.type.equals('改进意见')}">
							
							<c:if test="${photoinfo.subtype==1}">
								<p>完工后照片(拍摄时间：<c:out value="${photoinfo.time}"></c:out>)</p>
								<img src="${photoinfo.path}" />
							</c:if>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="tab-pane " id="problem">
				<div class="row text-left" style="margin-left:30px" >
					简要描述：<c:out value="${jobInfo.problem}"></c:out>
					<br />
				</div>
				<br />
				<hr/>
				<hr/>
				<div class="row">
					<c:forEach var="photoinfo" items="${photoInfoList}">
						<c:if test="${photoinfo.type.equals('问题描述')}">
							<c:if test="${photoinfo.subtype==0}">
								<p>开工前照片(拍摄时间：<c:out value="${photoinfo.time}"></c:out>)</p>
								<img src="<c:url value= '${photoinfo.path}'/>" />
							</c:if>
							
						</c:if>
					</c:forEach>
					<hr/>
					<hr/>
					<c:forEach var="photoinfo" items="${photoInfoList}">
						<c:if test="${photoinfo.type.equals('问题描述')}">
							
							<c:if test="${photoinfo.subtype==1}">
								<p>完工后照片(拍摄时间：<c:out value="${photoinfo.time}"></c:out>)</p>
								<img src="${photoinfo.path}" />
							</c:if>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<br />
	</div>
	<div class="col-md-1"></div>
	<br />
</div>
