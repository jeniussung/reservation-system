package kr.or.connect.reservation.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyReservationInfoDto {
    private Integer id;
    private Integer productId;
    private Integer userId;
    private Integer generalTicketCount;
    private Integer youthTicketCount;
    private Integer childTicketCount;
    private Integer reservationType;
    private String reservationName;
    private String reservationTel;
    private String reservationEmail;
    private String observationTime;
    private String displayStart;
    private String displayEnd;
    private String placeName;
    private String name;
    private Long totalPrice;
}
