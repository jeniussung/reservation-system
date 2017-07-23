package kr.or.connect.reservation.dto;

import java.math.BigDecimal;

public class ReserveInfo {
	int productId;
	String name;
	String event; 
	int priceType;
	int price; 
	int fileId;
	BigDecimal discountRate; 
	String observationTime; 
	String displayStart; 
	String displayEnd;
	String placeName;
	
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getPriceType() {
		return priceType;
	}
	public void setPriceType(int priceType) {
		this.priceType = priceType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	public String getObservationTime() {
		return observationTime;
	}
	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}
	public String getDisplayStart() {
		return displayStart;
	}
	public void setDisplayStart(String displayStart) {
		this.displayStart = displayStart;
	}
	public String getDisplayEnd() {
		return displayEnd;
	}
	public void setDisplayEnd(String displayEnd) {
		this.displayEnd = displayEnd;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}
