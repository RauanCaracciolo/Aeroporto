<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="Adm?action=update" method="post">
	  <div class="form-group">
	    <label for="flightnumber">Número do Vôo</label>
	    <input class="form-control" id="flightnumber" type="number" name="flightnumber">
	  </div>
	  <button type="submit" class="btn btn-primary">Atualizar</button>
	</form>
</body>
</html>