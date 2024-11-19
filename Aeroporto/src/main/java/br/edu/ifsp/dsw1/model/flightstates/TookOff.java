package br.edu.ifsp.dsw1.model.flightstates;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class TookOff implements State {
	private static TookOff instance = null;
	
	private TookOff() { }
	
	public static TookOff getIntance() {
		if (instance == null) {
			instance = new TookOff();
		}
		return instance;
	}

	@Override
	public void change(FlightData flight) {
		
	}

}
