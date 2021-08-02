package com.niit.dao;

import com.niit.models.Songs;
import java.util.List;

public interface SongDAO
{
     List<Songs> getAllSongs();
     boolean insertSong(Songs song);
    List<Songs> searchSongBySongName(String songName);
    List<Songs> searchSongByArtist(String artistName);
    List<Songs> searchSongByAlbum(String albumName);
    List<Songs> searchSongByGenre(String genreName);
    Songs getSong(String songName);
     boolean updateSong(Songs song);
     boolean deleteSong(String songName);
     void display(List<Songs> allSongs);
}
