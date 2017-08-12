package kr.or.connect.reservation.domain;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileDomain {
//    private Integer id;
    private Integer userId;
    private String fileName;
    private String saveFileName;
    private Long fileLength;
    private String contentType;
    private Integer deleteFlag;
    private Date createDate;
    private Date modifyDate;
}
