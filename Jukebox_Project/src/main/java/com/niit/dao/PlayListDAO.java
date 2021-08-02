package com.niit.dao;

import com.niit.models.Playlist;

import java.util.List;

public interface PlayListDAO
{
    List<Playlist> getAllPlaylist();
    void displayPlayList(List<Playlist> playlistList);
    boolean insertPlaylist(Playlist playlist);
    boolean updatePlaylist(Playlist playlist);
    boolean deletePlaylist(String playlist_id);
    Playlist getPlayList(String playListName);
}
