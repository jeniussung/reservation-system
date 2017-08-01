package kr.or.connect.reservation.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewInfoDto {
    Integer id;
    String name;
    BigDecimal avg;
    Integer count;
}
