package kr.or.connect.reservation.connect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


//서비스 단에서 필요한 공장 정보를 알려줄 때
@Configuration
@ComponentScan(basePackages = {
		  "kr.or.connect.reservation.dao",
		  "kr.or.connect.reservation.service",
		  "kr.or.connect.reservation.dto",
		  "kr.or.connect.reservation.interceptor"
})

@Import({Dbconfig.class})
public class RootApplicationContextConfig{

}
