<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!-- CSS  y JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado 2024 Spain - Insertar informacion</title>
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
	<h3>INSERTAR EN LISTADO DE CONSUMO PETROLEO - 2024 -</h3>
	<!-- END HEADER -->

  <form action="ControladorPetrol" name="form" method="get">
        <input type="hidden" name="instruccion" value="insertarBBDD">
        <table class="table-container">
	<!-- CUERPO -->
            <tr>
                <td class="celdainput">ID</td>
                <td ><input type="text" name="idPetrol" /></td>
            </tr>
            <tr>
                <td class="celdainput">FECHA</td>
                <td ><input type="text" name="fechaA" /></td>
            </tr>
            <tr>
                <td class="celdainput">COMUNIDAD</td>
                <td ><input type="text" name="comunidadA" /></td>
            </tr>
            <tr>
                <td class="celdainput">PROVINCIA</td>
                <td ><input type="text" name="provinciaA" /></td>
            </tr>
            <tr>
                <td class="celdainput">PRODUCTO</td>
                <td ><input type="text" name="productoA" /></td>
            </tr>
           <tr>
                <td class="celdainput">CONSUMO</td>
                <td width="73%"><input type="text" name="consumoA" /></td>
            </tr>
            <tr>
                <td colspan="2" class="modi">
                <input type="submit" value="INSERTAR">
                <input type="reset" name="BORRAR"/>
                </td>
            </tr>
        </table>

    </form>
</div>
</body>
</html>

