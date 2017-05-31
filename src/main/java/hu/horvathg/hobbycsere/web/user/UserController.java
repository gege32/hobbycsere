package hu.horvathg.hobbycsere.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping(value={"/profile"}, method = RequestMethod.GET)
	public ModelAndView profile(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("profile");
		return modelAndView;
	}
	
}
