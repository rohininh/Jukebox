package com.niit.models;

import java.util.Date;

public class Playlist
{
    private String playlistId;
    private String playlistName;
    private Date createdDate;

    public Playlist(String playlistId, String playlistName,Date createdDate) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.createdDate=createdDate;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
