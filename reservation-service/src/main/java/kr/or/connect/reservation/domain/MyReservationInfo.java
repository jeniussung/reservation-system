package kr.or.connect.reservation.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Date reservationDate;
    private Date createDate;
    private Date modifyDate;
}
