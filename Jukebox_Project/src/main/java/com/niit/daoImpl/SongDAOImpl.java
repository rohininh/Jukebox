package com.niit.daoImpl;

import com.niit.dao.SongDAO;
import com.niit.helper.MySqlConnection;
import com.niit.models.Songs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements SongDAO
{
    private Connection connection;

    public SongDAOImpl()
    {
        connection= MySqlConnection.getConnection();
    }

    //get all songs from the songs table
    @Override
    public List<Songs> getAllSongs()
    {
        List<Songs> allSongs =new ArrayList<>();
            String query="select  * from songs";
            try
            {
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query);
                while(resultSet.next())
                {
                    Songs song=new Songs(resultSet.getString("song_id"),resultSet.getString("song_name"),
                            resultSet.getDouble("duration"),resultSet.getString("album_id"),
                            resultSet.getNString("artist_id"), resultSet.getNString("location"));
                    allSongs.add(song);
                }
                return allSongs;
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

    //insert song into song table
    @Override
    public boolean insertSong(Songs song)
    {
        try
        {
            String query= "insert into songs(song_id,song_name,duration,album_id,artist_id,location) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,song.getSongId());
            preparedStatement.setString(2,song.getSongName());
            preparedStatement.setDouble(3,song.getDuration());
            preparedStatement.setString(4, song.getAlbumId());
            preparedStatement.setString(5, song.getArtistId());
            preparedStatement.setString(6, song.getLocation());

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

    //Search songs by song name
    @Override
    public List<Songs> searchSongBySongName(String songName)
    {
        List<Songs> songsList= new ArrayList<>();
            try
            {
                String query = "select * from songs where song_name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,songName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    Songs song=new Songs(resultSet.getString("song_id"),resultSet.getString("song_name"),
                            resultSet.getDouble("duration"),resultSet.getString("album_id"),
                            resultSet.getNString("artist_id"), resultSet.getNString("location"));
                    songsList.add(song);
                }
                return songsList;
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

    //Search song by artist name
    @Override
    public List<Songs> searchSongByArtist(String artistId)
    {
        List<Songs> songsList= new ArrayList<>();
        try
        {
            String query = "select * from songs where artist_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Songs song=new Songs(resultSet.getString("song_id"),resultSet.getString("song_name"),
                        resultSet.getDouble("duration"),resultSet.getString("album_id"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                songsList.add(song);
            }
            return songsList;
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

    //search songs by album
    @Override
    public List<Songs> searchSongByAlbum(String albumName) {
        List<Songs> songsList= new ArrayList<>();
        try
        {
            String query = "select * from songs where album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,albumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Songs song=new Songs(resultSet.getString("song_id"),resultSet.getString("song_name"),
                        resultSet.getDouble("duration"),resultSet.getString("album_id"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                songsList.add(song);
            }
            return songsList;
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

    //Search songs by Genre
    @Override
    public List<Songs> searchSongByGenre(String genreName)
    {
        List<Songs> songsList= new ArrayList<>();
        try
        {
            String query = "select s.song_id,s.song_name,s.duration, s.album_id, a.artist_name, g.genre_name, s.location\n" +
                    "from songs s join artist a \n" +
                    "on s.artist_id=a.artist_id join genre g\n" +
                    "on a.genre_id=g.genre_id\n" +
                    "where g.genre_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,genreName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Songs song=new Songs(resultSet.getString("song_id"),resultSet.getString("song_name"),
                        resultSet.getDouble("duration"),resultSet.getString("album_id"),
                        resultSet.getString("artist_id"), resultSet.getString("location"));
                songsList.add(song);
            }
            return songsList;
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

    //get one particular song from the song table
    @Override
    public Songs getSong(String songName) {
        try
        {
            String query = "select * from songs where song_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,songName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Songs song=new Songs(resultSet.getString("song_id"),resultSet.getString("song_name"),
                        resultSet.getDouble("duration"),resultSet.getString("album_id"),
                        resultSet.getNString("artist_id"), resultSet.getNString("location"));
                return song;
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

    //update song
    @Override
    public boolean updateSong(Songs song)
    {
        try
        {
            String query = "update songs set location = ? where song_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,song.getLocation());
             preparedStatement.setString(2,song.getSongId());

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

    //Delete Song from the songs table
    @Override
    public boolean deleteSong(String songId) {
        try
        {
            String query = "delete from songs where song_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,songId);
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

    //Display songs
    @Override
    public void display(List<Songs> allSongs)
    {
        //java 7 features
       /* System.out.format("%-10s%-35s%-20s%-20s%-20s%-20s","Song id","SongName","Duration","Album Id","Artist Id","Location\n");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");

        for(Songs song:allSongs)
        {
            System.out.format("\n%-10s%-35s%-20s%-20s%-20s%-20s",song.getSongId(),song.getSongName(),song.getDuration(),
                    song.getAlbumId(),song.getArtistId(),song.getLocation());
        }
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
    */

        //Java 8 Features
        System.out.format("%-10s%-35s%-20s%-20s%-20s%-20s","Song id","SongName","Duration","Album Id","Artist Id","Location\n");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
        allSongs.forEach(song ->System.out.format("\n%-10s%-35s%-20s%-20s%-20s%-20s",song.getSongId(),song.getSongName(),song.getDuration(),
                song.getAlbumId(),song.getArtistId(),song.getLocation()));
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------");

    }

}
