package com.niit;

import com.niit.daoImpl.GenreDAOImpl;
import com.niit.models.Genre;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GenreTest
{
    GenreDAOImpl genreDaoImpl;

    @BeforeEach
    void setUp()
    {
        genreDaoImpl = new GenreDAOImpl();
    }
    @AfterEach
    void tearDown()
    {
        genreDaoImpl = null;
    }
    @Test
    void givenDataToInsertGenreToTheGenreTable()
    {

        assertEquals(true,genreDaoImpl.insertGenre(new Genre("g006","indo pop")));
    }
    @Test
    void givenGenrenameToSearchByGenreNameFromTable()
    {
        assertEquals("g004",genreDaoImpl.searchByGenreName("Indian Classical music").getGenreId());
        assertEquals(null,genreDaoImpl.searchByGenreName("Rock"));
    }
    @Test
    void givenGenreIdDeleteGenreFromTable()
    {
        assertEquals(true,genreDaoImpl.deleteGenre("g006"));
        assertEquals(false,genreDaoImpl.deleteGenre("g009"));
    }
    @Test
    void  givenGenreObjectToUpdateGenreNameInGenreTable()
    {
        assertEquals(true,genreDaoImpl.updateGenre(new Genre( "g001","POP")));
        assertEquals(false,genreDaoImpl.updateGenre(new Genre( "g008","POP")));
    }
}
