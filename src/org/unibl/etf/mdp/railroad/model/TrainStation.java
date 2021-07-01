package org.unibl.etf.mdp.railroad.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class TrainStation implements Serializable { 
	
	private static final long serialVersionUID = 2843088438735494161L;
	
	private String id;
	private String name;
	private City city;
	
	public TrainStation(String id, String name, City city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}

	public TrainStation(String name, City city) {
		super();
		this.name = name;
		this.city = city;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TrainStation)) {
			return false;
		}
		TrainStation other = (TrainStation) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return name + " - " + city.getName() + ", " + city.getCountry().getShortName();
	}
	

}
