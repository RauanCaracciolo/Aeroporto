package br.edu.ifsp.dsw1.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String NOME = "ADMIN";
    private static final String SENHA = "ADMIN";

    
    public LoginServlet() {
        super();
    }
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("username");
		String senha = request.getParameter("senha");
		if(NOME.equals(nome) && SENHA.equals(senha)) {
			response.sendRedirect("admin.jsp");
		}else {
			response.sendRedirect("erro.jsp");
		}
	}
}
