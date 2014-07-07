<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- tiles prefix -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!-- HTML5 definition -->
<!DOCTYPE html>

<html>
<head>
    <tiles:insertAttribute name="_meta" />
</head>
<body>
    

    <div class="container">
    	 <div  class="row">
            <div class="col-md-1"></div>
            <div id="main-content" class="col-md-10">
            	<tiles:insertAttribute name="_header" />
        		<tiles:insertAttribute name="_body" />
    		</div>
            <div class="col-md-1"></div>
       	</div>
        <tiles:insertAttribute name="_footer" />
    </div>
</body>
</html>