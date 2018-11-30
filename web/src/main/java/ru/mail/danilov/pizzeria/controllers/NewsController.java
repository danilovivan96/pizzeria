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
import ru.mail.danilov.pizzeria.service.CommentService;
import ru.mail.danilov.pizzeria.service.NewsService;
import ru.mail.danilov.pizzeria.service.dto.CommentDto;
import ru.mail.danilov.pizzeria.service.dto.NewsDto;

import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
    private final PageProperties properties;
    private final NewsService newsService;
    private final Validator newsValidator;
    private final CommentService commentService;
    private final Validator commentValidator;

    @Autowired
    public NewsController(PageProperties properties,
                          NewsService newsService,
                          CommentService commentService,
                          @Qualifier(value = "newsValidator") Validator newsValidator,
                          @Qualifier(value = "commentValidator") Validator commentValidator) {
        this.properties = properties;
        this.newsService = newsService;
        this.newsValidator = newsValidator;
        this.commentService = commentService;
        this.commentValidator = commentValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_NEWS')")
    public String show(ModelMap modelMap,
                       @RequestParam(value = "page", defaultValue = "1") Long page) {
        List<NewsDto> list = newsService.getForPage(page);
        Long quantity = properties.getNewsForPageQuantity();
        Long pages = newsService.countPages(quantity);
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("pages", pages);
        return properties.getNewsPagePath();
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String creating(ModelMap modelMap) {
        NewsDto news = new NewsDto();
        modelMap.addAttribute("news", news);
        return properties.getNewsCreatePagePath();
    }

    @PostMapping("/{id}/comments/add")
    @PreAuthorize("hasAuthority('CUSTOMER_PERMISSION')")
    public String addComment(@PathVariable("id") Long news,
                             @RequestAttribute("newComment") CommentDto comment,
                             BindingResult result,
                             ModelMap modelMap) {
        commentValidator.validate(comment, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("message", "Invalid comment");
            return properties.getNewsCreatePagePath();
        } else {
            commentService.save(comment, news);
            return "redirect:/news/" + news + "/comments";
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String create(
            @ModelAttribute NewsDto news,
            BindingResult result,
            ModelMap modelMap) {
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return properties.getNewsCreatePagePath();
        } else {
            newsService.create(news);
            return "redirect:/news";
        }
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_NEWS')")
    public String delete(@PathVariable("id") Long id) {
        newsService.delete(id);
        return "redirect:/news";
    }

    @GetMapping("/{id}/comments")
    @PreAuthorize("hasAuthority('SHOW_NEWS')")
    public String showComments(@PathVariable("id") Long id,
                               ModelMap modelMap) {
        List<CommentDto> comments = newsService.getComments(id);
        modelMap.addAttribute("news", id);
        modelMap.addAttribute("comments", comments);
        CommentDto comment = new CommentDto();
        modelMap.addAttribute("newComment", comment);
        return properties.getCommentsPagePath();
    }
}
