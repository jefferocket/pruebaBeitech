<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seleccione el Cliente</title>
</head>
<body>
	<form:form action="seleccion" method="post"
		commandName="selectedClient">
		<strong>Seleccione un Cliente:</strong>
		<c:if test="${not empty clientes}">
			<form:select path="id">
				<c:forEach var="customer" items="${clientes}">
					<form:option value="${customer.id}">${customer.name}</form:option>
				</c:forEach>

			</form:select>
		</c:if>
		<input type="submit" value="submit" name="submit" />
	</form:form>

</body>
</html>