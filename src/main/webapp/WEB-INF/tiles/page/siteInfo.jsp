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
		<div class="table-responsive">
			<table class="table table-bordered table-hover table-striped">
				<h3>泰伦智能工作管理系统</h3>
				<thead>
					<tr >
						<th style="text-align:center">项目工地编号</th>
						<th style="text-align:center">项目工地简称</th>
						<th style="text-align:center">详细说明</th>
						<th style="text-align:center"><button class="btn btn-primary" data-toggle="modal"
			data-target="#addSite">新增工地</button></th>
					</tr>
				</thead>
				<tbody>
					<%-- 	<c:forEach var="workSiteInfo" items="${wkSiteList}" varStatus="status">
						<tr>
							<td id="num${status.index}">${workSiteInfo.num}</td>
							<td id="stname${status.index}">${workSiteInfo.sitename}</td>
							<td id="description${status.index}"><a style="width:27em"><span class="truncate_style">${workSiteInfo.description}</span></a></td>
							<td>
								<button class="btn btn-primary" data-toggle="modal" onclick="chgSiteInfo(<%=request.getContextPath()%>/,${status.index})">修改</button>
							</td>
							<td>
								<button class="btn btn-primary"
									onclick="deleteSite(<%=request.getContextPath()%>/ ,${status.index})">删除</button>
							</td>
						</tr>
					</c:forEach> --%>
				</tbody>
			</table>
		</div>
		
		<div class="modal fade" id="addSite" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<form class="form-horizontal" id="addd" role="form"
					onsubmit="return false;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">新增工地信息</h4>
							<p id="successInfo" style="color: #f00"></p>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="siteNum" class="col-sm-2 control-label">工地编号</label>
								<div class="col-sm-10">
									<input type="text" class="form-control required" id="siteNum"
										name="siteNum" placeholder="工地编号">
								</div>
							</div>
							<div class="form-group">
								<label for="siteName" class="col-sm-2 control-label">简称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="siteName"
										name="siteName" placeholder="项目工地简称">
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-2 control-label">详细说明</label>
								<div class="col-sm-10">
									<textarea class="form-control" name="description"
										id="description" placeholder="详细说明"></textarea>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary"
								onclick="addSite(<%=request.getContextPath()%>/)">保存</button>
						</div>
					</div>
				</form>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>

		<div class="modal fade" id="chgSite" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改工地信息</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="addd" role="form"
							onsubmit="return false;">
							<div class="form-group">
								<label for="siteNum" class="col-sm-2 control-label">工地编号</label>
								<div class="col-sm-10">
									<input type="text" class="form-control required" id="siteNum2"
										name="siteNum" placeholder="工地编号">
								</div>
							</div>
							<div class="form-group">
								<label for="siteName" class="col-sm-2 control-label">简称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="siteName2"
										name="siteName" placeholder="项目工地简称">
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-sm-2 control-label">详细说明</label>
								<div class="col-sm-10">
									<textarea class="form-control" name="description"
										id="description2" placeholder="详细说明"></textarea>
								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary"
							onclick="addSite(<%=request.getContextPath()%>/)">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="row"></div>
		<intput type="hidden" id="ID"/>
		<div id="Pagination" class="flickr"></div>
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
    
    var itemNum=${itemNum};
    //console.log(itemNum);
    var pageIndex = 0;     //页面索引初始值   
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可   
    $(function () {
           //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
           $("#Pagination").pagination(itemNum, {
               callback: PageCallback,  //PageCallback() 为翻页调用次函数。
               //这里如果用默认的#，会回到首页
               link_to: "javascript:void(0)",
               prev_text: "上一页",
               next_text: "下一页 ",
               items_per_page:pageSize,
               num_edge_entries: 2,       //两侧首尾分页条目数
               num_display_entries: 6,    //连续分页主体部分分页条目数
               current_page: pageIndex,   //当前页索引
           });
           InitTable(0);    //Load事件，初始化表格数据，页面索引为0（第一页）
     });
    //翻页调用   
     function PageCallback(index) {   
        InitTable(index);  
    }   
    //请求数据   
    function InitTable(pageIndex) {   
        $.ajax({   
     	   url: '/autojobserver/siteInfo/pagination',      //提交到一般处理程序请求数据   
            type: "POST",  
            data: {
         	   "pageIndex":pageIndex,
         	   "pageSize":pageSize
            },        //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
            success: function(data) {
         	   			$("tbody").empty();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）   
				    	console.log(data);
         	   			var i=0;
         	   			for(;i<data.siteSubList.length;i++){
         	   				$("tbody").append(
             	   					'<tr>'+
											'<td id="num'+i+'">'+data.siteSubList[i].num+'</td>'+
											'<td id="stname'+i+'">'+data.siteSubList[i].sitename+'</td>'+
											'<td  style="width:27em" id="description'+i+'"><span class="truncate_style">'+data.siteSubList[i].description+'</span></td>'+
											'<td><div class="btn-group">'+
												'<button class="btn btn-primary" data-toggle="modal" onclick="chgSiteInfo(<%=request.getContextPath()%>/,'+i+')">修改</button>'+
												'<button class="btn btn-primary" onclick="deleteSite(<%=request.getContextPath()%>/ ,'+i+')">删除</button>'+
											'</div></td>'+
										'</tr>'+
										'<input type="hidden" id="id'+i+'" value="'+data.siteSubList[i].id+'">');
         	   			}
         	   			
            },
            //error: console.log("123"),
        }); 
    }
</script>

