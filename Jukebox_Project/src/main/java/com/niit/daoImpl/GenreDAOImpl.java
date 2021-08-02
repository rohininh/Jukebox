package com.niit.daoImpl;

import com.niit.dao.GenreDAO;
import com.niit.helper.MySqlConnection;
import com.niit.models.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO
{
    private Connection connection;

    public GenreDAOImpl()
    {
        connection= MySqlConnection.getConnection();
    }

    //get all Genre from the table
    @Override
    public List<Genre> getAllGenre() {
        List<Genre> genreList =new ArrayList<>();
        String query="select  * from songs";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                Genre genre = new Genre(resultSet.getString("genre_id"),resultSet.getString("genre_name"));
                genreList.add(genre);
            }
            return genreList;
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

    // display Genre list
    @Override
    public void displayAllGenre(List<Genre> genreList)
    {

        //Java 7 Features
        /* System.out.format("%-10s%-30s%-20s%-20s%-20s%-20s","Genre id","Genre name");
        for (Genre genre : genreList)
        {
            System.out.format("\n%-10s%-30s%-20s%-20s%-20s%-20s",genre.getGenreId(),genre.getGenreName());
        }
         */

        //Java 8 Features
        System.out.format("%-10s%-30s%-20s%-20s%-20s%-20s","Genre id","Genre name");
        genreList.forEach(genre ->System.out.format("\n%-10s%-30s%-20s%-20s%-20s%-20s",genre.getGenreId(),genre.getGenreName()));

    }

    //Search Genre by Genre name
    @Override
    public Genre searchByGenreName(String GenreName)
    {
        try
        {
            String query = "select * from genre where genre_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,GenreName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Genre genre = new Genre(resultSet.getString("genre_id"),resultSet.getString("genre_name"));
                return genre;
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

    //insert genre into database
    @Override
    public boolean insertGenre(Genre genre)
    {
        try
        {
            String query= "insert into genre(genre_id,genre_name) values(?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,genre.getGenreId());
            preparedStatement.setString(2,genre.getGenreName());

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

    //Delete Genre from the genre name
    @Override
    public boolean deleteGenre(String genreName)
    {
        try
        {
            String query = "delete from genre where genre_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genreName);
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
// update Genre
    @Override
    public boolean updateGenre(Genre genre) {
        try
        {
            String query = "update genre set genre_name = ? where genre_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genre.getGenreName());
            preparedStatement.setString(2,genre.getGenreId());

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
