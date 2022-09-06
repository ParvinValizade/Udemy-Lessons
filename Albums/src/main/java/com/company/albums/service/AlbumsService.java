package com.company.albums.service;

import com.company.albums.dto.AlbumDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumsService {

    public List<AlbumDto> getAllAlbums(){
        AlbumDto album1 = new AlbumDto();
        album1.setAlbumId("albumIdHere");
        album1.setUserId("1");
        album1.setAlbumTitle("Album 1 title");
        album1.setAlbumDescription("Album 1 description");
        album1.setAlbumUrl("Album 1 URL");

        AlbumDto album2 = new AlbumDto();
        album2.setAlbumId("albumIdHere");
        album2.setUserId("2");
        album2.setAlbumTitle("Album 2 title");
        album2.setAlbumDescription("Album 2 description");
        album2.setAlbumUrl("Album 2 URL");

        return Arrays.asList(album1,album2);
    }
}
