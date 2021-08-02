package com.niit.daoImpl;

import com.niit.dao.PlaylistCatalogDAO;
import com.niit.helper.MySqlConnection;
import com.niit.models.PlaylistCatalog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistCatalogDaoImpl implements PlaylistCatalogDAO
{
    private Connection connection;

    public PlaylistCatalogDaoImpl()
    {
        connection= MySqlConnection.getConnection();
    }

    //get all Playlist items from the one playlist
    @Override
    public List<PlaylistCatalog> getAllPlayListCatalog(String playlistId)
    {

        List<PlaylistCatalog> playlistCatalogArrayList =new ArrayList<>();

        try
        {
            String query="select * from playlist_catalog where playlist_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                PlaylistCatalog playlistCatalog= new PlaylistCatalog(resultSet.getString("cat_id"),
                        resultSet.getString("playlist_id"), resultSet.getString("item_id"),
                        resultSet.getString("item_type"));
                playlistCatalogArrayList.add(playlistCatalog);
            }
            return playlistCatalogArrayList;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //Display playlist Item  of  one Playlist
    @Override
    public void displayPlaylist(List<PlaylistCatalog> playlistCatalogList)
    {
        //Java 7 Features
       /* System.out.format("%-10s%-20s%-20s%-20s","Catalog Id,","Playlist id","Item Id","Item Type\n");
        for(PlaylistCatalog playlist:playlistCatalogList)
        {
            System.out.format("\n%-10s%-20s%-20s%-20s",playlist.getCatalogId(),playlist.getPlaylistId(),
                    playlist.getItemId(), playlist.getItemType());
        }
        */

        //Java 8 Features
        System.out.format("%-10s%-20s%-20s%-20s","Catalog Id,","Playlist id","Item Id","Item Type\n");
        playlistCatalogList.forEach(playlist ->System.out.format("\n%-10s%-20s%-20s%-20s",playlist.getCatalogId(),playlist.getPlaylistId(),
                playlist.getItemId(), playlist.getItemType()));


    }

    //insert song or podcast into playlist
    @Override
    public boolean insertItemToPlayList(PlaylistCatalog playlistCatalog)
    {
        try
        {
            String query= "insert into playlist_catalog(cat_id,playlist_id,item_id,item_type) values(?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,playlistCatalog.getCatalogId());
            preparedStatement.setString(2,playlistCatalog.getPlaylistId());
            preparedStatement.setString(3,playlistCatalog.getItemId());
            preparedStatement.setString(4, playlistCatalog.getItemType());


            int count=preparedStatement.executeUpdate();

            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    //delete playlist by item Id
    @Override
    public boolean deleteItemFromPlaylist(String itemId) {
        try
        {
            String query = "delete from playlist_catalog where cat_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,itemId);
            int count = preparedStatement.executeUpdate();
            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    //update playlist
    @Override
    public boolean updatePlaylist(PlaylistCatalog playlistCatalog) {
        try
        {
            String query = "update playlist_catalog set playlist_id = ? where cat_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlistCatalog.getPlaylistId());
            preparedStatement.setString(2, playlistCatalog.getCatalogId());

            int count = preparedStatement.executeUpdate();
            if(count>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

}
