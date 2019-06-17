package br.com.opah.testvictornapoles.dto;

import java.util.List;

public class HotelResponseDto {
	private Integer id;
	private String cityName;
	private List<RoomResponseDto> rooms = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<RoomResponseDto> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomResponseDto> rooms) {
		this.rooms = rooms;
	}

}
