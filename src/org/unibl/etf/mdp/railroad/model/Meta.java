package org.unibl.etf.mdp.railroad.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Meta implements Serializable {
	private static final long serialVersionUID = 7290073690304468078L;
	private String id;
	private String name;
	private String user;
	private String time;
	private Integer size;
	
	public Meta(String id, String name, String user, String time, Integer size) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.time = time;
		this.size = size;
	}

	public Meta(String name, String user, String time, Integer size) {
		super();
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.user = user;
		this.time = time;
		this.size = size;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
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
		if (!(obj instanceof Meta)) {
			return false;
		}
		Meta other = (Meta) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Meta [id=" + id + ", name=" + name + ", user=" + user + ", time=" + time + ", size=" + size + "]";
	}
	
	
	
	
	
	
	

}
