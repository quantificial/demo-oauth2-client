package demo.oauth2.client;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("model") ModelMap model, @Autowired Principal principal) {
		
		
		model.addAttribute("secret", "this is secret message");

		if(principal!=null) {
			model.addAttribute("username", principal.getName().toUpperCase());
			model.addAttribute("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		}
		
		
		return "index";
	}
}
