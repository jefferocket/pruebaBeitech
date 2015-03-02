<%@page import="Model.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	}
	
	function sumarTotal(x) {
		total += x;
		document.getElementById('suma').value = total;
		document.getElementById('dolares').value = total*rate;
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
		<form action="agregarOrden" method="post">
			<tr>
				<td>Total Euros: <input type="text" id="suma" disabled></td>
			</tr>
			<tr>
				<td>Tasa de cambio: <input type="text" id="tasa" disabled></td>
			</tr>
			<tr>
				<td>Total Dolar: <input type="text" id="dolares" disabled></td>
			</tr>
			<tr>
				<td><input type="submit" value="Crear Orden"></td>
			</tr>
		</form>
	</table>
</body>
</html>