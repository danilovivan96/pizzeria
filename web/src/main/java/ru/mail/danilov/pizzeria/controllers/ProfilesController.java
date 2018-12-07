package ru.mail.danilov.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ru.mail.danilov.pizzeria.service.dto.ProfileDto;

@Controller
@RequestMapping(value = "/profiles")
public class ProfilesController {

    private final PageProperties properties;
    private final UserService userService;
    private final Validator profileValidator;

    @Autowired
    public ProfilesController(PageProperties properties,
                              UserService userService,
                              @Qualifier(value = "profileValidator") Validator profileValidator) {
        this.properties = properties;
        this.userService = userService;
        this.profileValidator = profileValidator;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('CHANGE_PROFILE')")
    public String fill(ModelMap modelMap) {
        modelMap.addAttribute("profile", new ProfileDto());
        return properties.getProfilePagePath();
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('CHANGE_PROFILE')")
    public String save(
            @ModelAttribute("profile") ProfileDto profile,
            BindingResult result,
            ModelMap modelMap) {
        profileValidator.validate(profile, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("profile", profile);
            return properties.getProfilePagePath();
        } else {
            userService.saveProfile(profile);
            return "redirect:/orders/bucket";
        }
    }
}