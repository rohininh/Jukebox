package com.niit.dao;

import com.niit.models.Artist;

import java.util.List;

public interface ArtistDAO
{
    List<Artist> getAllArtist();
    void displayAllArtist(List<Artist> artistsList);
    Artist searchByArtistName(String name);
    List<Artist> searchByGenre(String genre);
    boolean insertArtist(Artist artist);
    boolean deleteArtist(String name);
    boolean updateArtist(Artist artist);
}
