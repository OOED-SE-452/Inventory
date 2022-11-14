package se452.project.grocery.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WhiteErrorPageController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // do something like logging
        return "redirect:/";
    }
}