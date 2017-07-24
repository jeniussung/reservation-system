package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ReserveInfo;
import kr.or.connect.reservation.service.impl.ReserveServiceImpl;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
	
	@Autowired
	ReserveServiceImpl reserveServieImpl;
	
	@GetMapping("/top/{id}")
	public List<ReserveInfo> getReserveInfo(@PathVariable Integer id, HttpServletRequest request) {
		
		return reserveServieImpl.getReserveInfo(id);
		
	}
	
	@GetMapping("/middle")
	public Map<String,Object> getReserverInfo(HttpServletRequest request) {
		
		return reserveServieImpl.getInfo(request);
	}
}
