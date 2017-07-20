package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public long addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		return userDao.SelectUser(id);
	}
}
