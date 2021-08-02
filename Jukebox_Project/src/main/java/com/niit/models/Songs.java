package com.niit.models;

import java.sql.Time;
import java.util.Comparator;
import java.util.Timer;

public class Songs implements Comparable<Songs>
{
    private String songId;
    private String songName;
    private double duration;
    private String albumId;
    private String artistId;
    private String location;

    public Songs(String songId, String songName, double duration, String albumId, String artistId, String location) {
        this.songId = songId;
        this.songName = songName;
        this.duration = duration;
        this.albumId = albumId;
        this.artistId = artistId;
        this.location = location;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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
    public int compareTo(Songs songs) {
        return this.getSongName().compareTo(songs.getSongName());
    }
}
