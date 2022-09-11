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
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AlbumsService {

    private  final OAuth2AuthorizedClientService oAuth2ClientService;
    private final RestTemplate restTemplate;

    public AlbumsService(OAuth2AuthorizedClientService oAuth2ClientService, RestTemplate restTemplate) {
        this.oAuth2ClientService = oAuth2ClientService;
        this.restTemplate = restTemplate;
    }

    public String getAllAlbums(Model model,OidcUser principal){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oauth2Client = oAuth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = "+jwtAccessToken);

        System.out.println("Principal = "+principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = "+idTokenValue);



        String url = "http://localhost:8082/albums";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+jwtAccessToken);

        HttpEntity<List<AlbumDto>> entity = new HttpEntity<>(headers);


        ResponseEntity<List<AlbumDto>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, entity,
                        new ParameterizedTypeReference<>() {
                        });

        List<AlbumDto> albums = responseEntity.getBody();

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
