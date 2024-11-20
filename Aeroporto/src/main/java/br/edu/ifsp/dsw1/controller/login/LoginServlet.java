package br.edu.ifsp.dsw1.controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.apache.tomcat.jakartaee.bcel.classfile.Constant;


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
			HttpSession session = request.getSession();
			session.setAttribute("Usuario", NOME);
			session.setMaxInactiveInterval(5 * 60);
			response.sendRedirect("admin.jsp");
		}else {
			response.sendRedirect("erro.jsp");
		}
	}
}
