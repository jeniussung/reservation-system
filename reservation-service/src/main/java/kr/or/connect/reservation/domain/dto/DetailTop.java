package kr.or.connect.reservation.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailTop {
	private	Integer fileId;
	private Integer productId;
	private Integer categoryId;
	private String name;
	private String description;
	private String salesStart;
	private String salesEnd;
	private Integer salesFlag;
	private String event;
	private String observationTime;
	private String displayStart;
	private String displayEnd;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String tel;
	private String homepage;
	private String email;
	private String saveFileName;
	

}