package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.connect.reservation.domain.ReserveInfo;


public interface ReserveService {
	List<ReserveInfo> getReserveInfo(Integer id);
	Map<String, Object> getInfo(HttpServletRequest request);

}
