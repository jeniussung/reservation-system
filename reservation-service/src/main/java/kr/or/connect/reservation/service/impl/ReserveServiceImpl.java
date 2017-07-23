package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReserveDao;
import kr.or.connect.reservation.dto.ReserveInfo;
import kr.or.connect.reservation.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService{
	
	@Autowired
	ReserveDao reserveDao;
	

	@Override
	public List<ReserveInfo> getReserveInfo(Integer id) {
		// TODO Auto-generated method stub
		return reserveDao.selectReserveInfo(id);
	}

}
