package kr.or.connect.reservation.service;

import kr.or.connect.reservation.domain.User;

public interface UserService {
	Integer addUser(User user);
	User getUser(String id);
}
