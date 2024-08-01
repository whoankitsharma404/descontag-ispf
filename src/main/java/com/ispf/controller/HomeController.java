package com.ispf.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ispf.repositories.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ispf.model.Contact;

@Controller
public class HomeController {

    @Value("${api.url.contactUs}")
    String contactUsURL;

    @Value("${api.contactUs.portalname}")
    String portalName;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    boolean status = false;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/home")
    public String openHome(Model model) {
        model.addAttribute("contact", new Contact());
        logger.info("home action called");
        return "home";
    }

    @PostMapping("/redirecthome")
    public ModelAndView subscribeuser() {
        logger.info("The user have been subscribed");
        return new ModelAndView("redirect:/home");
    }

    @PostMapping(value = "/saveContact")
    public ModelAndView saveContact(@ModelAttribute("contact") Contact contact) {
		contactRepository.save(contact);
		return new ModelAndView("redirect:/home");
    }

    @GetMapping(value = "/")
    public String material(Model model) {
        // model.addAttribute("contact", new Contact());
        logger.info("material Action called");
        return "material";
    }

}
