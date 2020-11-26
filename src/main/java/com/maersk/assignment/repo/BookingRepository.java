package com.maersk.assignment.repo;



import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.maersk.assignment.entity.Booking;

public interface BookingRepository extends CassandraRepository<Booking, UUID> { 

	@Query("select * from bookings where id = 1 ORDER BY sortID  DESC LIMIT 1 ALLOW FILTERING")
	public Booking findLastEntry();
}

