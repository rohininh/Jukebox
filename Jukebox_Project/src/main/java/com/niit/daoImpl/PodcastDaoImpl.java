package com.niit.daoImpl;

import com.niit.dao.PodcastDAO;
import com.niit.helper.MySqlConnection;
import com.niit.models.Podcast;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PodcastDaoImpl implements PodcastDAO
{
    private Connection connection;

    public PodcastDaoImpl()
    {
        connection= MySqlConnection.getConnection();
    }

    //get all podcast
    @Override
    public List<Podcast> getAllPodcast()
    {
        List<Podcast> allPodcast =new ArrayList<>();
        String query="select  * from podcast";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getDate("date_of_publish"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                allPodcast.add(podcast);
            }
            return allPodcast;
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

    //Insert podcast
    @Override
    public boolean insertPodcast(Podcast podcast) {
        try
        {
            String query= "insert into podcast(podcast_id,podcast_name,duration,date_of_publish,artist_id,location) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,podcast.getPodcastId());
            preparedStatement.setString(2,podcast.getPodcastName());
            preparedStatement.setDouble(3,podcast.getDuration());
            java.sql.Date date=new java.sql.Date(podcast.getDateOfPublish().getTime());
            preparedStatement.setDate(4, date);
            preparedStatement.setString(5, podcast.getArtistId());
            preparedStatement.setString(6, podcast.getLocation());

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

    //get one particular podcast
    @Override
    public Podcast getPodcast(String podcastName) {
        try
        {
            String query = "select * from podcast where podcast_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,podcastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getDate("date_of_publish"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                return  podcast;

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

    //Search Podcast by podcast name
    @Override
    public List<Podcast> searchByPodcastName(String podcastName)
    {
        List<Podcast> podcastList= new ArrayList<>();
        try
        {
            String query = "select * from podcast where podcast_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,podcastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getDate("date_of_publish"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                podcastList.add(podcast);
            }
            return podcastList;
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

    //search Podcast by date of publish
    @Override
    public List<Podcast> searchByDateOfPublish(Date date)
    {
        List<Podcast> podcastList= new ArrayList<>();
        try
        {
            String query = "select * from podcast where date_of_publish = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            java.sql.Date dateOfPublish= new java.sql.Date(date.getTime());
            preparedStatement.setDate(1,dateOfPublish);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getDate("date_of_publish"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                podcastList.add(podcast);
            }
            return podcastList;
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

    //Search Podcast by one artist
    @Override
    public List<Podcast> searchByArtist(String artistId) {
        List<Podcast> podcastList= new ArrayList<>();
        try
        {
            String query = "select * from podcast where artist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Podcast podcast=new Podcast(resultSet.getString("podcast_id"),resultSet.getString("podcast_name"),
                        resultSet.getDouble("duration"),resultSet.getDate("date_of_publish"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                podcastList.add(podcast);
            }
            return podcastList;
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

    //update podcast
    @Override
    public boolean updatePodcast(Podcast podcast) {
        try
        {
            String query = "update podcast set location = ? where podcast_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, podcast.getLocation());
            preparedStatement.setString(2, podcast.getPodcastId());

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

    //Delete Podcast from table
    @Override
    public boolean deletePodcast(String podcastId) {
        try
        {
            String query = "delete from podcast where podcast_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,podcastId);
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


    //Display All Podcast from the Podcast table
    @Override
    public void displayPodcast(List<Podcast> podcastList)
    {
        //Java 7 Features
        /* System.out.format("%-15s%-40s%-15s%-15s%s","Podcast id","Podcast Name","Duration","Artist Id","Location\n");
        System.out.println("\n------------------------------------------------------------------------------------------------------------------");

        for (Podcast podcast : podcastList)
        {
            System.out.format("\n%-15s%-40s%-15s%-15s%s",podcast.getPodcastId(),podcast.getPodcastName(),
                    podcast.getDuration(), podcast.getArtistId(),podcast.getLocation());
        }

        System.out.println("\n-------------------------------------------------------------------------------------------------------------------");

         */

        //Java 8 Features
        System.out.format("%-15s%-40s%-15s%-15s%s","Podcast id","Podcast Name","Duration","Artist Id","Location\n");
        System.out.println("\n------------------------------------------------------------------------------------------------------------------");
        podcastList.forEach(podcast ->System.out.format("\n%-15s%-40s%-15s%-15s%s",podcast.getPodcastId(),podcast.getPodcastName(),
                podcast.getDuration(), podcast.getArtistId(),podcast.getLocation()));
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------");

    }
}
