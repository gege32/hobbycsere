package hu.horvathg.hobbycsere.web.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hu.horvathg.hobbycsere.model.user.User;
import hu.horvathg.hobbycsere.model.user.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
    private UserRepository userRepository;
	
	@RequestMapping(value={"/home", "/index"}, method = RequestMethod.GET)
	public ModelAndView home(){
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User customUser = userRepository.findByEmail(user.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", customUser);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
}
