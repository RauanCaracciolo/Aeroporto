<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="LoginServlet" method="post">
	  <div class="form-group">
	    <label for="username">Usuario</label>
	    <input class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter email" name="username">
	  </div>
	  <div class="form-group">
	    <label for="senha">Senha</label>
	    <input  class="form-control" id="senha" placeholder="Password" name="senha">
	  </div>
	  <button type="submit" class="btn btn-primary">Login</button>
	</form>
	<h2 style="color: red;">Erro no Login! Tente novamente ou volte a pagina inicial!</h2>
	<h3><a href="index.jsp">Volte a pagina inicial</a></h3>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>