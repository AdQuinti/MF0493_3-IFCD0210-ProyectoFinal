<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!-- CSS  y JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado 2024 Spain - Actualizacion informacion</title>
<!--  HOJA ESTILO -->
	<link href="<c:url value='/css/style.css' />" rel="stylesheet">
<!--  HOJA ESTILO -->
</head>
<body>
<div class="contenedorFather">
<!-- MENU -->
	<jsp:include page="includes/menu.jsp" />
<!-- END MENU -->
	<!-- HEADER -->
<h3>ACTUALIZAR LISTADO DE CONSUMO PETROLEO - 2024 -</h3> 
	<!-- END HEADER -->

  <form action="ControladorPetrol" name="form" method="get">
        <input type="hidden" name="instruccion" value="actualizarBBDD">
        <input type="hidden" name="idPetrol" value="${id_petrolCargar.petrol_id }">
		<!-- CUERPO -->
	<table class="table-container">

            <tr>
                <td class="celdainput">Fecha</td>
                <td ><input type="text" name="fechaA" value="${id_petrolCargar.fecha}"></td>
            </tr>
            <tr>
                <td class="celdainput">Comunidad</td>
                <td ><input type="text" name="comunidadA" value="${id_petrolCargar.comunidad}"></td>
            </tr>
            <tr>
                <td class="celdainput">Provincia</td>
                <td ><input type="text" name="provinciaA" value="${id_petrolCargar.provincia}"></td>
            </tr>
            <tr>
                <td class="celdainput">Producto</td>
                <td ><input type="text" name="productoA" value="${id_petrolCargar.producto}"></td>
            </tr>
            <tr>
                <td class="celdainput">Consumo</td>
                <td ><input type="text" name="consumoA" value="${id_petrolCargar.consumo}"></td>
            </tr>
            <tr>
                <td colspan="2" class="modi">
                <input type="submit" value="ACTUALIZAR">
                <input type="reset" name="BORRAR"/>
                </td>
            </tr>
       <!-- END CUERPO -->
        </table>

    </form>
</div>
</body>
</html>

