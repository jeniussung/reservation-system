package kr.or.connect.reservation.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DisplayInfo {
    private Integer id;
    private Integer productId;
    private String observationTime;
    private Date displayStart;
    private Date displayEnd;
    private String placeName;
    private String placeLot;
}
