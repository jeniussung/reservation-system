package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ReserveInfo;
import kr.or.connect.reservation.service.impl.ReserveServiceImpl;

@RestController
@RequestMapping("/reserve/top")
public class ReserveController {
	
	@Autowired
	ReserveServiceImpl reserveServieImpl;
	
	@GetMapping("/{id}")
	public List<ReserveInfo> getReserveInfo(@PathVariable Integer id) {
		
		return reserveServieImpl.getReserveInfo(id);
		
	}
	

}
