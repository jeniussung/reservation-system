package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.connect.reservation.dto.ReserveInfo;


public interface ReserveService {
	
	public List<ReserveInfo> getReserveInfo(Integer id);
	public Map<String, Object> getInfo(HttpServletRequest request);
}
