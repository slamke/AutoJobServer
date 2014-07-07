<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
               
                <div style="height:300px">
                <br/>
                <br/>
                <br/>
                <h3 style="text-align:center">欢迎进入泰伦智能工作管理系统</h3>
                <br/>
                <br/>
                <br/>
                </div>
                <%-- <div class="row">
                    <div class="col-md-1" style="text-align: center">
                        
                    </div>
                    <div class="col-md-10" style="text-align: center">
                        <div class="table-responsive">
						  <table class="table table-bordered table-hover table-striped">
						  	<h3>欢迎进入泰伦智能工作管理系统</h3>
						    <thead>
						    	<tr>
						    		<th>项目工地</th>
						    		<th>填写日期</th>
						    		<th>填写人</th>
						    		<th></th>
						    	</tr>
						    </thead>
						    <tbody >
						    </tbody>
						  </table>
						</div>
						<div class="row">
						</div>
						<div id="Pagination" class="flickr"></div>
                    </div>
                    <div class="col-md-1"></div>
                </div>
                <br/>
                <!-- <script>
                	alert("in");
                </script> -->
                <script>
                	var itemNum=${itemNum};
	                var pageIndex = 0;     //页面索引初始值   
	                var pageSize = 15;     //每页显示条数初始化，修改显示条数，修改这里即可   
	                $(function () {
	                	
	                     InitTable(0);    //Load事件，初始化表格数据，页面索引为0（第一页）
	                       //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
	                       $("#Pagination").pagination(itemNum, {
	                           callback: PageCallback,  //PageCallback() 为翻页调用次函数。
	                              prev_text: "上一页",
	                           next_text: "下一页 ",
	                           items_per_page:pageSize,
	                           num_edge_entries: 2,       //两侧首尾分页条目数
	                              num_display_entries: 6,    //连续分页主体部分分页条目数
	                              current_page: pageIndex,   //当前页索引
	                       });
	                       //翻页调用   
	                       function PageCallback(index, jq) {             
	                           InitTable(index);  
	                       }  
	                       //请求数据   
	                       function InitTable(pageIndex) {                                  
	                           $.ajax({   
	                        	   url: '<%=request.getContextPath()%>/home/jobinfo',      //提交到一般处理程序请求数据   
	                               type: "POST",  
	                               data: {
	                            	   "pageIndex":pageIndex,
	                            	   "pageSize":pageSize
	                               },        //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                   
	                               success: function(data) {
	                            	  /*  document.getElementById("Pagination").innerHTML=jobinfo; */
	                            	   /* $("#Pagination").write(jobinfo); */
	                            	   $("tbody").empty();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）   
	                                 	/* $("tbody").append('<c:forEach var="jobInfo" items="${allJobInfos}" varStatus="status">'+
	        	                				'<input type="hidden" id="jobinfoid${status.index}" value="${jobInfo.id}"/>'+
	        	                				'<tr>'+
	        							    		'<td id="location${status.index}">${jobInfo.location}</td>'+
	        							    		'<td id="time${status.index}">${jobInfo.time}</td>'+
	        							    		'<td id="realname${status.index}">${jobInfo.staff.realname}</td>'+
	        							    		'<td>'+
	        							    			'<button class="btn btn-primary" onclick="showItem(${status.index})">查看</button>'+
	        							    		'</td>'+
	        							    	'</tr>'+
	        						    	'</c:forEach>'); */
	        						    	for(var j=0;j<data.jobInfoList.length;j++){
	        						    		$("tbody").append(
	        						    				 
	                	                				'<tr>'+
		            							    		'<td id="location'+j+'">'+data.jobInfoList[j].location+'</td>'+
		            							    		'<td id="time'+j+'">'+data.jobInfoList[j].date+'</td>'+
		            							    		'<td id="realname'+j+'">'+data.jobInfoList[j].staff+'</td>'+
		            							    		'<td>'+
		            							    			'<button class="btn btn-primary" onclick="showItem('+j+')">查看</button>'+
		            							    		'</td>'+
	            							    		'</tr>'+'<input type="hidden" id="jobinfoid'+j+'" value="'+data.jobInfoList[j].id+'"/>');
	        						    	}
	        						    	
	                                   // $("#Result").append(data);             //将返回的数据追加到表格   
	                               }  
	                           }); 
	                       }
	                   }); 
                </script>
            --%>