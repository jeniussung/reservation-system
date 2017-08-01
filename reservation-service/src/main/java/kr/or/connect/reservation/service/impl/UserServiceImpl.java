package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.domain.User;
import kr.or.connect.reservation.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public Integer addUser(User user) {
		return userDao.insert(user);
	}

	@Override
	public User getUser(String id) {
		return userDao.selectUser(id);
	}
}
