package br.edu.ifsp.dsw1.model.observer;

public interface FlightDataSubject {

	void register(FlightDataObserver observer);
	
	void unregister(FlightDataObserver observer);
	
	void notifyObservers();
}
