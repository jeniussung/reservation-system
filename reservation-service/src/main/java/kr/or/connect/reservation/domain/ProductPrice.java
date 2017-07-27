package kr.or.connect.reservation.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPrice {
    private Integer id;
    private Integer productId;
    private Integer priceType;
    private Integer price;
    private BigDecimal discountRate;
}
