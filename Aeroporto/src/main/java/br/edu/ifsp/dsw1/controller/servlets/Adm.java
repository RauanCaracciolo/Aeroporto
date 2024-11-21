package br.edu.ifsp.dsw1.controller.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.entity.FlightDataCollection;
import br.edu.ifsp.dsw1.model.flightstates.*;
import br.edu.ifsp.dsw1.model.observer.FlightDataObserver;

@WebServlet("/Adm")
public class Adm extends HttpServlet implements FlightDataObserver {
    private static final long serialVersionUID = 1L;
    private static final String ADMIN_USER = "ADMIN";
    private static final String ADMIN_PASS = "ADMIN";
    private static final FlightDataCollection flightCollection = new FlightDataCollection();

    @Override
    public void init() throws ServletException {
        super.init();
        flightCollection.register(this);
        // Inicializa as listas que servirão para filtrar os voos por estado
        getServletContext().setAttribute("arrivingFlights", new ArrayList<>());
        getServletContext().setAttribute("boardingFlights", new ArrayList<>());
        getServletContext().setAttribute("takingOffFlights", new ArrayList<>());
        getServletContext().setAttribute("tookOffFlights", new ArrayList<>());
    }

    @Override
    public void destroy() {
        flightCollection.unregister(this);
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String redirectPage = "index.jsp";

        switch (action) {
        	// Checa se as informações colocadas foram corretas, se sim cria uma sessao e permite ao usuario cadastrar e atualizar, senao joga para a pagina de erro
            case "login":
                if (ADMIN_USER.equals(request.getParameter("username")) && ADMIN_PASS.equals(request.getParameter("senha"))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("Usuario", ADMIN_USER);
                    session.setMaxInactiveInterval(5 * 60); // 5 minutos
                    redirectPage = "admin.jsp";
                } else {
                    redirectPage = "erro.jsp";
                }
                break;

            case "logout":
            	// Invalida a sessao, tirando os poderes de ADMIN do usuario
                HttpSession session = request.getSession(false);
                session.invalidate();
                break;

            case "cadastro":
            	//Cadastra um novo voo com uma funçao propria
                FlightData flight = createFlightFromRequest(request);
                flightCollection.insertFlight(flight);
                redirectPage = "cadastro.jsp";
                break;

            case "update":
            	//Atualiza o voo identificado pelo numero enviado na requisicao, tambem atualiza a pagina com os voos atualizados
                flightCollection.updateFlight(Long.parseLong(request.getParameter("flightnumber")));
                request.setAttribute("flights", flightCollection.getAllFligthts());
            	request.getRequestDispatcher("atualizar.jsp").forward(request, response);
                redirectPage = "atualizar.jsp";
                break;

            case "listAll":
            	//Lista todos os voos
            	request.setAttribute("flights", flightCollection.getAllFligthts());
            	request.getRequestDispatcher("atualizar.jsp").forward(request, response);
            	break;
            case "listArriving":
            	//Busca no contexto a lista de voos no estado "Arriving"
                 List<FlightData> arrivingFlights = (List<FlightData>) getServletContext().getAttribute("arrivingFlights");
            	 request.setAttribute("flights", arrivingFlights);
                 request.getRequestDispatcher("arriving.jsp").forward(request, response);
                 break;
            case "listBoarding":
            	//Busca no contexto a lista de voos no estado "Boarding"
            	 List<FlightData> boardingFlights = (List<FlightData>) getServletContext().getAttribute("boardingFlights");
            	 request.setAttribute("flights", boardingFlights);
            	 request.getRequestDispatcher("boarding.jsp").forward(request, response);
            	 break;
            case "listTakingOff":                  
            	//Busca no contexto a lista de voos no estado "TakingOff"
            	 List<FlightData> takingOffFlights = (List<FlightData>) getServletContext().getAttribute("takingOffFlights");
            	 request.setAttribute("flights", takingOffFlights);
            	 request.getRequestDispatcher("takingoff.jsp").forward(request, response);
            	 break;
            case "listTookOff":
            	//Busca no contexto a lista de voos no estado "TookOff"
            	 List<FlightData> tookOffFlights = (List<FlightData>) getServletContext().getAttribute("tookOffFlights");
            	 request.setAttribute("flights", tookOffFlights);
            	 request.getRequestDispatcher("tookoff.jsp").forward(request, response);
            	break;

            default:
                break;
        }
        //redireciona para a pagina devida
        response.sendRedirect(redirectPage);
    }

    private FlightData createFlightFromRequest(HttpServletRequest request) {
    	//Pega as informaçoes do formulario
        Long number = Long.parseLong(request.getParameter("flightnumber"));
        String company = request.getParameter("company");
        String time = request.getParameter("time");
        //Cria um voo com as informaçoes
        FlightData flight = new FlightData(number, company, time);
        //Por padrao, seta o estado como Arriving
        flight.setState(Arriving.getIntance());
        return flight;
    }
    @Override
    public void update(FlightData flight) {
        // Verifica o estado atual do voo
        String state = flight.getState().getClass().getSimpleName();

        // Recupera as listas do contexto
        List<FlightData> arrivingFlights = (List<FlightData>) getServletContext().getAttribute("arrivingFlights");
        List<FlightData> boardingFlights = (List<FlightData>) getServletContext().getAttribute("boardingFlights");
        List<FlightData> takingOffFlights = (List<FlightData>) getServletContext().getAttribute("takingOffFlights");
        List<FlightData> tookOffFlights = (List<FlightData>) getServletContext().getAttribute("tookOffFlights");

        // Remove o voo de todas as listas
        arrivingFlights.remove(flight);
        boardingFlights.remove(flight);
        takingOffFlights.remove(flight);
        tookOffFlights.remove(flight);

        // Adiciona o voo a lista correta
        switch (state) {
            case "Arriving" -> arrivingFlights.add(flight);
            case "Boarding" -> boardingFlights.add(flight);
            case "TakingOff" -> takingOffFlights.add(flight);
            case "TookOff" -> tookOffFlights.add(flight);
        }

        // Atualiza o contexto com a lista nova
        getServletContext().setAttribute("arrivingFlights", arrivingFlights);
        getServletContext().setAttribute("boardingFlights", boardingFlights);
        getServletContext().setAttribute("takingOffFlights", takingOffFlights);
        getServletContext().setAttribute("tookOffFlights", tookOffFlights);
        
    }
}
