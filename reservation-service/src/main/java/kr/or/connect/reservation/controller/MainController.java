package kr.or.connect.reservation.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.ImgFile;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.service.impl.DetailServiceImpl;
import kr.or.connect.reservation.service.impl.NaverLoginServiceImpl;
import kr.or.connect.reservation.service.impl.UserServiceImpl;

	@Controller
	@RequestMapping("/")
	public class MainController {
		@Autowired
		DetailServiceImpl detailServiceImpl;
		
		@Autowired
		NaverLoginServiceImpl naverLoginServiceImpl;
		
		@Autowired
		UserServiceImpl userServiceImpl;
		
		private String baseDir = "/Users/seongjihyeon/Desktop" + File.separator + "serverfile" + File.separator;
		private String NAVER_LOGIN_URL = "https://nid.naver.com/oauth2.0/authorize?client_id=ealZ_klxUlkCLBWYXd1P&response_type=code&redirect_uri=http";
		
		@GetMapping("index")
	    public String index(){
	        return "index";
	    }
		
	    @GetMapping
	    public String mainPage(){
	        return "mainpage";
	    }
	    
	    @GetMapping("details/{id}")
	    public String detailPage(){
	        return "detail";
	    }
	    
	    @GetMapping("nlogin")
	    public String goNlogin(HttpServletRequest request){
	    	
	    		String state = naverLoginServiceImpl.generateState();
	    		
		    	HttpSession session = request.getSession();
		    	
	    	    	session.setAttribute("state", state);
	    	    	
	    	    	try {
	    	    			 String encodeURL = URLEncoder.encode("://localhost:8080/callback", "UTF-8");
	    	    			 
					 return "redirect:"+NAVER_LOGIN_URL +encodeURL+"&state="+state+"&auth_type=reauthenticate";
				} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						return "redirect:/nlogin";
				}
	    }
	    
	    @GetMapping("callback")
	    public String callback(HttpServletRequest request){
	    	
		    	String state = request.getParameter("state"); 
	    		String code = request.getParameter("code");
	    		
	    		HttpSession session = request.getSession();
	    		String storedState = (String) session.getAttribute("state");
	    		
	    		String url = (String)session.getAttribute("URL");		
	    		
	    		if( !state.equals( storedState ) ) {
	    			System.out.println("fail");
	    		    return "mainpage"; //401 unauthorized
	    		} else {
	    			System.out.println("ok");
	    			HashMap<String,Object> accessResult = naverLoginServiceImpl.getAcessToken(state, code);
		    		HashMap<String, String> profile = naverLoginServiceImpl.getProfile((String) accessResult.get("access_token"));
		    		
		    		if(profile == null) // 회원정보 받아오지 못하면 로그인 페이지로 리다이렉트, 접근 토큰은 1회성이기 때문.
		    		{
		    			return "redirect:/nlogin";
		    		}
		    		
//		    		System.out.println(profile);
		    		
		    		User user = userServiceImpl.getUser(profile.get("id"));
		    		
		    		if(user == null) {
		    			User userProfile = naverLoginServiceImpl.getUserDto(profile);
		    			userServiceImpl.addUser(userProfile); // 디비에 저장
		    		}
		    		
		    		session.setAttribute("user_id", profile.get("id")); // 아이디 세션에 저장
		    		session.setAttribute("name", profile.get("name"));
		    		session.setAttribute("email", profile.get("email"));
		    		
		    		return url;
	    		}
	    		
	    }
	    
	    @GetMapping("logout")
	    public String logout(HttpServletRequest request) {
	    		
	    		HttpSession session = request.getSession();
	    		
	    		session.removeAttribute("user_id");
	    		session.removeAttribute("name");
	    		session.removeAttribute("email");
	    		
	    		return "mainpage";
	    }
	    
	
	    @GetMapping("reviews")
	    public String getReview() {
	       
	    	   return "review";
	   }
	    
	    @GetMapping("reserve/{id}")
	    public String getReserve() {
	       
	    	   return "reserve";
	   }
	    
	    @GetMapping("myreservation")
	    public String getReserve(HttpServletRequest request) {
	    	
	    		return "myreservation";
	   }
        
        @GetMapping("files")
        public String fileform(){
            return "files";
        }

        @PostMapping("files")
        public String create(@RequestParam("title") String title, @RequestParam("file") MultipartFile[] files){

            if(files != null && files.length > 0){

                // windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.
                String formattedDate = baseDir + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
                File f = new File(formattedDate);
                if(!f.exists()){ // 파일이 존재하지 않는다면
                    f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
                }

                for(MultipartFile file : files) {
                    String contentType = file.getContentType();
                    String name = file.getName();
                    String originalFilename = file.getOriginalFilename();
                    long size = file.getSize();

                    String uuid = UUID.randomUUID().toString(); // 중복될 일이 거의 없다.
                    String saveFileName = formattedDate + File.separator + uuid; // 실제 저장되는 파일의 절대 경로

                    // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
                    // pk 값은 자동으로 생성되도록 한다.
                    System.out.println("title :" + title);
                    System.out.println("contentType :" + contentType);
                    System.out.println("name :" + name);
                    System.out.println("originalFilename : " + originalFilename);
                    System.out.println("size : " + size);
                    System.out.println("saveFileName : " + saveFileName);

                    // 실제 파일을 저장함.
                    // try-with-resource 구문. close()를 할 필요가 없다. java 7 이상에서 가능
                    try(
                            InputStream in = file.getInputStream();
                            FileOutputStream fos = new FileOutputStream(saveFileName)){
                        int readCount = 0;
                        byte[] buffer = new byte[512];
                        while((readCount = in.read(buffer)) != -1){
                            fos.write(buffer,0,readCount);
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                } // for
            } // if

            return "redirect:/files";
        }
        
        @GetMapping("files/{id}")
        public void downloadReservationUserCommentImage(@PathVariable(name="id") Integer id, HttpServletResponse response){
            // id를 이용하여 파일의 정보를 읽어온다.
            // 이 부분은 원래 db에서 읽어오는 것인데 db부분은 생략했다.
        		ImgFile addr = detailServiceImpl.getFileAddr(id);
        		
            String originalFilename = "원본파일명";
            String contentType = "image/jpeg";
            int fileSize = 271621;
            // 해당 파일이 이미 존재해야한다.
            String saveFileName = "/Users/seongjihyeon/Desktop/serverfile/"+addr.getSave_file_name();

            response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Type", contentType);
            //response.setHeader("Content-Length", ""+ fileSize);
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");

            java.io.File readFile = new java.io.File(saveFileName);
            if(!readFile.exists()){ // 파일이 존재하지 않다면
                throw new RuntimeException("file not found");
            }

            FileInputStream fis = null;
            try{
                fis = new FileInputStream(readFile);
                FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
                response.getOutputStream().flush();
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }finally {
                try {
                    fis.close();
                }catch(Exception ex){
                    // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
                }
            }

        }
	    
	    @GetMapping("test")
	    public String test(){
	        return "test2";
	    }
	    
	    @GetMapping("map")
	    public String testMap(){
	    	
	        return "testmap";
	     
	    }
	}
