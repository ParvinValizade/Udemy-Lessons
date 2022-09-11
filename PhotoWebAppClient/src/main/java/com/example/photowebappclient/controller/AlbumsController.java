package com.example.photowebappclient.controller;

import com.example.photowebappclient.service.AlbumsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlbumsController {

    private final AlbumsService service;

    public AlbumsController(AlbumsService service) {
        this.service = service;
    }

    @GetMapping("/albums")
    public String getAllAlbums(Model model, @AuthenticationPrincipal OidcUser principal){
        return service.getAllAlbums(model,principal);
    }
}
