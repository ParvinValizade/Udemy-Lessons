package com.example.photowebappclient.service;

import com.example.photowebappclient.dto.AlbumDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AlbumsService {

    private  final OAuth2AuthorizedClientService oAuth2ClientService;
    private final WebClient webClient;

    public AlbumsService(OAuth2AuthorizedClientService oAuth2ClientService, WebClient webClient) {
        this.oAuth2ClientService = oAuth2ClientService;
        this.webClient = webClient;
    }
//    private final RestTemplate restTemplate;

//    public AlbumsService(OAuth2AuthorizedClientService oAuth2ClientService, RestTemplate restTemplate) {
//        this.oAuth2ClientService = oAuth2ClientService;
//        this.restTemplate = restTemplate;
//    }

    public String getAllAlbums(Model model,OidcUser principal){

        String url = "http://localhost:8082/albums";

        List<AlbumDto> albums = webClient.get()
                .uri(url)
                        .retrieve()
                                .bodyToMono(new ParameterizedTypeReference<List<AlbumDto>>() {
                                }).block();

//        AlbumDto album = new AlbumDto();
//        album.setAlbumId("albumOne");
//        album.setAlbumTitle("Album one title");
//        album.setAlbumUrl("http://localhost:8082/albums/1");
//
//        AlbumDto album2 = new AlbumDto();
//        album2.setAlbumId("albumTwo");
//        album2.setAlbumTitle("Album two title");
//        album2.setAlbumUrl("http://localhost:8082/albums/2");

        model.addAttribute("albums", albums);

        return "albums";
    }
}
