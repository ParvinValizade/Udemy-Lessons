package com.company.albums.controller;

import com.company.albums.dto.AlbumDto;
import com.company.albums.service.AlbumsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumsService albumsService;

    public AlbumsController(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }

    @GetMapping
    public List<AlbumDto> getAllAlbums(){
        return albumsService.getAllAlbums();
    }
}
