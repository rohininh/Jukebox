package com.niit;

import com.niit.daoImpl.PodcastDaoImpl;
import com.niit.models.Podcast;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class PodcastTest
{
    PodcastDaoImpl podcastDaoImpl;

    @BeforeEach
    void setUp()
    {
        podcastDaoImpl=new PodcastDaoImpl();
    }

    @AfterEach
    void tearDown()
    {
        podcastDaoImpl=null;
    }

    @Test
    void givenDataToInsertPodcastIntoTable() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(true,podcastDaoImpl.insertPodcast(new Podcast("pod007","Sadhguru_You_are_not_your_mind",
                35.00,format.parse("2020-06-06"),"a005","H:\\music\\Sadhguru_You_are_not_your_mind.mp3")));
    }
    @Test
    void GivenPodcastIdToDeletePodcastFromtable()
    {
        assertEquals(true,podcastDaoImpl.deletePodcast("pod007"));
        assertEquals(false,podcastDaoImpl.deletePodcast("pod009"));
    }

    @Test
    void givenPodcastObjectToUpdatePodcastTable() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(true,podcastDaoImpl.updatePodcast(new Podcast("pod001","Best of Arijit Singh",
               30.05,format.parse("2020-10-12"), "a003","H:\\WAV Format Music\\Arijith Singh Podcast.wav")));
        assertEquals(false,podcastDaoImpl.updatePodcast(new Podcast("pod019","Best of Arijit Singh",13000,
                format.parse("2020-10-12"),"a003","H:musicArijith Singh Podcast.mp3")));

    }

    @Test
    void givenDifferentAttributeToGetSizeOfPodcastList() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(4,podcastDaoImpl.getAllPodcast().size());
        assertEquals(2,podcastDaoImpl.searchByDateOfPublish(format.parse("2020-10-12")).size());
        assertEquals(1,podcastDaoImpl.searchByArtist("a005").size());
        assertEquals(0,podcastDaoImpl.searchByPodcastName("How to be calm in every situation").size());

    }
    @Test
    void givenPodcastNameToSearchPodcastDetails() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("pod001",podcastDaoImpl.getPodcast("Best of Arijit Singh").getPodcastId());
        assertEquals("a003",podcastDaoImpl.getPodcast("Best of Arijit Singh").getArtistId());
        assertEquals(new java.sql.Date(format.parse("2020-10-12").getTime()),podcastDaoImpl.getPodcast("Best of Arijit Singh").getDateOfPublish());
        assertEquals("H:\\WAV Format Music\\Arijith Singh Podcast.wav",podcastDaoImpl.getPodcast("Best of Arijit Singh").getLocation());
    }
}
