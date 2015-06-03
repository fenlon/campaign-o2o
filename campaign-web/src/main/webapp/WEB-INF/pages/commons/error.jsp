<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<spring:hasBindErrors name="user">
		<c:if test="${errors.fieldErrorCount > 0}">  
        字段错误：<br />
			<c:forEach items="${errors.fieldErrors}" var="error">
				<spring:message var="message" code="${error.code}"
					arguments="${error.arguments}" text="${error.defaultMessage}" />  
            ${error.field}------${message}<br />
			</c:forEach>
		</c:if>

		<c:if test="${errors.globalErrorCount > 0}">  
        全局错误：<br />
			<c:forEach items="${errors.globalErrors}" var="error">
				<spring:message var="message" code="${error.code}"
					arguments="${error.arguments}" text="${error.defaultMessage}" />
				<c:if test="${not empty message}">  
                ${message}<br />
				</c:if>
			</c:forEach>
		</c:if>
	</spring:hasBindErrors>
</body>
</html>