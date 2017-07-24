package kr.or.connect.reservation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


	@Override
	public Map<String, Object> getInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HashMap<String,Object> info = new HashMap<String,Object>();
		
		if (session.getAttribute("user_id") != null) {
			info.put("name", session.getAttribute("name"));
			info.put("email", session.getAttribute("email"));
			
			return info;
				
		} else {
			
			return null;
		}
	}
	
	

}
