<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ page import="java.util.Locale"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Starter</title>
		<style>
		    <%@ include file="resources/css/loader.css"%>
		</style>
		${applicationScope.app_css}
	</head>
	<body>
		<div id="circle">
			<div class="dot dot1"></div>
			<div class="dot dot2"></div>
			<div class="dot dot3"></div>
			<div class="dot dot4"></div>
		</div>
	</body>
	<%
		Locale locale = RequestContextUtils.getLocale(request);
	%>
	<spring:eval expression="@environment.acceptsProfiles('development')" var="isDevelopment" />
	<%
		if ((Boolean) pageContext.getAttribute("isDevelopment")) {
	%>
	<script src="services/i18n.js"></script>
	<%
		} else {
	%>
	<script
		src="services/i18n-<%=locale%>_<spring:eval expression='@environment["application.version"]'/>.js"></script>
	<%
		}
	%>
	${applicationScope.app_js}
	
</html>