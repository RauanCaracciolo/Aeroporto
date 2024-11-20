package br.edu.ifsp.dsw1.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;


@WebServlet("/Adm")
public class Adm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NOME = "ADMIN";
    private static final String SENHA = "ADMIN";
	private static final FlightDataCollection colecao = new FlightDataCollection();

   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processaRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processaRequest(request,response);
	}

	private void processaRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String acao = request.getParameter("action");
		String redirect = "";
		switch(acao){
			case "login":
				String nome = request.getParameter("username");
				String senha = request.getParameter("senha");
				if(NOME.equals(nome) && SENHA.equals(senha)) {
					HttpSession session = request.getSession();
					session.setAttribute("Usuario", NOME);
					session.setMaxInactiveInterval(5 * 60);
					redirect = "admin.jsp";
				}else {
					redirect = "erro.jsp";
				}
				break;
			case "deslogar":
				HttpSession sessao = request.getSession(false);
				sessao.invalidate();
				redirect = "index.jsp";
				break;
			case "cadastro":
				Long number = Long.parseLong(request.getParameter("flightnumber"));
				String company = request.getParameter("company");
				String time = request.getParameter("time");	
				colecao.insertFlight(new FlightData(number, company, time));
				redirect = "cadastro.jsp";
			default:
				break;
		}
	response.sendRedirect(redirect);
	}
}
