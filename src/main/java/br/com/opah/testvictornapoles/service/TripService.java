package br.com.opah.testvictornapoles.service;

import java.util.Date;
import java.util.List;

import br.com.opah.testvictornapoles.dto.Hotel;
import br.com.opah.testvictornapoles.exceptions.ValidateException;

public interface TripService {
	
	public Hotel findById(Integer id);	
	
	public List<Hotel> findHotels(Integer cityCode, Date checkin, Date checkout, Integer adults, Integer children) throws ValidateException;

}
