package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;
import pl.cup.russia.api.Russia2018Api.model.security.User;
import pl.cup.russia.api.Russia2018Api.util.exception.security.UserAlreadyExistAuthenticationException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ModelAndView registerUser(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView(StaticHtmlResource.HOME.getValue());

        try {
            service.registerNewUserAccount(user);
            mav.addObject("regSuccess", true);
        } catch (UserAlreadyExistAuthenticationException e) {
            mav.addObject("regSuccess", false);
            mav.addObject("errorMsg", e.getMessage());
        }

        return mav;
    }

}
