package com.maersk.assignment.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
@Table("bookings")
public class Booking {

	@PrimaryKey
	private BookingKey bookingKey;
	
	@NotNull
	private Integer containerSize;
	
	@NotNull
	private ContainerType containerType;
	
	@NotNull
	@Size(min = 2, max = 10, message = "Origin should have size between 2 to 10 character")
	private String origin;
	
	@NotNull
	@Size(min = 2, max = 10, message = "Destination should have size between 2 to 10 character")
	private String destination;

	@NotNull
	@Range(min = 1, max = 1000, message = "Quantity should be in range 1 to 100")
	private String quantity;

	
	private Date timestamp;
	
	public Booking() {
		
	}
	public Booking(BookingKey bookingKey, @NotNull Integer containerSize, @NotNull ContainerType containerType,
			@NotNull @Size(min = 2, max = 10, message = "Origin should have size between 2 to 10 character") String origin,
			@NotNull @Size(min = 2, max = 10, message = "Destination should have size between 2 to 10 character") String destination,
			@NotNull @Range(min = 1, max = 1000, message = "Quantity should be in range 1 to 100") String quantity) {
		super();
		this.bookingKey = bookingKey;
		this.containerSize = containerSize;
		this.containerType = containerType;
		this.origin = origin;
		this.destination = destination;
		this.quantity = quantity;
		this.timestamp = new Date();
	}

	public BookingKey getBookingKey() {
		return bookingKey;
	}

	public void setBookingKey(BookingKey bookingKey) {
		this.bookingKey = bookingKey;
	}

	public Integer getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(Integer containerSize) {
		this.containerSize = containerSize;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Booking [bookingKey=" + bookingKey + ", containerSize=" + containerSize + ", containerType="
				+ containerType + ", origin=" + origin + ", destination=" + destination + ", quantity=" + quantity
				+ "]";
	}

	
}
