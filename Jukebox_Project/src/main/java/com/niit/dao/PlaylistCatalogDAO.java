package com.niit.dao;

import com.niit.models.Playlist;
import com.niit.models.PlaylistCatalog;


import java.util.List;

public interface PlaylistCatalogDAO
{
    List<PlaylistCatalog> getAllPlayListCatalog(String playlistName);
    boolean insertItemToPlayList(PlaylistCatalog playlistCatalog);

    boolean deleteItemFromPlaylist(String itemId);
    boolean updatePlaylist(PlaylistCatalog playlistCatalog);
    void displayPlaylist(List<PlaylistCatalog> playlistCatalogList);
}
