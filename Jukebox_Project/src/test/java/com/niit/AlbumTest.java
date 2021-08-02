package com.niit;

import com.niit.daoImpl.AlbumDAOImpl;
import com.niit.models.Album;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest
{
    AlbumDAOImpl albumDAOImpl;

    @BeforeEach
    void setUp()
    {
        albumDAOImpl = new AlbumDAOImpl();
    }

    @AfterEach
    void tearDown()
    {
        albumDAOImpl=null;
    }

    @Test
    void givenDataToInsertIntoAlbumTable() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(true,albumDAOImpl.insertAlbum(new Album("al008","Dostana",format.parse("2010-12-15"))));
    }

    @Test
    void givenAlbumObjectToUpdateAlbumNameByAlbumId() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(true,albumDAOImpl.updateAlbum(new Album("al007","Race",format.parse("2016-10-12"))));
        assertEquals(false,albumDAOImpl.updateAlbum(new Album("a009","Race",format.parse("2012-07-03"))));

    }

    @Test
    void givenAlbumIdToDeleteAlbumByAlbumId()
    {
        assertEquals(true,albumDAOImpl.deleteAlbum("al008"));
        assertEquals(false,albumDAOImpl.deleteAlbum("al010"));

    }
    @Test
    void givenAlbumNameToSearchAlbum() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat=format.parse("2020-04-02");
        assertEquals("al003",albumDAOImpl.searchByAlbumName("Dia").getAlbumId());
        assertEquals(new java.sql.Date(dateFormat.getTime()),albumDAOImpl.searchByAlbumName("Dia").getReleseDate());
    }
}
