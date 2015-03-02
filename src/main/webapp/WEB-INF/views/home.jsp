<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<ul type="circle">
            <li><a href="seleccionUsuario.htm">Seleccionar Productos</a></li>
            <li><a href="ordenesform.htm">Crear Ordenes</a></li>
        </ul>

</body>
</html>
