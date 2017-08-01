package kr.or.connect.reservation.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	private Integer id;
	private Integer categoryId;
	private String name;
	private String description;
	private String placeName;
	private String salesStart;
	private String salesEnd;
	private Integer salesFlag;
	private String event;
	private String createDate;
	private String modifyDate;
	private Integer fileId;
}
