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
		<form class="form-horizontal" role="form" action="jobInfo/searchSituation" method="post" onsubmit="return validateSit()">
			<div class="row">
				<div class="form-group col-sm-5">
					<label for="itemname" class="col-sm-4 control-label">项目名称</label>
					<div class="col-sm-8">
						<select class="form-control" id="itemname" name="itemname">
							<option></option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-5">
					<label for="date" class="col-sm-4 control-label">日期</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="date" name="date" placeholder="">
					</div>
				</div>
				<div class="form-group col-sm-2">
					<button class="btn btn-primary" onclick="search()">查询</button>
				</div>
			</div>
			<p class="text-center" id="validateInfo"></p>
			<br />
		</form>
		<c:choose>
			<c:when test="${!empty jobInfo}">
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="" class="col-sm-4 control-label">任务负责人：</label>
						<div class="col-sm-8">
							<label class="control-label"><c:out value="${jobInfo.staff.realname}" /></label>
						</div>
					</div>
					<div class="form-group col-sm-6">
						<label for="" class="col-sm-4 control-label">工期：</label>
						<div class="col-sm-8">
							<label class="control-label"><c:out value="${siteTime}" /></label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="" class="col-sm-4 control-label">施工情况：</label>
						<div class="col-sm-8">
							<label class="control-label"><c:out value="${jobInfo.brief}" /></label>
						</div>
					</div>
					<div class="form-group col-sm-6">
						<label for="" class="col-sm-4 control-label">已出差：</label>
						<div class="col-sm-8">
							<label class="control-label"><c:out value="${doDay}" /></label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="" class="col-sm-4 control-label">改进意见：</label>
						<div class="col-sm-8">
							<label class="control-label"><c:out value="${jobInfo.improvement}" /></label>
						</div>
					</div>
					<div class="form-group col-sm-6">
						<label for="" class="col-sm-4 control-label">工作进展：</label>
						<div class="col-sm-8">
							<label class="control-label"><c:out value="${progress}" /></label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="" class="col-sm-4 control-label">问题描述：</label>
						<div class="col-sm-8">
							<label class="control-label"><c:out value="${jobInfo.problem}" /></label>
						</div>
					</div>
				</div>

				<c:choose>
					<c:when test="${!empty jobInfo.infos}">
						<div class="row text-center">
							<%-- <div class="col-md-3 "></div>
							<div class="col-md-6 ">
							<div id="carousel-example-generic" class="carousel slide"
								data-ride="carousel">
								<!-- Indicators -->
								<ol class="carousel-indicators">
									<c:forEach var="photo" items="${jobInfo.infos}"
										varStatus="status">
										<li data-target="#carousel-example-generic"
											data-slide-to="${status.index}" ></li>
									</c:forEach>
								</ol>
								<div class="col-md-12">
									<li data-target="#carousel-example-generic"
										data-slide-to="${status.index}" class="active"></li> <a
										href="<c:url value="${photo.path}"/>" class="ceraBox"
										title="${photo.description}"> </a>
								</div>
								<!-- Wrapper for slides -->

								<div class="carousel-inner">
									<c:forEach var="photo" items="${jobInfo.infos}"
										varStatus="status">
										<c:if test="${status.index==0}">
											<div class="item active">
											<img src="<c:url value="${photo.path}"/>" height="600px" width="600px"/>
											</div>
										</c:if>
										<div class="item ">
											<img src="<c:url value="${photo.path}"/>" height="600px" width="600px"/>
										</div>
									</c:forEach>
								</div>
								<!-- Controls -->
								<a class="left carousel-control"
									href="#carousel-example-generic" data-slide="prev"> <span
									class="glyphicon glyphicon-chevron-left"></span>
								</a> <a class="right carousel-control"
									href="#carousel-example-generic" data-slide="next"> <span
									class="glyphicon glyphicon-chevron-right"></span>
								</a>
							</div>
							</div>
							<div class="col-md-3 "></div> --%>
							<div class="col-md-10 "  >
														<div class="flexslider">
														    <ul class="slides">
														    	<c:forEach var="photo" items="${jobInfo.infos}"
																	varStatus="status">
																	<li >
																		<img src="<c:url value="${photo.path}"/>"/>
																	</li>
																</c:forEach>
														    </ul>
														</div>
													</div>
						</div>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
		<div class="row" id="hiddenDiv"></div>
	</div>
	<div class="col-md-1"></div>
</div>
<br />
<script>
	$(function(){
		$('.carousel').carousel({
			  interval:1000
			})
		$('.flexslider').flexslider({
			animation: "fade",
	        directionNav: true,
	        pauseOnHover: true,    //鼠标悬停，则停止轮播
	        pauseOnAction: false,  //false表示开启自动播放
	        slideshowSpeed: 1000,  //速度
	        before: function(){},  
    	});
		//$("#table").hide();
		getAllItemName();
		var selectDate;
		var myDate = new Date();
		var today=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
		 $('#date').datepicker({//添加日期选择功能  
             numberOfMonths:1,//显示几个月  
             showButtonPanel:true,//是否显示按钮面板  
             dateFormat: 'yy-mm-dd',//日期格式  
             clearText:"清除",//清除日期的按钮名称  
             closeText:"关闭",//关闭选择框的按钮名称  
             yearSuffix: '年', //年的后缀  
             showMonthAfterYear:true,//是否把月放在年的后面  
             defaultDate:today,//默认日期  
            // minDate:'2011-03-05',//最小日期  
           //  maxDate:'2011-03-20',//最大日期  
             monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
             dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
             dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
             dayNamesMin: ['日','一','二','三','四','五','六'],  
             onSelect: function(selectedDate) {//选择日期后执行的操作  
             }  
         });  
	})
	function validateSit(){
		var itemname=$("#itemname").val();
		if(itemname==""||itemname==null){
			$("#validateInfo").text("请填写项目名称");
			return false;
		}else{
			return true;
		}
	}
</script>