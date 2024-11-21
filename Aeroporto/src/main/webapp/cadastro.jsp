<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Vôo</title>
</head>
<body>
	<form action="Adm?action=cadastro" method="post">
	  <div class="form-group">
	    <label for="flightnumber">Número do Vôo</label>
	    <input class="form-control" id="flightnumber" type="number" name="flightnumber">
	  </div>
	  <div class="form-group">
	    <label for="company">Empresa</label>
	    <input  class="form-control" id="company" name="company">
	  </div>
	  <div class="form-group">
	    <label for="time">Horario</label>
	    <input  class="form-control" id="time" name="time">
	  </div>
	  <button type="submit" class="btn btn-primary">Cadastrar</button>
	</form>
</body>
</html>