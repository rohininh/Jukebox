package com.niit.daoImpl;

import com.niit.dao.AlbumDAO;
import com.niit.helper.MySqlConnection;
import com.niit.models.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAOImpl implements AlbumDAO
{
    private Connection connection;

    public AlbumDAOImpl()
    {
        connection= MySqlConnection.getConnection();
    }

    //Select all Albums from the database
    @Override
    public List<Album> getAllAlbum()
    {
        List<Album> albumArrayList =new ArrayList<>();
        String query="select  * from album";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                Album album =new Album(resultSet.getString("album_id"),resultSet.getString("album_name"),
                        resultSet.getDate("relese_date"));
                albumArrayList.add(album);
            }
            return albumArrayList;
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

    //Display All Album
    @Override
    public void display(List<Album> albumList)
    {
        // Java 7 Features
        /*System.out.format("%-10s%-20s%-20s","Album Id","Album Name","Relese Date");
        for(Album album:albumList)
        {
            System.out.format("%-10s%-20s%-20s",album.getAlbumId(),album.getAlbumName(),album.getReleseDate());
        }*/

        //Java 8 Features
        System.out.format("%-10s%-20s%-20s","Album Id","Album Name","Relese Date");
        albumList.stream().forEach(album -> System.out.format("%-10s%-20s%-20s",album.getAlbumId(),album.getAlbumName(),album.getReleseDate()));

    }

    //Insert Album into Database
    @Override
    public boolean insertAlbum(Album album)
    {
        try
        {
            String query= "insert into album(album_id,album_name,relese_date) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,album.getAlbumId());
            preparedStatement.setString(2,album.getAlbumName());
            java.sql.Date date=new java.sql.Date(album.getReleseDate().getTime());
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

    //Search  album
    @Override
    public Album searchByAlbumName(String albumName)
    {
        try
        {
            String query = "select * from album where album_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,albumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Album album = new Album(resultSet.getString("album_id"),resultSet.getString("album_name"),
                        resultSet.getDate("relese_date"));
                return album;
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

    //Update Album
    @Override
    public boolean updateAlbum(Album album) {
        try
        {
            String query = "update album set album_name = ? where album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,album.getAlbumName());
            preparedStatement.setString(2,album.getAlbumId());

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

    //Delete Album
    @Override
    public boolean deleteAlbum(String albumId)
    {
        try
        {
            String query = "delete from album where album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,albumId);
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
