package com.example.photowebappclient.service;

import com.example.photowebappclient.dto.AlbumDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;

@Service
public class AlbumsService {

    public String getAllAlbums(Model model){
        AlbumDto album = new AlbumDto();
        album.setAlbumId("albumOne");
        album.setAlbumTitle("Album one title");
        album.setAlbumUrl("http://localhost:8082/albums/1");

        AlbumDto album2 = new AlbumDto();
        album2.setAlbumId("albumTwo");
        album2.setAlbumTitle("Album two title");
        album2.setAlbumUrl("http://localhost:8082/albums/2");

        model.addAttribute("albums", Arrays.asList(album,album2));

        return "albums";
    }
}
