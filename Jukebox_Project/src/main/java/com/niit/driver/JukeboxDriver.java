package com.niit.driver;

import com.niit.dao.*;
import com.niit.daoImpl.*;
import com.niit.models.*;
import com.niit.userDefineExceptions.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class JukeboxDriver
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        SongDAO songDaoImpl = new SongDAOImpl();
        ArtistDAO artistDAOImpl = new ArtistDAOImpl();
        GenreDAO genreDaoImpl = new GenreDAOImpl();
        AlbumDAO albumDaoImpl = new AlbumDAOImpl();
        PodcastDAO podcastDaoImpl = new PodcastDaoImpl();
        PlayListDAO playlistDaoImpl =new PlaylistDaoImpl();
        PlaylistCatalogDAO playlistCatalogDaoImpl = new PlaylistCatalogDaoImpl();
        PlaymusicDAOImpl playMusicDAOImpl ;


        int menuChoice;
        System.out.println("************************** Welcome to Jukebox Music **************************");
        System.out.println("******** User Menu **********");
        do {
            System.out.println("1. Songs\n2. Podcast\n3. PlayList\n4. Exit");
            System.out.println("Enter your Choice : (1-4)");
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                //Song Menu
                case 1:
                    int songOption;
                    System.out.println("......Songs Menu......");
                    do {
                        System.out.println("\n1. View All Songs \n2. Insert Song\n3. Search Songs\n4. Go back");
                        System.out.println("Enter you choice : (1-4)");
                        songOption = scanner.nextInt();
                        switch (songOption) {
                            case 1:
                                try
                                {
                                    //Display All Song
                                    List<Songs> songsList = songDaoImpl.getAllSongs();
                                    Collections.sort(songsList);
                                    if(songsList.size()==0)
                                    {
                                        throw new EmptySongsListException("No Songs present in the Songs List");
                                    }
                                    else
                                    {
                                        songDaoImpl.display(songsList);
                                    }
                                }
                                catch (EmptySongsListException ex)
                                {
                                    System.out.println(ex.getMessage());
                                }
                                break;

                            case 2:
                                //Insert Song into Song table
                                System.out.println("Enter song Name : ");
                                String sName=scanner.next();
                                System.out.println("Enter Duration of Song : ");
                                double duration = scanner.nextDouble();
                                System.out.println("Enter Artist Name : ");
                                String aName=scanner.next();
                                Artist artistName=artistDAOImpl.searchByArtistName(aName);
                                System.out.println("Enter Album Name : ");
                                String alName =scanner.next();
                                Album albumName= albumDaoImpl.searchByAlbumName(alName);
                                System.out.println("Enter location of Song : ");
                                String location =scanner.next();

                                int start =0;
                                int end = 9000;
                                String songId ="s"+(int) Math.floor(Math.random()*(end-start+1)+start);

                                Songs song = new Songs(songId, sName, duration, albumName.getAlbumId(), artistName.getArtistId(), location);
                                boolean insertSong = songDaoImpl.insertSong(song);
                                if (insertSong)
                                {
                                    System.out.println("Song " + song.getSongName() + " is inserted successfully");
                                }
                                else
                                {
                                    System.out.println("Sorry Unable to insert Song");
                                }
                                break;

                            case 3:
                                //Search Song by Artist, Album, Genre, and Song Name
                                int searchChoice;
                                do {
                                    System.out.println("\n......Choose search option..........");
                                    System.out.println("1. By Song name \n2. By Artist\n3. By Genre\n4. By Album\n5. Go back");
                                    System.out.println("Enter your Choice : (1-5)");
                                    searchChoice = scanner.nextInt();
                                    switch (searchChoice) {
                                        case 1:
                                            //Seach Song by Song Name and display to user
                                            System.out.println("Enter Song name to search : ");
                                            String songName = scanner.next();
                                            try
                                            {
                                                List<Songs> songsBysongName = songDaoImpl.searchSongBySongName(songName);
                                                Collections.sort(songsBysongName);
                                                if(songsBysongName.size()==0)
                                                {
                                                    throw new EmptySongsListException("No Songs present in the Songs List");
                                                }
                                                else {
                                                    songDaoImpl.display(songsBysongName);
                                                }
                                            }
                                            catch (EmptySongsListException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }


                                            break;
                                        case 2:
                                            //Seach Song by Artist Name and display to user
                                            System.out.println("Enter Artist name to search Songs : ");
                                            String nameOfArtist = scanner.next();
                                            try
                                            {
                                                Artist artist = artistDAOImpl.searchByArtistName(nameOfArtist);
                                                if(artist==null)
                                                {
                                                    throw new ArtistNotFoundException(nameOfArtist+" is present in the Artist List");
                                                }
                                                List<Songs> songsByArtist = songDaoImpl.searchSongByArtist(artist.getArtistId());
                                                Collections.sort(songsByArtist);
                                                if(songsByArtist.size()==0)
                                                {
                                                    throw new EmptySongsListException("No Songs present in the Songs List");
                                                }
                                                else
                                                {
                                                    songDaoImpl.display(songsByArtist);
                                                }
                                            }
                                            catch (ArtistNotFoundException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }
                                            catch (EmptySongsListException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }


                                            break;
                                        case 3:
                                            //Search Song by Genre Name and display to user
                                            System.out.println("Enter Genre name that you want to search for songs : ");
                                            String genreName =  scanner.next();
                                            try
                                            {
                                                Genre genre = genreDaoImpl.searchByGenreName(genreName);
                                                List<Songs> songsByGenre = songDaoImpl.searchSongByGenre(genre.getGenreName());
                                                Collections.sort(songsByGenre);
                                                if(songsByGenre.size()==0)
                                                {
                                                    throw new EmptySongsListException("No Songs present in the Songs List");
                                                }
                                                else {
                                                    songDaoImpl.display(songsByGenre);
                                                }
                                            }
                                            catch (EmptySongsListException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }

                                            break;
                                        case 4:
                                            //Search Song by Album Name and display to user
                                            System.out.println("Enter Album Name that you want to search for Songs : ");
                                            String nameOfAlbum = scanner.next();
                                            try
                                            {
                                                Album album = albumDaoImpl.searchByAlbumName(nameOfAlbum);
                                                if(album==null)
                                                {
                                                    throw new NoSuchAlbumAvaiableException(nameOfAlbum+" is not present for for any of the songs");
                                                }
                                                List<Songs> songsByAlbum = songDaoImpl.searchSongByAlbum(album.getAlbumId());
                                                Collections.sort(songsByAlbum);
                                                if(songsByAlbum.size()==0)
                                                {
                                                    throw new EmptySongsListException("No Songs present in the Songs List");
                                                }
                                                else
                                                {
                                                    songDaoImpl.display(songsByAlbum);
                                                }
                                            }
                                            catch (NoSuchAlbumAvaiableException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }
                                            catch (EmptySongsListException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }

                                            break;
                                        default:
                                            System.out.println("Exit from the Search Songs");
                                            break;
                                    }
                                } while (searchChoice <=4);
                                break;

                            default: // exit from the Song menu
                                System.out.println("Exit from the Song menu");
                                System.out.println("........End of Song menu..........");
                                break;
                        }
                    } while (songOption <=3);

                    break;

                //Podcast menu
                case 2:
                    int podcastChoice;
                    do {
                        System.out.println("1. View All Podcast \n2. Insert Podcast\n3. Search Podcast\n4. Exit");
                        System.out.println("Enter your choice : (1-4)");
                        podcastChoice = scanner.nextInt();

                        switch (podcastChoice) {
                            case 1:
                                try
                                {
                                    //Display All Podcast
                                    List<Podcast> podcastList = podcastDaoImpl.getAllPodcast();
                                    Collections.sort(podcastList);
                                    if(podcastList.size()==0)
                                    {
                                        throw new EmptyPodcastListException("No podcast available");
                                    }
                                    podcastDaoImpl.displayPodcast(podcastList);
                                }
                                catch (EmptyPodcastListException ex)
                                {
                                    System.out.println(ex.getMessage());
                                }

                                break;
                            case 2:
                                try
                                {
                                    //Insert Podcast
                                    System.out.println("Enter Podcast Name : ");
                                    String pName=scanner.next();
                                    System.out.println("Enter Duration : ");
                                    double podDuration = scanner.nextDouble();
                                    System.out.println("Enter Date of publish : ");
                                    String date = scanner.next();
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Date dateConverted=format.parse(date);
                                    System.out.println("Artist Name : ");
                                    String artiName= scanner.next();
                                    Artist findArtist=artistDAOImpl.searchByArtistName(artiName);
                                    System.out.println("Enter Location : ");
                                    String location = scanner.next();

                                    int start =0;
                                    int end = 9000;
                                    String podId ="pod"+(int) Math.floor(Math.random()*(end-start+1)+start);

                                    Podcast podcast = new Podcast(podId, pName, podDuration, dateConverted, findArtist.getArtistId(), location);
                                    boolean insertPodcast = podcastDaoImpl.insertPodcast(podcast);
                                    if (insertPodcast) {
                                        System.out.println("Song " + podcast.getPodcastName() + " is inserted successfully");
                                    } else {
                                        System.out.println("Sorry Unable to insert Song");
                                    }
                                } catch (ParseException ex) {
                                    System.out.println(ex.getMessage());
                                }

                                break;
                            case 3:
                                //Search Podcast by Artist or Date of Release date
                                int podcastSearch;
                                do {
                                    System.out.println("\n......Choose search option..........");
                                    System.out.println("1. By Podcast Published Date \n2. By Artist\n3. Exit");
                                    System.out.println("Enter your choice : (1-3)");
                                    podcastSearch = scanner.nextInt();
                                    switch (podcastSearch)
                                    {
                                        case 1:
                                            try
                                            {
                                                //Search Podcast by Date of Publish
                                                System.out.println("Enter Date of publish to search for podcast :  ");
                                                String dateOfPublish = scanner.next();
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                Date publishedDate = dateFormat.parse(dateOfPublish);
                                                List<Podcast> podcasts = podcastDaoImpl.searchByDateOfPublish(publishedDate);
                                                Collections.sort(podcasts);
                                                if(podcasts.size()==0)
                                                {
                                                    throw new EmptyPodcastListException("No podcast available for this Date");
                                                }
                                                else
                                                {
                                                    podcastDaoImpl.displayPodcast(podcasts);
                                                }
                                            }
                                            catch (ParseException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }
                                            catch (EmptyPodcastListException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }

                                            break;
                                        case 2:
                                            //Search By Artist
                                            System.out.println("Enter Artist name to search for Podcast : ");
                                            String artistName = scanner.next();
                                            try
                                            {
                                                Artist artist = artistDAOImpl.searchByArtistName(artistName);
                                                if(artist==null)
                                                {
                                                    throw new ArtistNotFoundException(artistName+" is present in the Artist List");
                                                }
                                                List<Podcast> podcastListByArtist = podcastDaoImpl.searchByArtist(artist.getArtistId());
                                                Collections.sort(podcastListByArtist);
                                                if(podcastListByArtist.size()==0)
                                                {
                                                    throw new EmptyPodcastListException("No podcast available for this Artist");
                                                }
                                                else
                                                {
                                                    podcastDaoImpl.displayPodcast(podcastListByArtist);
                                                }

                                            } catch (ArtistNotFoundException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            } catch (EmptyPodcastListException ex)
                                            {
                                                System.out.println(ex.getMessage());
                                            }

                                            break;
                                        default:
                                            System.out.println("Exit from the Search Podcast");
                                            break;
                                    }
                                } while (podcastSearch <=2);
                                break;
                            default: // exit from the podcast
                                System.out.println("Exit from the Podcast");
                                System.out.println(".....End of Podcast Menu.....");
                                break;
                        }
                    } while (podcastChoice <=3);
                    break;

            //PlayList menu starts
                case 3:
                    int playListChoice;
                    String plName=null;

                    int start =0;
                    int end = 9000;
                    String catId ="c"+(int) Math.floor(Math.random()*(end-start+1)+start);

                    System.out.println("......Playlist Menu......");
                    do {
                        //User option to choose
                        System.out.println("\n1. View all item\n2. Insert Song to Playlist\n3. insert Podcast to playlist\n4. Play Music\n5. Exit");
                        System.out.println("Enter your Choice : (1-5)");
                        playListChoice= scanner.nextInt();

                        switch (playListChoice)
                        {
                            case 1:
                                //display all Playlist item of one particular playlist
                                    List<Playlist> playlistsList=playlistDaoImpl.getAllPlaylist();
                                    playlistDaoImpl.displayPlayList(playlistsList);
                                    System.out.println("Enter your playlist name to view all songs of that playlist : ");
                                    plName= scanner.next();
                                    try
                                    {
                                        Playlist playlist = playlistDaoImpl.getPlayList(plName);
                                        List<PlaylistCatalog> playlistCatalogList = playlistCatalogDaoImpl.getAllPlayListCatalog(playlist.getPlaylistId());
                                        if(playlistCatalogList.size()==0)
                                        {
                                            throw new EmptyPlaylistException("No songs or podcast are presnt in the playlist");
                                        }
                                        else
                                        {
                                            List<Songs> songsList=songDaoImpl.getAllSongs();
                                            Collections.sort(songsList);
                                            if(songsList.size()==0)
                                            {
                                                throw new EmptySongsListException("No Songs present in the Songs List");
                                            }
                                            List<Podcast> podcastList = podcastDaoImpl.getAllPodcast();
                                            Collections.sort(podcastList);
                                            if(podcastList.size()==0)
                                            {
                                                throw new EmptyPodcastListException("No podcast present in the podcast List");
                                            }

                                            System.out.println("\n\n************************** Playlist : "+playlist.getPlaylistName()+" ********************************");
                                            System.out.format("\n%-10s%-25s%-35s%-20s","Sl.No","Playlist Name","Item Name","Item Type");
                                            System.out.println("\n--------------------------------------------------------------------------------------");

                                            //java 7 features
                                            /*   int count =1;
                                            for(PlaylistCatalog list:playlistCatalogList)
                                            {
                                                for (Songs songs : songsList)
                                                {
                                                    if(list.getItemId().equalsIgnoreCase(songs.getSongId()))
                                                    {
                                                        System.out.format("\n%-10s%-25s%-40s%-20s",count,playlist.getPlaylistName(),songs.getSongName(),"Song");
                                                        count++;
                                                    }
                                                }

                                                for(Podcast podcast: podcastList)
                                                {
                                                    if(list.getItemId().equalsIgnoreCase(podcast.getPodcastId()))
                                                    {
                                                        System.out.format("\n%-10s%-25s%-40s%-20s",count,playlist.getPlaylistName(),podcast.getPodcastName(),"Podcast");
                                                        count++;
                                                    }
                                                }
                                            }*/

                                            //Java 8 features
                                            final int[] count = {1};
                                            playlistCatalogList.stream().forEach(playlistCatalog ->
                                            {

                                                songsList.stream().filter(songs ->
                                                        playlistCatalog.getItemId().equalsIgnoreCase(songs.getSongId())).
                                                        forEach(songs->
                                                        {
                                                            System.out.format("\n%-10s%-25s%-40s%-20s", count[0],playlist.getPlaylistName(),
                                                                    songs.getSongName(), "Song");
                                                            count[0]++;
                                                        });

                                                podcastList.stream().filter(podcast ->
                                                        playlistCatalog.getItemId().equalsIgnoreCase(podcast.getPodcastId())).
                                                        forEach(podcast ->
                                                        {
                                                            System.out.format("\n%-10s%-25s%-40s%-20s", count[0],playlist.getPlaylistName(),
                                                                    podcast.getPodcastName(),"Podcast");
                                                            count[0]++;
                                                        });
                                            });

                                            System.out.println("\n--------------------------------------------------------------------------------------");
                                        }

                                    }
                                     catch (EmptySongsListException ex)
                                     {
                                         System.out.println(ex.getMessage());
                                    }
                                    catch (EmptyPlaylistException ex)
                                    {
                                        System.out.println(ex.getMessage());
                                    }
                                    catch (EmptyPodcastListException ex)
                                    {
                                        System.out.println(ex.getMessage());
                                    }

                                break;
                            case 2:     //Insert Song to Playlist

                                System.out.println("Enter The Playlist name : ");
                                String playlistName=scanner.next();
                                System.out.println("Enter the Song name : ");
                                String songName= scanner.next();
                                try
                                {
                                    Songs songs=songDaoImpl.getSong(songName);
                                    if(songs==null)
                                    {
                                        throw new NoSuchSongAvailableException(songName+" is not present in the songs list");
                                    }

                                    Playlist playlistItem = playlistDaoImpl.getPlayList(playlistName);
                                    if(playlistItem==null)
                                    {
                                        throw new NoPlaylistAvailableException(playlistName+" is not present in the Playlist");
                                    }

                                    PlaylistCatalog playlistCatalog =new PlaylistCatalog(catId,playlistItem.getPlaylistId(),songs.
                                            getSongId(),"Song");
                                    boolean insertItem= playlistCatalogDaoImpl.insertItemToPlayList(playlistCatalog);
                                    if(insertItem)
                                    {
                                        System.out.println("Song "+songs.getSongName()+"is inserted successfully");
                                    }
                                    else
                                    {
                                        System.out.println("Unable to insert song in the playlist");
                                    }
                                } catch (NoSuchSongAvailableException ex) {
                                    System.out.println(ex.getMessage());
                                } catch (NoPlaylistAvailableException ex) {
                                    System.out.println(ex.getMessage());
                                }


                                break;
                            case 3:      //Insert Podcast to Playlist
                                System.out.println("Enter the Playlist Name : ");
                                String namePlaylist=scanner.next();
                                System.out.println("Enter the Podcast Name : ");
                                String podcastName = scanner.next();
                                try
                                {
                                    Podcast podcast=podcastDaoImpl.getPodcast(podcastName);
                                    if(podcast==null)
                                    {
                                        throw new NoPodcastAvailableException(podcastName+" is not available in podcast in list");
                                    }

                                    Playlist pl = playlistDaoImpl.getPlayList(namePlaylist);
                                    if(pl==null)
                                    {
                                        throw new NoPlaylistAvailableException(namePlaylist+" is not present in the Playlist");
                                    }

                                    PlaylistCatalog insertPlaylistCatalog =new PlaylistCatalog(catId,pl.getPlaylistId(),podcast.getPodcastId(),"Podcast");
                                    boolean insertPodcast= playlistCatalogDaoImpl.insertItemToPlayList(insertPlaylistCatalog);
                                    if(insertPodcast)
                                    {
                                        System.out.println("Podcast "+podcast.getPodcastName()+"is inserted successfully");
                                    }
                                    else
                                    {
                                        System.out.println("Unable to insert Podcast in the playlist");
                                    }
                                } catch (NoPodcastAvailableException ex) {
                                    System.out.println(ex.getMessage());
                                } catch (NoPlaylistAvailableException ex) {
                                    System.out.println();
                                }

                                break;

                            case 4:   // Play song OR podcast from the playlist
                                System.out.println("********** Play music ***********");

                                String continuePlay=null;

                                do {
                                    List<Playlist> playlistList = playlistDaoImpl.getAllPlaylist();
                                    playlistDaoImpl.displayPlayList(playlistList);
                                    System.out.println("Enter your playlist name to play songs  : ");
                                    plName= scanner.next();
                                    try
                                    {
                                        Playlist nameOfPlaylist = playlistDaoImpl.getPlayList(plName);
                                        if(nameOfPlaylist==null)
                                        {
                                            throw new NoPlaylistAvailableException(plName+" is not availble in the Playlist");
                                        }
                                        List<PlaylistCatalog> pcList = playlistCatalogDaoImpl.getAllPlayListCatalog(nameOfPlaylist.getPlaylistId());
                                        if(pcList.size()==0)
                                        {
                                            throw new EmptyPlaylistException("No songs or podcast are presnt in the playlist");
                                        }
                                        else {
                                            List<Songs> allSongsList = songDaoImpl.getAllSongs();
                                            List<Podcast> podList = podcastDaoImpl.getAllPodcast();
                                            int choice;
                                            int i;
                                            do {
                                                System.out.println("Enter your Choice to play song in 1 to "+pcList.size()+" or press any other number to come out of playlist");
                                                choice = scanner.nextInt();
                                                i = 1;
                                                    for (PlaylistCatalog list : pcList) {
                                                        if (choice == i && list.getItemType().equalsIgnoreCase("song")) {
                                                            for (Songs song : allSongsList) {
                                                                if (list.getItemId().equalsIgnoreCase(song.getSongId())) {
                                                                    playMusicDAOImpl = new PlaymusicDAOImpl(song.getLocation());
                                                                    playMusicDAOImpl.MusicPlay();
                                                                    // choice++;
                                                                }
                                                            }
                                                        } else if (choice == i && list.getItemType().equalsIgnoreCase("podcast")) {
                                                            for (Podcast podcasts : podList) {
                                                                if (list.getItemId().equalsIgnoreCase(podcasts.getPodcastId())) {
                                                                    playMusicDAOImpl = new PlaymusicDAOImpl(podcasts.getLocation());
                                                                    playMusicDAOImpl.MusicPlay();
                                                                    //choice++;
                                                                }
                                                            }
                                                        }
                                                        i++;
                                                    }

                                            }while (choice<=pcList.size());
                                            System.out.println("You You Came out of Playlist.....!");
                                            System.out.println("Do you want continue with playing songs from the other playlist.....?--(yes/no)");
                                            continuePlay=scanner.next();
                                        }
                                    }
                                    catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    } catch (EmptyPlaylistException ex) {
                                        System.out.println(ex.getMessage());
                                    } catch (UnsupportedAudioFileException ex) {
                                        System.out.println(ex.getMessage());
                                    } catch (LineUnavailableException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    catch (NoPlaylistAvailableException ex)
                                    {

                                    }
                                }while(continuePlay.equalsIgnoreCase("yes"));

                                break;

                            default:
                                System.out.println("Exit from the Playlist");
                                System.out.println("......End of Playlist......");
                                break;
                        }

                    }while(playListChoice<=4);
                    break;

                default: // exit from the Jukebox
                    System.out.println("..............Exit From the User manu...............");
                    System.out.println("*********************** End of Jukebox Thank you for visiting ********************");
                    break;
            }
        }while (menuChoice <=3) ;
    }
}
