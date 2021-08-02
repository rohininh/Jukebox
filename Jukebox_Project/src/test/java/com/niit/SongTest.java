package com.niit;

import com.niit.daoImpl.SongDAOImpl;
import com.niit.models.Songs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest
{
    SongDAOImpl songDAOImpl;

    @BeforeEach
    void setUp()
    {
        songDAOImpl=new SongDAOImpl();
    }
    @AfterEach
    void tearDown()
    {
        songDAOImpl=null;
    }

    @Test
    void givenDataInsertSongIntoSongTable()
    {
        assertEquals(true,songDAOImpl.insertSong(new Songs("s007","Channa Mereya",4.50,"al001",
                "a003","H:\\music\\Channa Mereya (From Ae Dil Hai Mushkil)(Djjohal.Com.mp3")));
    }

    @Test
    void givenSongIdTodeleteSongFromSOngTaable()
    {
        assertEquals(true,songDAOImpl.deleteSong("s007"));
        assertEquals(false,songDAOImpl.deleteSong("s009"));
    }

    @Test
    void givenSongToUpdateSongBySongLocation() {
       assertEquals(true, songDAOImpl.updateSong(new Songs("s001", "Desi Girl", 5.00, "al001",
              "a001", "H:\\WAV Format Music\\Dostana 2008 - Desi Girl.wav")));
        assertEquals(false, songDAOImpl.updateSong(new Songs("s012","Ek Ladki Ko Dekha Toh Aisa Laga",2.36,"al006","a003",
               "H:\\WAV Format Music\\Ek Ladki Ko Dekha Toh Aisa Laga.wav")));

    }
    @Test
    void givenDiffrentAttritubeFindSizeOfSongsTable()
    {
        assertEquals(8,songDAOImpl.getAllSongs().size());
        assertEquals(1,songDAOImpl.searchSongByAlbum("al002").size());
        assertEquals(0,songDAOImpl.searchSongByAlbum("al0015").size());

        assertEquals(5,songDAOImpl.searchSongByArtist("a003").size());
        assertEquals(0,songDAOImpl.searchSongByArtist("a010").size());

        assertEquals(1,songDAOImpl.searchSongBySongName("tum hi ho").size());
        assertEquals(0,songDAOImpl.searchSongBySongName("ABCD").size());
    }

    @Test
    void givenSongNameTosearchSongsDetails()
    {
        assertEquals("s003",songDAOImpl.getSong("Maretuhoyite").getSongId());
        assertEquals("al003",songDAOImpl.getSong("Maretuhoyite").getAlbumId());
        assertEquals("a004",songDAOImpl.getSong("Maretuhoyite").getArtistId());
        assertEquals("H:\\WAV Format Music\\Marethuhoyithe.wav",songDAOImpl.getSong("Maretuhoyite").getLocation());
    }


}
