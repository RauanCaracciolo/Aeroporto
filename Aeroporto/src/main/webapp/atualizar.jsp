<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="br.edu.ifsp.dsw1.model.entity.FlightData"%>
<%@page import="java.util.List"%>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Atualizar</title>
</head>
<body>
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
        <td><a href="Adm?action=update&flightnumber=<%= voo.getFlightNumber() %>"><button>Atualizar</button></a></td>
    </tr>
    <%
        }
    }else{
    %>
    <td colspan="5">Nenhum Voo encontrado</td>
    <%} %>
    </tbody>
</table>    
<a href="Adm?action=listAll"><button>Mostrar todos os voos</button></a>

</body>
</html>