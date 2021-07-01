package org.unibl.etf.mdp.railroad.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class City implements Serializable {
	
	private static final long serialVersionUID = -1170337433744098842L;
	
	private String id;
	private String name;
	private Country country;
	
	
	
	public City(String id, String name, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}

	public City(String name, Country country) {
		super();
		this.name = name;
		this.country = country;
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
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
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
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", country=" + country + "]";
	}
	
	
	
	
	

}
