package br.com.opah.testvictornapoles.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomResponseDto {
	private Integer roomID;
	private String categoryName;
	private Double totalPrice;
	@JsonProperty("priceDetail")
	private PriceResponseDto price;

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PriceResponseDto getPrice() {
		return price;
	}

	public void setPrice(PriceResponseDto price) {
		this.price = price;
	}
}
