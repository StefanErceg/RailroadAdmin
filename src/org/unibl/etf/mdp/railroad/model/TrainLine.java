package org.unibl.etf.mdp.railroad.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class TrainLine implements Serializable {

	private static final long serialVersionUID = 8491674044889384786L;
	
	private String id;
	private TrainStation start;
	private TrainStation destination;
	private String startTime;
	private String arrivalTime;
	private ArrayList<TrainStop> stops;
	
	public TrainLine(String id, TrainStation start, TrainStation destination, String startTime, String arrivalTime,
			ArrayList<TrainStop> stops) {
		super();
		this.id = id;
		this.start = start;
		this.destination = destination;
		this.startTime =startTime;
		this.arrivalTime =arrivalTime;
		this.stops = stops;
	}

	public TrainLine(TrainStation start, TrainStation destination, String startTime, String arrivalTime,
			ArrayList<TrainStop> stops) {
		super();
		this.start = start;
		this.destination = destination;
		this.startTime = startTime;
		this.arrivalTime = arrivalTime;
		this.stops = stops;
	}

	/**
	 * @return the start
	 */
	public TrainStation getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(TrainStation start) {
		this.start = start;
	}

	/**
	 * @return the destination
	 */
	public TrainStation getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(TrainStation destination) {
		this.destination = destination;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime =  startTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the stops
	 */
	public ArrayList<TrainStop> getStops() {
		return stops;
	}

	/**
	 * @param stops the stops to set
	 */
	public void setStops(ArrayList<TrainStop> stops) {
		this.stops = stops;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
		if (!(obj instanceof TrainLine)) {
			return false;
		}
		TrainLine other = (TrainLine) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TrainLine [id=" + id + ", start=" + start + ", destination=" + destination + ", startTime=" + startTime
				+ ", arrivalTime=" + arrivalTime + ", stops=" + stops + "]";
	}
	
	
	
	
	
	
	
	
}
