package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.domain.MyReservationInfo;
import kr.or.connect.reservation.domain.dto.MyReservationInfoDto;
import kr.or.connect.reservation.service.MyReservationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/myreservations")
public class MyReservationController {
    @Autowired
    MyReservationInfoService myReservationInfoService;

    @GetMapping("/{id}")
    public List<MyReservationInfoDto> getMyReservationInfo(@PathVariable Integer id) {

        return myReservationInfoService.getReservationInfoList(id);
    }

    @PutMapping
    public Integer updateReservationType(@RequestBody MyReservationInfo myReservationInfo) {

        return myReservationInfoService.updateResrvationType(myReservationInfo);

    }
    
    @PostMapping
    public Integer create(@RequestBody MyReservationInfo myReservationInfo){
    	
    	return myReservationInfoService.addReservationInfo(myReservationInfo);
    	
    }
}
