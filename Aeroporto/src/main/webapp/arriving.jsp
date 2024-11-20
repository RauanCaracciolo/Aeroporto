<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Desmbarque</title>
</head>
<body>
	<a href="Adm?action=listArriving"><button>Atualiza</button></a>
	<table class="table table-dark table-bordered">
    <thead>
    <tr>
        <th scope="col">Numero do Voo</th>
        <th scope="col">Agencia</th>
        <th scope="col">Horario</th>
        <th scope="col">Status</th>
    </tr>
    </thead>

    <tbody>
    <%
        List<FlightData> lista = (List<FlightData>) request.getAttribute("flights");
        if(lista != null && !lista.isEmpty()){
            for(FlightData voo : lista){
    %>

    <tr>
        <td><%= voo.getFlightNumber()%></td>
        <td><%= voo.getCompany()%></td>
        <td><%= voo.getTime()%></td>
        <td><%= voo.getState().getClass().getSimpleName()%></td>
    </tr>

    <%
        }
    }else{
    %>
    <td colspan="5">Nenhum Voo encontrado</td>
    <%} %>

    </tbody>

</table>
</body>
</html>