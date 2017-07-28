package kr.or.connect.reservation.service;

import kr.or.connect.reservation.domain.User;

public interface UserService {
	public Integer addUser(User user);
	public User getUser(String id);
}
