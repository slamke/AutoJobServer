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

		<div class="row">
			<div class="col-md-2" style="text-align: center">
				<c:forEach var="info" items="${infoList}" varStatus="status">
					<button id="site-${status.index}" class="btn"
						onclick="carouselBtn(event);" data-slide-to="${status.index}"
						data-target="#worksite">${info.siteName}</button>
					<hr />
				</c:forEach>
			</div>
			<div class="col-md-10" style="text-align: center">
				<div id="worksite" class="carousel slide" data-ride="carousel">
					<!-- Wrapper for slides -->
					<div class="carousel-inner" id="outerItem">
						<c:forEach var="info" items="${infoList}" varStatus="status">
							<c:if test="${status.index==0}">
								<div class="item active">
							</c:if>
							<c:if test="${status.index!=0}">
								<div class="item">
							</c:if>
							<input type="hidden" class="itemNum" value="${status.index}" />
							<input type="hidden" class="sum" value="${status.count}" />
							<c:choose>
								<c:when test="${!empty info.taskerror}">
									<c:out value="${info.taskerror}"/>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${empty info.taskerror}">
							<c:choose>
								<c:when test="${!empty info.jobInfo}">
									<div class="row">
										<div class="form-group col-sm-6">
											<label for="" class="col-sm-4 control-label">任务负责人：</label>
											<div class="col-sm-8">
												<label class="control-label"><c:out
														value="${info.jobInfo.staff.realname}" /></label>
											</div>
										</div>
										<div class="form-group col-sm-6">
											<label for="" class="col-sm-4 control-label">工期：</label>
											<div class="col-sm-8">
												<label class="control-label"><c:out
														value="${info.siteTime}" /></label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-sm-6">
											<label for="" class="col-sm-4 control-label">施工情况：</label>
											<div class="col-sm-8">
												<label class="control-label"><c:out
														value="${info.jobInfo.brief}" /></label>
											</div>
										</div>
										<div class="form-group col-sm-6">
											<label for="" class="col-sm-4 control-label">已出差：</label>
											<div class="col-sm-8">
												<label class="control-label"><c:out
														value="${info.doDay}" /></label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-sm-6">
											<label for="" class="col-sm-4 control-label">改进意见：</label>
											<div class="col-sm-8">
												<label class="control-label"><c:out
														value="${info.jobInfo.improvement}" /></label>
											</div>
										</div>
										<div class="form-group col-sm-6">
											<label for="" class="col-sm-4 control-label">工作进展：</label>
											<div class="col-sm-8">
												<label class="control-label"><c:out
														value="${info.progress}" /></label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-sm-6">
											<label for="" class="col-sm-4 control-label">问题描述：</label>
											<div class="col-sm-8">
												<label class="control-label"><c:out
														value="${info.jobInfo.problem}" /></label>
											</div>
										</div>
									</div>

									<c:choose>
										<c:when test="${!empty info.jobInfo.infos}">
											<div class="row text-center">
												<div class="col-md-10 ">
													<div class="flexslider">
														<ul class="slides">
															<c:forEach var="photo" items="${info.jobInfo.infos}"
																varStatus="status">
																<li><img src="<c:url value="${photo.path}"/>" /></li>
															</c:forEach>
														</ul>
													</div>
												</div>
												<div class="col-md-1 "></div>
											</div>
										</c:when>
									</c:choose>
								</c:when>


							</c:choose>
							<c:choose>
								<c:when test="${empty info.jobInfo}">
									<div>
										<c:out value="${JobInfoError}" />
									</div>
								</c:when>
							</c:choose>
							</c:when>
							</c:choose>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="row" id="hiddenDiv"></div>
	</div>
</div>
<div class="col-md-1"></div>

</div>
<br />
<script>
	$(function() {
		$('.carousel').carousel({
			interval : 15000
		})
		/*内部轮播插件的资料在http://www.woothemes.com/flexslider/  相应参数也可以在这里看到*/
		$('.flexslider').flexslider({
			animation : "fade",
			directionNav : true,
			pauseOnHover : true, //鼠标悬停，则停止轮播
			pauseOnAction : false, //false表示开启自动播放
			slideshowSpeed : 1000, //速度
			before : function() {
			},
		});
		$('#worksite').on('slide.bs.carousel', function() {
			var sum = $('input.sum:last').val();
			var num1 = $(".item.active").find('input.itemNum').val();
			var num = (new Number(num1) + 1) % (new Number(sum));
			$("button.btn-primary").removeClass("btn-primary");
			$("#site-" + num).addClass('btn-primary');
		})
		getAllItemName();
		var selectDate;
		var myDate = new Date();
		var today = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-"
				+ myDate.getDate();
	})
	function validateSit() {
		var itemname = $("#itemname").val();
		if (itemname == "" || itemname == null) {
			$("#validateInfo").text("请填写项目名称");
			return false;
		} else {
			return true;
		}
	}
	function carouselBtn(event) {
		$('#worksite').carousel('pause');
		$("button").each(function() {
			$("button").removeClass("btn-primary")
		});
		//alert(index);
		//$(event.target).addClass("btn-primary");
	}
</script>