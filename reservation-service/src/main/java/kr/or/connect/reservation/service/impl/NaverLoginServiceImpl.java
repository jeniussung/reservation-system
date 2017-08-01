package kr.or.connect.reservation.service.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.reservation.domain.User;
import kr.or.connect.reservation.service.LoginService;

@Service
public class NaverLoginServiceImpl implements LoginService{

	@Value("${open-api.naver.client-id}")
	private String clientId;
	@Value("${open-api.naver.client-secret}")
	private String clientSecret;
	
    private String REACCESS_TOKEN_URL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&client_id="+ clientId +"&client_secret="+ clientSecret +"&refresh_token=";
    private String PROVIDER = "&service_provider=NAVER";


    public Map<String, Object> getAcessToken(String token, String code) {
    	String tokenGetUrl = "https://nid.naver.com/oauth2.0/token?client_id="+ clientId +"&client_secret="+ clientSecret +"&grant_type=authorization_code&state=";
        try {
            String apiURL = tokenGetUrl + token + "&code=" + code;
            URL url = new URL(apiURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            return jsonToMap(response.toString());
        } catch (Exception e) {
            return null;
        }
    }


    public Map<String, String> getProfile(String token) {
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);

            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            Map<String, String> profile = (Map<String, String>) jsonToMap(response.toString()).get("response");

            return profile;
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> removeToken(String token) {
    	String tokenRemoveUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id="+ clientId +"&client_secret="+ clientSecret +"&access_token=";
        try {
            String accessToken = URLEncoder.encode(token, "UTF-8");
            String apiURL = tokenRemoveUrl + accessToken + PROVIDER;

            URL url = new URL(apiURL);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            Map<String, Object> profile =  jsonToMap(response.toString());

            return profile;
        } catch (Exception e) {
            return null;
        }
    }

    public String generateState() {
        SecureRandom random = new SecureRandom();

        return new BigInteger(130, random).toString(32);
    }


    private Map<String, Object> jsonToMap(String json) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();

        try {
            return map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    private String getCurrentTime() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format(currentTime);
        return mTime;
    }

    public User getUserDto(Map<String, String> profile) {
        User user = new User();
        user.setUsername(profile.get("name"));
        user.setAdmin_flag(1);
        user.setCreate_date(getCurrentTime());
        user.setEmail(profile.get("email"));
        user.setSns_id(profile.get("id"));
        user.setModify_date(getCurrentTime());
        user.setNickname(profile.get("nickname"));
        user.setSns_profile(profile.get("profile_image"));
        user.setSns_type("naver");
        user.setTel("010-010-0000");
        return user;
    }
}
