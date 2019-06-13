/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Movies;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface DvdLibrarydao {
   // It Add Movies To the DVD Libray
    Movies addMovies(String title,Movies movies)throws DvdLibraryDaoException;
     List<Movies> getAllMovies()throws DvdLibraryDaoException;
    // It Selects Movies From the DVD Libray by Using Title
    Movies getMovies(String title)throws DvdLibraryDaoException;
    // It Removes Movies From the DVD Libray by Using Title
    Movies removeMovies(String title)throws DvdLibraryDaoException;
    Movies editMovies(String title,Movies movies)throws DvdLibraryDaoException;
    
}
