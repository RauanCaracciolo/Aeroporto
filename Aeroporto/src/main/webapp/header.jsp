<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>    
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href="#">Aeroporto</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="arriving.jsp">Desembarque</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="boarding.jsp">Embarque</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="takingoff.jsp">Hall 1</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="tookoff.jsp">Hall 2</a>
		      </li>
		    <% 
		      HttpSession sessao = request.getSession(false);
		      if(sessao == null || sessao.getAttribute("Usuario") == null){
		      
		      }else{
		      	out.println("<li class=\"nav-item\">");
		      	out.println("<a class= \"nav-link\" href= \"cadastro.jsp\">Cadastro</a>");
		      	out.println("</li>");
		      	out.println("<li class=\"nav-item\">");
		      	out.println("<a class= \"nav-link\" href= \"atualizar.jsp\">Atualizar</a>");
		      	out.println("</li>");
		      %>
		    </ul>
		  </div>
		  <% 
		   out.println("<a href=\"Adm?action=logout\"><button type=\"button\" class=\"btn btn-primary\">Deslogar</button></a>");
		      } %>
		  <a class="nav-link" href="login.jsp"><button type="button" class="btn btn-primary">Login</button></a>
		</nav>
    </header>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>