package org.unibl.etf.mdp.railroad.model;

import java.io.Serializable;
import java.util.Objects;

public class Report implements Serializable {

	private static final long serialVersionUID = -202684413724052606L;
	private byte[] data;
	private Meta meta;
	public Report(byte[] data, Meta meta) {
		super();
		this.data = data;
		this.meta = meta;
	}
	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	/**
	 * @return the meta
	 */
	public Meta getMeta() {
		return meta;
	}
	/**
	 * @param meta the meta to set
	 */
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	@Override
	public int hashCode() {
		return Objects.hash(meta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Report)) {
			return false;
		}
		Report other = (Report) obj;
		return Objects.equals(meta, other.meta);
	}
	
	

}
