package com.niit.daoImpl;

import com.niit.dao.PlayListDAO;
import com.niit.helper.MySqlConnection;
import com.niit.models.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDaoImpl implements PlayListDAO
{

    private Connection connection;

    public PlaylistDaoImpl()
    {
        connection= MySqlConnection.getConnection();
    }

    //get all playlist from the playlist table
    @Override
    public List<Playlist> getAllPlaylist()
    {
        List<Playlist> allPlaylist =new ArrayList<>();
        String query="select  * from playlist";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                Playlist playlist=new Playlist(resultSet.getString("playlist_id"),resultSet.getString("playlist_name"),
                        resultSet.getDate("created_date"));
                allPlaylist.add(playlist);
            }
            return allPlaylist;
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

    //display all playlist name to user
    @Override
    public void displayPlayList(List<Playlist> playlistList)
    {
        //Java 7 Features
        /*System.out.format("%-20s%s","Playlist id","Playlist Name");
        System.out.println("\n-----------------------------------------");
        for (Playlist playlist:playlistList)
        {
            System.out.format("\n%-20s%-20s",playlist.getPlaylistId(),playlist.getPlaylistName());
        }
        System.out.println("\n------------------------------------------");
         */

        //Java 8 Features
        System.out.format("%-20s%s","Playlist id","Playlist Name");
        System.out.println("\n-----------------------------------------");
        playlistList.forEach(playlist ->System.out.format("\n%-20s%-20s",playlist.getPlaylistId(),playlist.getPlaylistName()));
        System.out.println("\n------------------------------------------");
    }

    // insert playlist
    @Override
    public boolean insertPlaylist(Playlist playlist) {
        try
        {
            String query= "insert into playlist(playlist_id,playlist_name,created_date) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,playlist.getPlaylistId());
            preparedStatement.setString(2,playlist.getPlaylistName());
            java.sql.Date date=new java.sql.Date(playlist.getCreatedDate().getTime());
            preparedStatement.setDate(3,date);

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

    //Update playlist
    @Override
    public boolean updatePlaylist(Playlist playlist)
    {
        try
        {
            String query = "update playlist set playlist_name = ? where playlist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlist.getPlaylistName());
            preparedStatement.setString(2,playlist.getPlaylistId());

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

    //Delete playlist from the table
    @Override
    public boolean deletePlaylist(String playlistId)
    {
        try
        {
            String query = "delete from playlist where playlist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playlistId);
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

    //get one particular playlist
    @Override
    public Playlist getPlayList(String playListName) {
        try
        {
            String query = "select * from playlist where playlist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,playListName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
               Playlist playlist =new Playlist(resultSet.getString("playlist_id"),resultSet.getString("playlist_name"),
                       resultSet.getDate("created_date"));
                return playlist;
            }
            else
            {
                return null;
            }

        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
