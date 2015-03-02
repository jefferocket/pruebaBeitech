<%@page import="Model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javaScript">
	
	function clienteSeleccionado(x) {
		$.ajax({
            url : 'loadcliente/'+x,
            success : function(data) {
                $('#productos').html(data);
            }
        });
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creacion de ordenes</title>

</head>
<body>
	<h1>Crear orden</h1>
	<div id = "cliente" >
	<strong>Seleccione un Cliente:</strong>
		<c:if test="${not empty clientes}">
			<select>
				<c:forEach var="customer" items="${clientes}">
					<option value="${customer.id}" onclick="clienteSeleccionado(${customer.id})">${customer.name}</option>
				</c:forEach>
			</select>
		</c:if>
	</div>
	<br>
	<div id = "productos" >
	
	</div>
</body>
</html>