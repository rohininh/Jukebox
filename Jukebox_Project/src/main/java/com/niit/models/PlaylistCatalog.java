package com.niit.models;

public class PlaylistCatalog
{
    private String catalogId;
    private String playlistId;
    private String itemId;
    private String itemType;


    public PlaylistCatalog(String catalogId, String playlistId, String itemId,String itemType) {
        this.catalogId = catalogId;
        this.playlistId = playlistId;
        this.itemId = itemId;
        this.itemType = itemType;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
