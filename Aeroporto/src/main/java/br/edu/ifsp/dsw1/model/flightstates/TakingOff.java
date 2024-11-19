package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class TakingOff implements State {
	private static TakingOff instance = null;
	
	private TakingOff() { }
	
	public static TakingOff getIntance() {
		if (instance == null) {
			instance = new TakingOff();
		}
		return instance;
	}

	@Override
	public void change(FlightData flight) {
		flight.setState(TookOff.getIntance());
	}

}
