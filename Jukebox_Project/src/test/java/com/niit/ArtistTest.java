package com.niit;

import com.niit.daoImpl.ArtistDAOImpl;

import com.niit.models.Artist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArtistTest
{
    ArtistDAOImpl artistDAOImpl;

    @BeforeEach
    void setUp()
    {
        artistDAOImpl = new ArtistDAOImpl();
    }
    @AfterEach
    void tearDown()
    {
        artistDAOImpl = null;
    }

    @Test
    void givenDataToInsertIntoArtistTable()
    {
        assertEquals(true, artistDAOImpl.insertArtist(new Artist("a006","Sonu Nigam","g004")));
    }
     @Test
    void givenArtistNameToSearchArtistByArtistName()
     {
         assertEquals("Arijit Singh",artistDAOImpl.searchByArtistName("Arijit Singh").getArtistName());
         assertEquals(null,artistDAOImpl.searchByArtistName("Arman Malik"));
     }
    @Test
    void givenGenreIdTofindSizeOfSearchArtistByGenre()
    {
        assertEquals(3,artistDAOImpl.searchByGenre("g004").size());
        assertEquals(0,artistDAOImpl.searchByGenre("g006").size());
    }

     @Test
    void givenArtistObjectToUpdateArtistByName()
     {
         assertEquals(true,artistDAOImpl.updateArtist(new Artist("a001","Shankar mahadevan","g004")));
         assertEquals(false,artistDAOImpl.updateArtist(new Artist("a009","Shankar mahadevan","g004")));
     }

     @Test
    void givenArtistNameToDeleteArtistFromTheTable()
     {
         assertEquals(true,artistDAOImpl.deleteArtist("Sonu Nigam"));
         assertEquals(false,artistDAOImpl.deleteArtist("Arman Malik"));

     }
}
