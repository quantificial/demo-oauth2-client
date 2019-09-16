package demo.oauth2.client;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	@Qualifier("oauth2RestTemplate")
	private OAuth2RestTemplate oauth2RestTemplate;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("model") ModelMap model, @Autowired Principal principal) {
		
		
		model.addAttribute("secret", "this is secret message!");
		
		
		try {
		// consume remote oauth2 protected resources		
		String result = oauth2RestTemplate.getForObject("http://localhost:8089/api/policies", String.class);		
		//log.info(result);		
		model.addAttribute("policies",result);
		}catch(Exception ex) {
			log.error(ex.toString());
		}
		

		if(principal!=null) {
			model.addAttribute("username", principal.getName().toUpperCase());
			model.addAttribute("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		}
		
		
		return "index";
	}
}
