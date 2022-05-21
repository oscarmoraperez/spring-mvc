package org.oka.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "Oscar");
        return "greeting";
    }
}
