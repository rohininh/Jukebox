package com.niit.daoImpl;

import com.niit.dao.ArtistDAO;
import com.niit.helper.MySqlConnection;
import com.niit.models.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAOImpl implements ArtistDAO
{

    private Connection connection;

    public ArtistDAOImpl()
    {
        connection= MySqlConnection.getConnection();
    }

    //get all Artist from the Artist table
    @Override
    public List<Artist> getAllArtist()
    {
        List<Artist> allArtist =new ArrayList<>();
        String query="select  * from songs";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                Artist artist = new Artist(resultSet.getString("artist_id"),resultSet.getString("artist_name"),
                        resultSet.getString("genre_id"));
                allArtist.add(artist);
            }
            return allArtist;
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

    //Display All Artist
    @Override
    public void displayAllArtist(List<Artist> artistList)
    {
        //Java 7 Features
       /* System.out.format("%-10s%-30s%-20s%-20s%-20s%-20s","Artist id","Artist Name","Genre Id","Location\n");

        for(Artist artist:artistList)
        {
            System.out.format("\n%-10s%-30s%-20s%-20s%-20s%-20s",artist.getArtistId(),
                    artist.getArtistName(),artist.getGenreId());
        }
        */
        //Java 8 Features
        System.out.format("%-10s%-30s%-20s%-20s%-20s%-20s","Artist id","Artist Name","Genre Id","Location\n");
        artistList.forEach(artist ->System.out.format("\n%-10s%-30s%-20s%-20s%-20s%-20s",artist.getArtistId(),
                artist.getArtistName(),artist.getGenreId()));
    }

    //get Artist From the Artist name
    @Override
    public Artist searchByArtistName(String name)
    {
        try
        {
            String query = "select * from artist where artist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Artist artist = new Artist(resultSet.getString("artist_id"),resultSet.getString("artist_name"),
                        resultSet.getString("genre_id"));
                return artist;
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

    //Search Artist by Genre
    @Override
    public List<Artist> searchByGenre(String genreId)
    {
        List<Artist> artistList =new ArrayList<>();
        try
        {
            String query = "select * from artist where genre_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genreId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Artist artist = new Artist(resultSet.getString("artist_id"), resultSet.getString("artist_name"),
                        resultSet.getString("genre_id"));
                artistList.add(artist);
            }
            return artistList;
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

    //insert Artist into artist table in the database
    @Override
    public boolean insertArtist(Artist artist)
    {
        try
        {
            String query= "insert into artist(artist_id,artist_name,genre_id) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,artist.getArtistId());
            preparedStatement.setString(2,artist.getArtistName());
            preparedStatement.setString(3,artist.getGenreId());

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

    //Delete Artist by artist name
    @Override
    public boolean deleteArtist(String artistName)
    {
        try
        {
            String query = "delete from artist where artist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,artistName);
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

    //Update Artist
    @Override
    public boolean updateArtist(Artist artist) {
        try
        {
            String query = "update artist set genre_id = ? where artist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,artist.getGenreId());
            preparedStatement.setString(2,artist.getArtistId());
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
