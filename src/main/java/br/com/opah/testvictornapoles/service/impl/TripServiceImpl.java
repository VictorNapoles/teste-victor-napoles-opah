package br.com.opah.testvictornapoles.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.opah.testvictornapoles.client.BrokerClient;
import br.com.opah.testvictornapoles.dto.Hotel;
import br.com.opah.testvictornapoles.exceptions.ValidateException;
import br.com.opah.testvictornapoles.service.TripService;

@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
	private BrokerClient client;
	
	@Override
	public Hotel findById(Integer id) {
		Optional<List<Hotel>> optional = client.findById(id);
		
		return optional
				.map(h -> h.get(0))
				.orElse(null);
	}

	@Override
	public List<Hotel> findHotels(Integer cityCode, Date checkin, Date checkout, Integer adults, Integer children) throws ValidateException {
		Optional<List<Hotel>> optionalHotels = client.findByCity(cityCode);
		validate(checkin, checkout, adults, children);
		
		if(optionalHotels.isPresent()) {
			
			long daysDiff = getDaysDiff(checkin, checkout);			
			List<Hotel> hotels = optionalHotels.get();			
			
			hotels.forEach(h -> {
				
			});
		}
		
		return Collections.EMPTY_LIST;
	}

	private long getDaysDiff(Date checkin, Date checkout) {
		LocalDate cIn = checkin.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		LocalDate cOut = checkout.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		return ChronoUnit.DAYS.between(cIn, cOut);
	}

	private void validate(Date checkin, Date checkout, Integer adults, Integer children) throws ValidateException {
		Optional<Date> optionalCheckin = Optional.ofNullable(checkin);
		Optional<Date> optionalCheckout = Optional.ofNullable(checkout);
		Optional<Integer> optionalAdults = Optional.ofNullable(adults);
		Optional<Integer> optionalChildren = Optional.ofNullable(children);
		
		optionalCheckin.orElseThrow(() -> new ValidateException("Check-in date is required"));
		optionalCheckout.orElseThrow(() -> new ValidateException("Check-out date is required"));
		optionalAdults.orElseThrow(() -> new ValidateException("Number of adults is required"));
		optionalChildren.orElseThrow(() -> new ValidateException("Number of children is required"));
	}

}
