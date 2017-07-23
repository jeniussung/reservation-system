package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReserveInfo;

public interface ReserveService {
	
	public List<ReserveInfo> getReserveInfo(Integer id);

}
