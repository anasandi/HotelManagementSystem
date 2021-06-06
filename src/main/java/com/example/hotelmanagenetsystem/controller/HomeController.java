package com.example.hotelmanagenetsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model){
       // model.addAttribute("tagline","home Page");
        model.addAttribute("booking",model.containsAttribute("booking"));
        model.addAttribute("bookingNumber",model.containsAttribute("bookingNumber"));

        return "layout/viewlayout";
    }

}
