package com.niit.dao;

import com.niit.models.Genre;
import java.util.List;

public interface GenreDAO
{
    List<Genre> getAllGenre();
    void displayAllGenre(List<Genre> genreList);
    Genre searchByGenreName(String Genre);
    boolean insertGenre(Genre genre);
    boolean deleteGenre(String genreName);
    boolean updateGenre(Genre genre);
}
