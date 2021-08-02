package com.niit;

import com.niit.daoImpl.PlaylistCatalogDaoImpl;
import com.niit.models.PlaylistCatalog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaylistCatalogTest
{
    PlaylistCatalogDaoImpl playlistCatalogDaoImpl;

    @BeforeEach
    void setUp()
    {
        playlistCatalogDaoImpl =new PlaylistCatalogDaoImpl();
    }

    @AfterEach
    void teatDown()
    {
        playlistCatalogDaoImpl=null;
    }

    @Test
    void givenDataToInsertItemToPlayList()
    {
        assertEquals(true,playlistCatalogDaoImpl.insertItemToPlayList(new PlaylistCatalog("c009","pl001","s005","song")));
        assertEquals(true,playlistCatalogDaoImpl.insertItemToPlayList(new PlaylistCatalog("c010","pl001","pod001","Podcast")));
    }

    @Test
    void givenSongIdTodeleteSongFormPlaylist()
    {
        assertEquals(true,playlistCatalogDaoImpl.deleteItemFromPlaylist("c009"));
        assertEquals(false,playlistCatalogDaoImpl.deleteItemFromPlaylist("c011"));
    }
    @Test
    void givenPodcastIdTodeletePodcastFormPlaylist()
    {
        assertEquals(true,playlistCatalogDaoImpl.deleteItemFromPlaylist("c010"));
        assertEquals(false,playlistCatalogDaoImpl.deleteItemFromPlaylist("c011"));
    }
    @Test
    void givenItemUpdatePlaylistforSongOrPodcast()
    {
        assertEquals(true,playlistCatalogDaoImpl.updatePlaylist(new PlaylistCatalog("c009","pl004","s005","song")));
        assertEquals(false,playlistCatalogDaoImpl.updatePlaylist(new PlaylistCatalog("c014","pl004","s003","song")));
    }
    @Test
    void givenListOfPlayListToFindSizeofPlaylist()
    {
        assertEquals(2,playlistCatalogDaoImpl.getAllPlayListCatalog("pl002").size());
        assertEquals(0,playlistCatalogDaoImpl.getAllPlayListCatalog("pl005").size());
    }
}
