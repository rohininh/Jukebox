package com.niit.models;

import java.util.Date;

public class Album
{
    private String albumId;
    private String albumName;
    private Date releseDate;

    public Album(String albumId, String albumName, Date releseDate) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.releseDate = releseDate;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(Date releseDate) {
        this.releseDate = releseDate;
    }
}
