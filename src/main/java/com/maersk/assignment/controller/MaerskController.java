package com.maersk.assignment.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.maersk.assignment.entity.Booking;
import com.maersk.assignment.entity.BookingKey;
import com.maersk.assignment.service.BookingService;

@RestController
public class MaerskController {

	@Autowired
	public BookingService bookingService;

	@PostMapping("/api/bookings")
	public ResponseEntity<BookingKey> postBooking(@Valid @RequestBody Booking booking) throws Exception {
		
		final String uri = "http://localhost:8100/api/bookings";

	    RestTemplate restTemplate = new RestTemplate();
	     Map<String,Boolean> result = restTemplate.postForObject(uri,null, Map.class);

	    
	 if(result.values().stream().findFirst().get()) {
		 Booking newBooking = bookingService.saveOrUpdate(booking);

			return new ResponseEntity<BookingKey>(newBooking.getBookingKey(), HttpStatus.OK);
	 }
	    
	    
		return null;

	}

}
