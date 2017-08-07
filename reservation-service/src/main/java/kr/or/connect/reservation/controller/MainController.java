package kr.or.connect.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.connect.reservation.annotation.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.connect.reservation.domain.User;
import kr.or.connect.reservation.service.LoginService;
import kr.or.connect.reservation.service.UserService;

@PropertySource("classpath:/application.properties")
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Value("${open-api.naver.client-id}")
    private String clientId;
    @Value("${open-api.naver.callback-url}")
    private String callbackUrl;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("details/{id}")
    public String detailPage() {
        return "detail";
    }

    @GetMapping("nlogin")
    public String goNlogin(HttpServletRequest request) {

        String state = loginService.generateState();
        HttpSession session = request.getSession();
        String naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize?client_id="+ clientId +"&response_type=code&redirect_uri=http";

        session.setAttribute("state", state);

        try {
            String encodeURL = URLEncoder.encode(callbackUrl, "UTF-8");

            return "redirect:" + naverLoginUrl + encodeURL + "&state=" + state + "&auth_type=reauthenticate";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "redirect:/nlogin";
        }
    }

    @GetMapping("callback")
    public String callback(HttpServletRequest request) {

        String state = request.getParameter("state");
        String code = request.getParameter("code");

        HttpSession session = request.getSession();
        String storedState = (String) session.getAttribute("state");

        String url = (String) session.getAttribute("URL");

        if (!state.equals(storedState)) {
            return "mainpage";
        } else {
            Map<String, Object> accessResult = loginService.getAcessToken(state, code);
            Map<String, String> profile = loginService.getProfile((String) accessResult.get("access_token"));

            if (profile == null) {
                return "redirect:/nlogin";
            }

            User userProfile = userService.getUser((String) profile.get("id"));
            System.out.println(userProfile);

            if (userProfile == null) {
                userProfile = loginService.getUserDto(profile);
                Integer id = userService.addUser(userProfile);
                userProfile.setId(id);
            }

            session.setAttribute("user", userProfile);

            return "redirect:"+url;
        }

    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();

        session.removeAttribute("user");

        return "mainpage";
    }


    @GetMapping("api/reviews")
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
    
    @GetMapping("reviewWrite/{id}")	//productId
    public String getreviewWrite(HttpServletRequest request) {

        return "reviewWrite";
    }

    @GetMapping("argument")	// argumentResorver test
    public String argument(@AuthUser String value) {

        System.out.println("----------------------------------------------------------");
        System.out.println("ArgumentResolver에서 넘긴 이름 : " + value);
        System.out.println("----------------------------------------------------------");

        return "mainpage";
    }


}
