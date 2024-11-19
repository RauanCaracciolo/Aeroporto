package br.edu.ifsp.dsw1.model.observer;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public interface FlightDataObserver {

	void update(FlightData flight);
	
}
