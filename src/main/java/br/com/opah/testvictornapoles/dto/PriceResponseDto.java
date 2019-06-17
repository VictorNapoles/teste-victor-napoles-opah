package br.com.opah.testvictornapoles.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceResponseDto {
	
	@JsonProperty("pricePerDayAdult")
	private Double adult;
	
	@JsonProperty("pricePerDayChild")
	private Double child;

	public Double getAdult() {
		return adult;
	}

	public void setAdult(Double adult) {
		this.adult = adult;
	}

	public Double getChild() {
		return child;
	}

	public void setChild(Double child) {
		this.child = child;
	}

}
