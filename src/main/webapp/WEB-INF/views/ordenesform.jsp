<%@page import="Model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javaScript">
	var total = 0;
	var rate = 0;
	function clienteSeleccionado(x,ts) {
		rate = ts;
		$.ajax({
            url : 'loadcliente/'+x,
            success : function(data) {
                $('#productos').html(data);
            }
        });
		total = 0;
		document.getElementById('suma').value = total;
		document.getElementById('tasa').value = rate;
		document.getElementById('dolares').value = total*rate;
		document.getElementById('idcliente').value = x;
	}
	
	function sumarTotal(x) {
		total += x;
		document.getElementById('suma').value = total;
		document.getElementById('dolares').value = total*rate;
	}
	window.onload = function () {
		document.getElementById('suma').value = "";
		document.getElementById('dolares').value = "";
		document.getElementById('tasa').value = "";
		document.getElementById('idcliente').value = "";
		document.getElementById('delivery').value = "";
	}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creacion de ordenes</title>

</head>
<body>

	<h1>Crear orden</h1>
	<div id="cliente">
		<strong>Seleccione un Cliente:</strong>
		<c:if test="${not empty clientes}">
			<select>
				<c:forEach var="customer" items="${clientes}">
					<option value="${customer.id}"
						onclick="clienteSeleccionado(${customer.id},${tasa})">${customer.name}</option>
				</c:forEach>
			</select>
		</c:if>
	</div>
	<br>
	<div id="productos"></div>
	<br>
	<table>
		<form:form modelAttribute="orden" action="agregarOrden" method="POST">
			<tr>
				<td>Total Euros: <form:input path="euros" id="suma" /><!--input type="text" id="suma" disabled--></td>
			</tr>
			<tr>
				<td>Tasa de cambio: <form:input path="tasa" type="text" id="tasa" /><!--input type="text" id="tasa" disabled--></td>
			</tr>
			<tr>
				<td>Total Dolar: <form:input path="dolares" type="text" id="dolares" /><!--input type="text" id="dolares" disabled--></td>
			</tr>
			<tr>
				<td><form:hidden path="idCliente" id="idcliente"/><!--<input type="hidden" id="idcliente" value="data1"--></td>
			</tr>
			<tr>
				<td>Direccion entrega: <!-- <input type="text" id="delivery" required> -->
				<form:input path="delivery" id="delivery" required="true"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Crear Orden"></td>
			</tr>
		</form:form>
	</table>
</body>
</html>