package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public interface State {
	
	void change(FlightData flight);
	
}
