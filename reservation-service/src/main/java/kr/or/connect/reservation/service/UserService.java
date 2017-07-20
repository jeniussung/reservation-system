package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.User;

public interface UserService {
	public long addUser(User user);
	public User getUser(String id);
}
