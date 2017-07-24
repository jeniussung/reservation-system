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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.reservation.dto.User;

@Service
public class NaverLoginServiceImpl {
	
	@Autowired
	User user;
	
	String GET_TOKEON_URL = "https://nid.naver.com/oauth2.0/token?client_id=ealZ_klxUlkCLBWYXd1P&client_secret=torwUYuKZq&grant_type=authorization_code&state=";
	String REMOVE_TOKEN_URL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=ealZ_klxUlkCLBWYXd1P&client_secret=torwUYuKZq&access_token=";
	String REACCESS_TOKEN_URL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&client_id=ealZ_klxUlkCLBWYXd1P&client_secret=torwUYuKZq&refresh_token=";
	String PROVIDER = "&service_provider=NAVER"; 
	
	
	public HashMap<String, Object> getAcessToken(String token, String code){
        try {
	            String apiURL = GET_TOKEON_URL+token+"&code="+code;
	            URL url = new URL(apiURL);
	            
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            
	            int responseCode = con.getResponseCode();
	            
	            BufferedReader br;
	            
	            if(responseCode==200) { // 정상 호출
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
	
	public HashMap<String, Object> reGetAcessToken(String token){
        try {
        			String accessToken = URLEncoder.encode(token, "UTF-8");
	            String apiURL = REACCESS_TOKEN_URL + accessToken;
	            URL url = new URL(apiURL);
	            
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            
	            int responseCode = con.getResponseCode();
	            
	            BufferedReader br;
	            
	            if(responseCode==200) { // 정상 호출
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
	
	public HashMap<String,String> getProfile(String token){
		String apiURL = "https://openapi.naver.com/v1/nid/me";
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            
	            con.setRequestMethod("GET");
	            con.setRequestProperty("Authorization", header);
	            
	            int responseCode = con.getResponseCode();
	            
	            BufferedReader br;
	            
	            if(responseCode==200) { // 정상 호출
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
	            
	            HashMap<String,String> profile = (HashMap<String, String>) jsonToObjectMap(response.toString()).get("response");
	            
	            return profile;
        } catch (Exception e) {
        		return null;
        }
	}
	
	public HashMap<String,Object> removeToken(String token){
        try {
        			String accessToken = URLEncoder.encode(token, "UTF-8");
        			String apiURL = REMOVE_TOKEN_URL+accessToken+PROVIDER;
	            
        			URL url = new URL(apiURL);
	            
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            
	            con.setRequestMethod("GET");
	           
	            int responseCode = con.getResponseCode();
	            
	            BufferedReader br;
	            
	            if(responseCode==200) { // 정상 호출
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
	            
	            HashMap<String,Object> profile = (HashMap<String, Object>) jsonToObjectMap(response.toString());
	            
	            return profile;
        } catch (Exception e) {
        		return null;
        }
	}
	
	 public String generateState()
	    {
	        SecureRandom random = new SecureRandom();
	        
	        return new BigInteger(130, random).toString(32);
	    }
	
	public HashMap<String, Object> jsonToMap(String json){
		
		ObjectMapper mapper = new ObjectMapper();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			return map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
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
	
	public HashMap<String, Object> jsonToObjectMap(String json){
		
		ObjectMapper mapper = new ObjectMapper();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			return map = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
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
	
	public String getCurentTime() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
		return mTime;
	}
	
	public User getUserDto(HashMap<String, String> profile) {
		user.setUsername(profile.get("name"));
		user.setAdmin_flag(1);
		user.setCreate_date(getCurentTime());
		user.setEmail(profile.get("email"));
		user.setSns_id(profile.get("id"));
		user.setModify_date(getCurentTime());
		user.setNickname(profile.get("nickname"));
		user.setSns_profile(profile.get("profile_image"));
		user.setSns_type("naver");
		user.setTel("010-010-0000");
		return user;
	}
	
}
