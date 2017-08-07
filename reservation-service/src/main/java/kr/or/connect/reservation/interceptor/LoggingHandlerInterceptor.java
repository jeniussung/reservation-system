package kr.or.connect.reservation.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingHandlerInterceptor extends HandlerInterceptorAdapter {
    // Logger객체를 선언
    private static Logger logger = LoggerFactory.getLogger(LoggingHandlerInterceptor.class);
    private static final String ATTRIBUTE_BEGIN_TIME = "ATTR_BEGIN_TIME";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long currentTime = System.currentTimeMillis();
        request.setAttribute(ATTRIBUTE_BEGIN_TIME, currentTime);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long beginTime = (long) request.getAttribute(ATTRIBUTE_BEGIN_TIME);
        String uri = request.getRequestURI();
        String method = request.getMethod();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(method).append("] ");
        stringBuilder.append(uri).append(" ");
        stringBuilder.append(System.currentTimeMillis() - beginTime);
        stringBuilder.append(" ms");

        // 로그 남기기 '+'를 이용하여 문자열을 생성하는 것은 퍼포먼스에 문제를 발생시킨다. {}를 이용하여 파라미터의 값을 출력할 수 있다.
        logger.info("메소드 실행 시간 {}", stringBuilder.toString());
        //System.out.println(stringBuilder.toString());

        super.postHandle(request, response, handler, modelAndView);
    }
}

