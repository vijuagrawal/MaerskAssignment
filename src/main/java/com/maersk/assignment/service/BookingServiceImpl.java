package com.maersk.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.assignment.entity.Booking;
import com.maersk.assignment.entity.BookingKey;
import com.maersk.assignment.repo.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	

	@Override
	public Booking saveOrUpdate(Booking booking) {
		System.out.println(booking);
		Booking lastBooking = bookingRepository.findLastEntry();

		
		if (lastBooking == null) {
			booking.setBookingKey(new BookingKey(1, 957000001, 957000001));

		} else {
			Integer key= lastBooking.getBookingKey().getBookingRef();
			Integer incrementPrimaryKey = key + 1;
			booking.setBookingKey(new BookingKey(1, incrementPrimaryKey,incrementPrimaryKey));
		}
		bookingRepository.save(booking);
		return booking;
	}

	

}
