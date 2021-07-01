package org.unibl.etf.mdp.railroad.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Country implements Serializable {

	private static final long serialVersionUID = -3655430467922629915L;
	
	
	private String id;
	private String name;
	private String shortName;
	
	
	public Country(String id, String name, String shortName) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
	}

	public Country(String name, String shortName) {
		super();
		this.name = name;
		this.shortName = shortName;
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
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
		if (!(obj instanceof Country)) {
			return false;
		}
		Country other = (Country) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
	
	
	
	
}
