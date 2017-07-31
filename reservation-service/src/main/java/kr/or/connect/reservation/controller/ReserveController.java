package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.connect.reservation.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.connect.reservation.domain.ReserveInfo;
import kr.or.connect.reservation.service.ReserveService;
import kr.or.connect.reservation.service.impl.ReserveServiceImpl;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
	
	@Autowired
	ReserveService reserveServieImpl;
	
	@GetMapping("/top/{id}")
	public List<ReserveInfo> getReserveInfo(@PathVariable Integer id) {
		
		return reserveServieImpl.getReserveInfo(id);
		
	}
	
}
