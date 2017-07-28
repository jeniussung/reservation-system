package kr.or.connect.reservation.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    Integer id;
    Integer productId;
    Integer userId;
    BigDecimal score;
    String comment;

}
