package com.maersk.assignment.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@PrimaryKeyClass
public class BookingKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.ASCENDING)
	private Integer id;

	@PrimaryKeyColumn(name = "bookingRef", ordinal = 1, ordering = Ordering.ASCENDING)
	private Integer bookingRef;

	@JsonIgnore
	@PrimaryKeyColumn(name = "sortID", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
	private Integer sortID;

	public BookingKey( Integer id,Integer bookingRef, Integer sortID) {
		super();
		this.bookingRef = bookingRef;
		this.id = id;
		this.sortID = sortID;
	}

	public Integer getSortID() {
		return sortID;
	}

	public void setSortID(Integer sortID) {
		this.sortID = sortID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(Integer bookingRef) {
		this.bookingRef = bookingRef;
	}

	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

}
