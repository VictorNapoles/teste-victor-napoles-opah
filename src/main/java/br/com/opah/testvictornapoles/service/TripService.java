package br.com.opah.testvictornapoles.service;

import java.util.Date;
import java.util.List;

import br.com.opah.testvictornapoles.dto.HotelResponseDto;
import br.com.opah.testvictornapoles.exceptions.ValidateException;

public interface TripService {
	
	public HotelResponseDto findById(Integer id) throws ValidateException;	
	
	public List<HotelResponseDto> findHotels(Integer cityCode, Date checkin, Date checkout, Integer adults, Integer children) throws ValidateException;

}
