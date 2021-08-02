package com.niit.dao;

import com.niit.models.Album;

import java.util.List;

public interface AlbumDAO
{
    List<Album> getAllAlbum();
    void display(List<Album> albumList);
    boolean insertAlbum(Album album);
    Album searchByAlbumName(String albumName);
    boolean updateAlbum(Album album);
    boolean deleteAlbum(String albumName);
}
