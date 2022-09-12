package com.company.socialloginwebclient.service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HomePageService {

    public String displayHomePage(Model model, OAuth2User principal){
        if (principal != null){
            String name = principal.getAttribute("name");
            model.addAttribute("name",name);
        }
        return "home";
    }
}
