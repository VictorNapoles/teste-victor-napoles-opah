package br.com.opah.testvictornapoles.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.opah.testvictornapoles.dto.HotelResponseDto;
import br.com.opah.testvictornapoles.exceptions.ValidateException;
import br.com.opah.testvictornapoles.service.TripService;

@RestController
@RequestMapping("trip")
public class TripController {
	
	@Autowired
	private TripService service;

	@GetMapping
	public ResponseEntity findTrip(@RequestParam("cityCode") Integer cityCode, 
			@RequestParam("checkin") @DateTimeFormat(pattern="dd-MM-yyyy") Date checkin, 
			@RequestParam("checkout") @DateTimeFormat(pattern="dd-MM-yyyy") Date checkout, 
			@RequestParam("adults") Integer adults, 
			@RequestParam("children") Integer children){
		
		try {
			HttpStatus status = HttpStatus.OK;
			List<HotelResponseDto> findHotels = service.findHotels(cityCode, checkin, checkout, adults, children);
			
			if(findHotels == null || findHotels.isEmpty())
				status = HttpStatus.NO_CONTENT;
			
			return new ResponseEntity<List<HotelResponseDto>>(findHotels, status);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("hotel/{hotelID}")
	public ResponseEntity findHotel(@PathVariable("hotelID") Integer hotelId){
		
		try {
			HttpStatus status = HttpStatus.OK;
			HotelResponseDto hotel = service.findById(hotelId);
			
			if(hotel == null || hotel.getId() == null)
				status = HttpStatus.NO_CONTENT;
			
			return new ResponseEntity<HotelResponseDto>(hotel, status);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
}
