<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Selección</title>
</head>
<body>
	<h1>Selección de Productos para : ${cliente.name}</h1>


	<c:choose>
		<c:when test="${not empty cliente.avaible_products}">
			<c:out value="Vacio"> jjskjsa </c:out>
		</c:when>
		<c:otherwise>
			<form:form method="get" action="agregarproductos"
				commandName="cliente">
				<table>
					<form:hidden path="id" value="${cliente.id}" />
					<form:hidden path="name" value="${cliente.name}" />

					<c:forEach items="${productos}" var="product">
						<tr>
							<td><form:checkbox path="${cliente.avaible_products}"
									name="lista" value="${product.id}" /> ${product.name}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="2"><input type="submit" value="Submit" /></td>
					</tr>
				</table>
			</form:form>

		</c:otherwise>
	</c:choose>


</body>
</html>
