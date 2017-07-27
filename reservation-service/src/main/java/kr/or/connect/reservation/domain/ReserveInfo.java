package kr.or.connect.reservation.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReserveInfo {
	private Integer productId;
	private Integer priceType;
	private Integer price;
	private Integer fileId;
	private String name;
	private String event;
	private BigDecimal discountRate;
	private String observationTime;
	private String displayStart;
	private String displayEnd;
	private String placeName;
	

}
