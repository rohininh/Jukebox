package com.niit.dao;

import com.niit.models.Podcast;

import java.util.Date;
import java.util.List;

public interface PodcastDAO
{
    List<Podcast> getAllPodcast();
    boolean insertPodcast(Podcast podcast);
    Podcast getPodcast(String podcastName);
    List<Podcast> searchByPodcastName(String podcastName);
    List<Podcast> searchByDateOfPublish(Date date);
    List<Podcast> searchByArtist(String artistName);
    boolean updatePodcast(Podcast podcast);
    boolean deletePodcast(String podcastName);
    void displayPodcast(List<Podcast> podcastList);
}
