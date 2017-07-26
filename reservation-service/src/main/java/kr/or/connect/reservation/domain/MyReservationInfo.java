package kr.or.connect.reservation.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyReservationInfo {
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
}
