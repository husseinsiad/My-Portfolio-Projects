/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dao.DvdLibrarydao;
import com.sg.dvdlibrary.dto.Movies;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author siyaa
 */
public class DvdLibraryController {

    // UserIO io = new UserIOConsoleImpl();
    DvdLibraryView view;
    DvdLibrarydao dao;

    public DvdLibraryController(DvdLibrarydao dao, DvdLibraryView view) {
        this.view = view;
        this.dao = dao;
    }

    public void createMovies() throws DvdLibraryDaoException {
        // Display Message
        view.displayCreateStudentBanner();
        //Get new Movies Info From DvdLibrary View
        Movies movies = view.getNewMoviesInfo();
        // Call Data Access to Add New Movies into File/Database
        dao.addMovies(movies.getTitle(), movies);
        // Display Message
        view.displayCreateSuccessBanner();

    }

    private void listMovies() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Movies> moviesList = dao.getAllMovies();
        view.displayMoviesList(moviesList);
        view.displayDisplayStudentBanner();
    }

    public void getMoviesTitle() throws DvdLibraryDaoException {
        view.displayDisplayStudentBanner();
        String title = view.getMoviesTitle();
        Movies matchingMovie = dao.getMovies(title);
        view.displayTitle(matchingMovie);
    }

    public void RemoveMovies() throws DvdLibraryDaoException {
        view.displayRemoveStudentBanner();
        String title = view.removeMovies();
        dao.removeMovies(title);
        view.displayRemoveSuccessBanner();
    }

    public void EditMovies() throws DvdLibraryDaoException {
        view.displayUpdateStudentBanner();
         String getTitleInfo = view.getMoviesTitle();
          Movies matchingMovies= dao.getMovies(getTitleInfo);
         Movies currentMovie=view.getEditMoviesInfo(matchingMovies);
        // view.displayUpdateMessage();
        // Movies currentMovie=view.getNewMoviesInfo();
         dao.editMovies(currentMovie.getTitle(),currentMovie);
          view.displayUpdateSuccessBanner();

    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listMovies();
                        break;
                    case 2:
                        createMovies();
                        break;
                    case 3:
                        getMoviesTitle();
                        break;
                    case 4:
                        RemoveMovies();
                        break;
                    case 5:
                        EditMovies();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:

                }

            }
            extMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void extMessage() {

        view.displayExitBanner();
    }

}
