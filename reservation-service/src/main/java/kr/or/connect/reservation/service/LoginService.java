package kr.or.connect.reservation.service;

import kr.or.connect.reservation.domain.User;

import java.util.Map;

public interface LoginService {
    Map<String, Object> getAcessToken(String token, String code);
    Map<String, String> getProfile(String token);
    Map<String, Object> removeToken(String token);
    String generateState();
    User getUserDto(Map<String, String> profile);
}
