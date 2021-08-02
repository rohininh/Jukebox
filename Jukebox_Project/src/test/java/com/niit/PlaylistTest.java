package com.niit;

import com.niit.daoImpl.PlaylistDaoImpl;
import com.niit.models.Playlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest
{
    PlaylistDaoImpl playlistDaoImpl;

    @BeforeEach
    void setUp()
    {
        playlistDaoImpl =new PlaylistDaoImpl();
    }

    @AfterEach
    void tearDown()
    {
        playlistDaoImpl=null;
    }

    @Test
    void givenDataToInsertPlayListIntoTable() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(true,playlistDaoImpl.insertPlaylist(new Playlist("pl005","Lonely",format.parse("2021-06-20"))));
    }

    @Test
    void givenSongIdToDeletePlayList()
    {
        assertEquals(true,playlistDaoImpl.deletePlaylist("pl005"));
        assertEquals(false,playlistDaoImpl.deletePlaylist("pl006"));

    }
    @Test
    void givenSongObjectToUpdatePlaList() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(true,playlistDaoImpl.updatePlaylist(new Playlist("pl003","Party",format.parse("2020-12-24"))));
        assertEquals(false,playlistDaoImpl.updatePlaylist(new Playlist("pl006","Lonely",format.parse("2020-12-24"))));
    }
    @Test
    void givenDateOrArtistToSearchForPlayList() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("pl002",playlistDaoImpl.getPlayList("Kannada Music").getPlaylistId());
        assertEquals(new java.sql.Date(format.parse("2021-06-15").getTime()),playlistDaoImpl.getPlayList("Kannada Music").getCreatedDate());
    }
}
