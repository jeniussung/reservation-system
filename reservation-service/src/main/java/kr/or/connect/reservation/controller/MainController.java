package kr.or.connect.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.connect.reservation.service.LoginService;
import kr.or.connect.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.connect.reservation.domain.User;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;


    private String NAVER_LOGIN_URL = "https://nid.naver.com/oauth2.0/authorize?client_id=ealZ_klxUlkCLBWYXd1P&response_type=code&redirect_uri=http";

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

        session.setAttribute("state", state);

        try {
            String encodeURL = URLEncoder.encode("://localhost:8080/callback", "UTF-8");

            return "redirect:" + NAVER_LOGIN_URL + encodeURL + "&state=" + state + "&auth_type=reauthenticate";
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

            if (userProfile == null) {
                userProfile = loginService.getUserDto(profile);
                Integer id = userService.addUser(userProfile);
                userProfile.setId(id);
            }

            session.setAttribute("user_id", profile.get("id"));
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


}
