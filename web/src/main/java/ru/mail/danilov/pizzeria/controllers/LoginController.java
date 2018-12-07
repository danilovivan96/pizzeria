package ru.mail.danilov.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mail.danilov.pizzeria.controllers.properties.PageProperties;
import ru.mail.danilov.pizzeria.service.UserService;
import ru.mail.danilov.pizzeria.service.dto.UserDto;

@Controller
@RequestMapping("")
public class LoginController {

    private final PageProperties properties;
    private final UserService userService;
    private final Validator userValidator;

    @Autowired
    public LoginController(PageProperties properties,
                           UserService userService,
                           @Qualifier(value = "userValidator") Validator userValidator) {
        this.properties = properties;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String login() {
        return properties.getLoginPagePath();
    }

    @GetMapping("/registration")
    public String registration(ModelMap modelMap) {
        UserDto user = new UserDto();
        modelMap.addAttribute("user", user);
        return properties.getRegistrationPagePath();
    }

    @PostMapping("/registration")
    public String register(
            @ModelAttribute("user") UserDto user,
            BindingResult result,
            ModelMap modelMap) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return properties.getRegistrationPagePath();
        } else {
            userService.register(user);
            modelMap.addAttribute("message", "Success! Now you may Log in.");
            return properties.getLoginPagePath();
        }
    }
}
