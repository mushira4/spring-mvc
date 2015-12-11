<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal" var="user" />
		<div>
			Olá ${user.name}
		</div>
	</security:authorize>
	<c:out value="${success}"></c:out>
	<table>
		<tr>
			<td>Title</td>
			<td>Values</td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td><a href="<c:out value="${spring:mvcUrl(\"PC#show\").arg(0, product.id).build()}"/>">${product.title}</a></td>
				<td><c:forEach items="${product.prices}" var="price">
						[${price.value} - ${price.bookType}]
					</c:forEach></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>