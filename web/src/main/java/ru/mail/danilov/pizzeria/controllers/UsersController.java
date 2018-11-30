package ru.mail.danilov.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import ru.mail.danilov.pizzeria.controllers.properties.PageProperties;
import ru.mail.danilov.pizzeria.service.UserService;
import ru.mail.danilov.pizzeria.service.dto.ChangePasswordDto;
import ru.mail.danilov.pizzeria.service.dto.RoleDto;
import ru.mail.danilov.pizzeria.service.dto.UserDto;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
    private final PageProperties properties;
    private final UserService userService;
    private final Validator userValidator;
    private final Validator userUpdateValidator;
    private final Validator passwordValidator;

    @Autowired
    public UsersController(PageProperties properties,
                           UserService userService,
                           @Qualifier(value = "userValidator") Validator userValidator,
                           @Qualifier(value = "userUpdateValidator") Validator userUpdateValidator,
                           @Qualifier(value = "passwordValidator") Validator passwordValidator) {
        this.properties = properties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.userUpdateValidator = userUpdateValidator;
        this.passwordValidator = passwordValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_USERS')")
    public String getForPage(ModelMap modelMap,
                             @RequestParam(value = "page", defaultValue = "1") Long page) {
        List<UserDto> users = userService.getForPage(page);
        modelMap.addAttribute("users", users);
        Long quantity = properties.getUsersForPageQuantity();
        Long pages = userService.countPages(quantity);
        modelMap.addAttribute("pages", pages);
        List<RoleDto> roles = userService.getRoleList();
        modelMap.addAttribute("roles", roles);
        return properties.getUsersPagePath();
    }

    @GetMapping("{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_USERS')")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("{id}/enable")
    @PreAuthorize("hasAuthority('DISABLE_USERS')")
    public String changeEnable(@PathVariable("id") Long id) {
        userService.enable(id);
        return "redirect:/users";
    }

    @GetMapping("{id}/updating")
    @PreAuthorize("hasAuthority('SHOW_USERS')")
    public String update(@PathVariable("id") Long id,
                         ModelMap modelMap) {
        UserDto user = userService.getById(id);
        modelMap.addAttribute("user", user);
        return properties.getUsersUpdatePagePath();
    }

    @PostMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('SECURITY_USER_PERMISSION')")
    public String updateItem(@PathVariable("id") Long id,
                             @ModelAttribute UserDto user,
                             BindingResult result,
                             ModelMap modelMap) {
        user.setId(id);
        userUpdateValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return properties.getUsersUpdatePagePath();
        } else {
            userService.update(user);
            modelMap.addAttribute("success", "User updated!");
            modelMap.addAttribute("user", user);
            return properties.getUsersUpdatePagePath();
        }
    }

    @GetMapping("{id}/password")
    @PreAuthorize("hasAuthority('CHANGE_USERS_PASSWORD')")
    public String passwordPage(@PathVariable("id") Long user,
                               ModelMap modelMap) {
        ChangePasswordDto password = new ChangePasswordDto();
        password.setUser(user);
        modelMap.addAttribute("password", password);
        return properties.getPasswordPagePath();
    }

    @GetMapping("{id}/role/update")
    @PreAuthorize("hasAuthority('CHANGE_USERS_ROLE')")
    public String passwordPage(@PathVariable("id") Long user,
                               @RequestParam("new_role") Long role) {
        userService.changeRole(user, role);
        return "redirect:/users";
    }

    @PostMapping("{id}/password/change")
    @PreAuthorize("hasAuthority('CHANGE_USERS_ROLE')")
    public String changePassword(@PathVariable("id") Long user,
                                 @ModelAttribute("password") ChangePasswordDto passwordDto,
                                 BindingResult result,
                                 ModelMap modelMap) {
        passwordValidator.validate(passwordDto, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("password", passwordDto);
            return properties.getPasswordPagePath();
        } else {
            passwordDto.setUser(user);
            userService.changePassword(passwordDto);
            UserDto userDto = userService.getById(user);
            modelMap.addAttribute("user", userDto);
            modelMap.addAttribute("success", "Password changed!");
            return properties.getUsersUpdatePagePath();
        }
    }
}
