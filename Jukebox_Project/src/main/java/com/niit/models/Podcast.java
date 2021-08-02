package com.niit.models;

import java.util.Date;

public class Podcast implements Comparable<Podcast>
{
    private String podcastId;
    private String podcastName;
    private double duration;
    private Date dateOfPublish;
    private String artistId;
    private String location;

    public Podcast(String podcastId, String podcastName, double duration, Date dateOfPublish, String artistId, String location) {
        this.podcastId = podcastId;
        this.podcastName = podcastName;
        this.duration = duration;
        this.dateOfPublish = dateOfPublish;
        this.artistId = artistId;
        this.location = location;
    }

    public String getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(String podcastId) {
        this.podcastId = podcastId;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(Date dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public int compareTo(Podcast podcast)
    {
        return this.getPodcastName().compareTo(podcast.getPodcastId());
    }
}
