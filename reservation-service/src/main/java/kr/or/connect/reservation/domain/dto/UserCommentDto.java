package kr.or.connect.reservation.domain.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCommentDto {
	
	private Integer id;
	private Integer imgCount;
	private String nickname;
	private Integer productId;
	private Integer userId;
	private Integer fileId;
	private BigDecimal score;
	private String comment;
	private String createDate;
	private String modifyDate;
	
}
