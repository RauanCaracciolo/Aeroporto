package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class Boarding implements State {
	private static Boarding instance = null;
	
	private Boarding() { }
	
	public static Boarding getIntance() {
		if (instance == null) {
			instance = new Boarding();
		}
		return instance;
	}

	@Override
	public void change(FlightData flight) {
		flight.setState(TakingOff.getIntance());
	}
}
