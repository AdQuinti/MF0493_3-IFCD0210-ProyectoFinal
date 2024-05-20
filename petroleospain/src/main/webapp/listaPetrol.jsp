<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- CSS  y JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado 2024 Spain</title>
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
	<h3>LISTADO DE CONSUMO: Estadística Petróleo - Ene. y Feb. 2024 -</h3>


	<!-- END HEADER -->
<!-- CUERPO -->

	<table class="TheadTfoot">
		<thead>
			<tr>
				<td>ID</td>
				<td class="modi">FECHA</td>
				<td class="modi">COMUNIDAD</td>
				<td class="modi">PROVINCIA</td>
				<td>TIPO DE PRODUCTO</td>
				<td class="modi">CONSUMO</td>
				<td colspan="2" class="modi">MODIFICAR</td>
			</tr>
		</thead>
	</table>
		<div class="table-container">
			<table class="scrollable-table">
	
				<tbody>
					<!-- cuerpo tabla -->
					<!--  info debe coincidir con metodo que lo llame desde ControladorPetrol -->
					<c:forEach var="tempPetrol" items="${listaPetrol}">
					<!-- enlace para el id de cada actor a actualizar-->
					<c:url var="linkTemp" value="ControladorPetrol">
						<c:param name="instruccion" value="cargar">
						</c:param>
						<c:param name="id_Petrol" value="${tempPetrol.petrol_id}"></c:param>
					</c:url>
					<!-- enlace para el id de cada actor a eliminar-->
					<c:url var="linkEliminarTemp" value="ControladorPetrol">
						<c:param name="instruccion" value="eliminar">
						</c:param>
						<c:param name="id_Petrol" value="${tempPetrol.petrol_id}"></c:param>
					</c:url>
					<tr>
						<td>${tempPetrol.petrol_id}</td>
						<td class="modi">${tempPetrol.fecha}</td>
						<td class="modi">${tempPetrol.comunidad}</td>
						<td >${tempPetrol.provincia}</td>
						<td>${tempPetrol.producto}</td>
						<td>${tempPetrol.consumo}</td>
						<td><a type="button" href="${linkTemp}">Actualizar</a></td>
						<td><a type="button" href="${linkEliminarTemp}">Eliminar</a></td>
				</c:forEach>
				</tr>
				<tr>
					<td></td>
				</tr>


			</tbody>
		</table>
	</div>
	<!-- END CUERPO -->
			<!-- FOOTER -->
	<table class="TheadTfoot">
			<tfoot>
				<tr>
					<td colspan="8">FUENTE: - Comisión Nacional de los Mercados y la
						Competencia (MINISTERIO DE ASUNTOS ECONÓMICOS Y TRANSFORMACIÓN
						DIGITAL)</td>
				</tr>
				<!-- END FOOTER -->
			</tfoot>
	</table>
</div>
</body>
</html>