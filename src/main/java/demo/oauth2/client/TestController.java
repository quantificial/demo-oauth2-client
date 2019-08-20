package demo.oauth2.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

	@RequestMapping("/hello")
	public String Hello(HttpServletRequest request) {
		
		int maxInactiveInterval = request.getSession().getMaxInactiveInterval();
		
		long last = request.getSession().getLastAccessedTime();
		
		log.info("###################################");
		log.info(""+maxInactiveInterval);
		log.info(""+last);
		
		String s = maxInactiveInterval + " " + last;
		
		return s;
	}
}
