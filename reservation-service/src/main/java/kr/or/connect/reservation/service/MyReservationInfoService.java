package kr.or.connect.reservation.service;


import kr.or.connect.reservation.domain.MyReservationInfo;
import kr.or.connect.reservation.domain.dto.MyReservationInfoDto;

import java.util.List;

public interface MyReservationInfoService {
    List<MyReservationInfoDto> getReservationInfoList(Integer id);
    Integer updateResrvationType(MyReservationInfo myReservationInfo);
    Integer addReservationInfo(MyReservationInfo myReservationInfo);
}
