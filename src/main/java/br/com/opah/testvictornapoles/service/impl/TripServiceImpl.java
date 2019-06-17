package br.com.opah.testvictornapoles.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.opah.testvictornapoles.broker.client.BrokerClient;
import br.com.opah.testvictornapoles.broker.dto.Hotel;
import br.com.opah.testvictornapoles.broker.dto.Price;
import br.com.opah.testvictornapoles.broker.dto.Room;
import br.com.opah.testvictornapoles.dto.HotelResponseDto;
import br.com.opah.testvictornapoles.dto.PriceResponseDto;
import br.com.opah.testvictornapoles.dto.RoomResponseDto;
import br.com.opah.testvictornapoles.exceptions.ValidateException;
import br.com.opah.testvictornapoles.service.TripService;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private BrokerClient client;

	@Override
	public HotelResponseDto findById(Integer id) throws ValidateException {
	
		Optional<List<Hotel>> optional = client.findById(id);		
		Hotel hotel = optional.map(h -> h.get(0)).orElse(new Hotel());
		
		HotelResponseDto hotelResponseDto = getHotelResponseDto(hotel);
		calculate(1l, 1, 1, hotelResponseDto);
		return hotelResponseDto;
	}

	@Override
	public List<HotelResponseDto> findHotels(Integer cityCode, Date checkin, Date checkout, Integer adults, Integer children)
			throws ValidateException {
		Optional<List<Hotel>> optionalHotels = client.findByCity(cityCode);

		long daysDiff = getDaysDiff(checkin, checkout);
		List<Hotel> hotels = optionalHotels.orElse(Collections.emptyList());		
		List<HotelResponseDto> hotelsResponseDtos = new ArrayList<HotelResponseDto>();
		

		hotels.forEach(h -> {
			processHotel(h, daysDiff, adults, children, hotelsResponseDtos);
		});

		return hotelsResponseDtos;
	}
	
	@Async
	public void processHotel(Hotel hotel, long daysDiff, Integer adults, Integer children, List<HotelResponseDto> hotelsResponseDtos) {
	
		HotelResponseDto hotelResponseDto = getHotelResponseDto(hotel);
		calculate(daysDiff, adults, children, hotelResponseDto);
		
		hotelsResponseDtos.add(hotelResponseDto);
	}

	private void calculate(long daysDiff, Integer adults, Integer children, HotelResponseDto hotelResponseDto) {
		Optional<HotelResponseDto> optionalHotel = Optional.of(hotelResponseDto);
		List<RoomResponseDto> rooms =optionalHotel.map(HotelResponseDto::getRooms).orElse(Collections.emptyList());
		
		rooms.forEach(r -> {
			Double totalPrice = 0.0;
			PriceResponseDto price = r.getPrice();
			if(adults > 0) {
				price.setAdult(adults * price.getAdult() / 0.7 * daysDiff);
			}else {
				price.setAdult(0d);
			}
			totalPrice += price.getAdult();
			
			if(children > 0) {
				price.setChild(children * price.getChild() / 0.7 * daysDiff);
			}else {
				price.setChild(0d);
			}
			
			totalPrice += price.getChild();
			r.setTotalPrice(totalPrice);
		});
	}

	private long getDaysDiff(Date checkin, Date checkout) {
		LocalDate cIn = checkin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate cOut = checkout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return ChronoUnit.DAYS.between(cIn, cOut);
	}
	
	private HotelResponseDto getHotelResponseDto(Hotel hotel) {
		HotelResponseDto dto = new HotelResponseDto();
		List<RoomResponseDto> roomsDto = new ArrayList<>();
		
		dto.setCityName(hotel.getCityName());
		dto.setId(hotel.getId());
		
		List<Room> rooms = hotel.getRooms();
		rooms.forEach(r ->{
			Price price = r.getPrice();

			RoomResponseDto roomResponseDto = new RoomResponseDto();
			roomResponseDto.setRoomID(r.getRoomID());
			roomResponseDto.setCategoryName(r.getCategoryName());

			PriceResponseDto priceDto = new PriceResponseDto();
			priceDto.setAdult(price.getAdult());
			priceDto.setChild(price.getChild());
			
			roomResponseDto.setPrice(priceDto);
			
			roomsDto.add(roomResponseDto);
			
		});
		
		dto.setRooms(roomsDto);
	
		return dto;
	}

}
