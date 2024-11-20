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
        // Inicializa as listas no contexto
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
                HttpSession session = request.getSession(false);
                session.invalidate();
                break;

            case "cadastro":
                FlightData flight = createFlightFromRequest(request);
                System.out.println(flight);
                flightCollection.insertFlight(flight);
                redirectPage = "cadastro.jsp";
                break;

            case "update":
                flightCollection.updateFlight(Long.parseLong(request.getParameter("flightnumber")));
                redirectPage = "atualizar.jsp";
                break;

            case "listArriving":
            case "listBoarding":
            case "listTakingOff":
            case "listTookOff":
                String state = action.replace("list", "");
                List<FlightData> filteredFlights = filterFlightsByState(state);
                request.setAttribute("flights", filteredFlights);
                request.getRequestDispatcher(state.toLowerCase() + ".jsp").forward(request, response);
                return;

            default:
                break;
        }

        response.sendRedirect(redirectPage);
    }

    private FlightData createFlightFromRequest(HttpServletRequest request) {
        Long number = Long.parseLong(request.getParameter("flightnumber"));
        String company = request.getParameter("company");
        String time = request.getParameter("time");
        String state = request.getParameter("state");

        State flightState = switch (state) {
            case "Arriving" -> Arriving.getIntance();
            case "Boarding" -> Boarding.getIntance();
            case "TakingOff" -> TakingOff.getIntance();
            case "TookOff" -> TookOff.getIntance();
            default -> throw new IllegalArgumentException("Invalid flight state");
        };

        FlightData flight = new FlightData(number, company, time);
        flight.setState(flightState);
        return flight;
    }

    private List<FlightData> filterFlightsByState(String state) {
        List<FlightData> flights = flightCollection.getAllFligthts();
        List<FlightData> filtered = new ArrayList<>();

        for (FlightData flight : flights) {
            if (flight.getState().getClass().getSimpleName().equalsIgnoreCase(state)) {
            	System.out.println(flight.getState().getClass().getSimpleName());
                filtered.add(flight);
            }
        }
        return filtered;
    }

    @Override
    public void update(FlightData flight) {
        // Verificar o estado atual do voo
        String state = flight.getState().getClass().getSimpleName();

        // Recuperar as listas do contexto
        List<FlightData> arrivingFlights = (List<FlightData>) getServletContext().getAttribute("arrivingFlights");
        List<FlightData> boardingFlights = (List<FlightData>) getServletContext().getAttribute("boardingFlights");
        List<FlightData> takingOffFlights = (List<FlightData>) getServletContext().getAttribute("takingOffFlights");
        List<FlightData> tookOffFlights = (List<FlightData>) getServletContext().getAttribute("tookOffFlights");

        // Remover o voo de todas as listas
        arrivingFlights.remove(flight);
        boardingFlights.remove(flight);
        takingOffFlights.remove(flight);
        tookOffFlights.remove(flight);

        // Adicionar o voo à lista correta
        switch (state) {
            case "Arriving" -> arrivingFlights.add(flight);
            case "Boarding" -> boardingFlights.add(flight);
            case "TakingOff" -> takingOffFlights.add(flight);
            case "TookOff" -> tookOffFlights.add(flight);
        }

        // Atualizar o contexto
        getServletContext().setAttribute("arrivingFlights", arrivingFlights);
        getServletContext().setAttribute("boardingFlights", boardingFlights);
        getServletContext().setAttribute("takingOffFlights", takingOffFlights);
        getServletContext().setAttribute("tookOffFlights", tookOffFlights);
        
    }
}
