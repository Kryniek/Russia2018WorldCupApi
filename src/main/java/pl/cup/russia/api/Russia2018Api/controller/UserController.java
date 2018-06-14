package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;
import pl.cup.russia.api.Russia2018Api.model.security.User;
import pl.cup.russia.api.Russia2018Api.util.exception.security.UserAlreadyExistAuthenticationException;

import static java.time.LocalDate.now;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private MatchService matchService;

	@PostMapping
	public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		try {
			service.registerNewUserAccount(user);
			redirectAttributes.addAttribute("regSuccess", true);
		} catch (UserAlreadyExistAuthenticationException e) {
			redirectAttributes.addAttribute("regSuccess", false);
			redirectAttributes.addAttribute("errorMsg", e.getMessage());
		}

		redirectAttributes.addAttribute("todayMatches", matchService.selectMatchesByDate(now()));

		return StaticHtmlResource.HOME.getKebabCasedRedirectValue();
	}

	@PutMapping("/{username}/paid")
	@ResponseBody
	public Integer changeUserPaidStatus(@PathVariable String username) {
		return service.setUserPaidStatusToTrue(username);
	}

}
