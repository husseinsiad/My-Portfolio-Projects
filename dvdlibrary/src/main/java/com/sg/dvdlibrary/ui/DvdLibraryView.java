/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Movies;
import java.util.List;

/**
 *
 * @author siyaa
 */
public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public Movies getNewMoviesInfo() {
        String title = io.readString("Please Enter Title");
        String releaseDate = io.readString("Please Enter Release Date");
        String mpaaRating = io.readString("Please Enter MPAA Rating");
        String directorName = io.readString("Please Enter Director Name");
        String studio = io.readString("Please Enter Studio");
        String userRating = io.readString("PLease Enter User Rating");
        Movies currentMovies = new Movies(title);
        currentMovies.setReleaseDate(releaseDate);
        currentMovies.setMpaaRating(mpaaRating);
        currentMovies.setDirectorName(directorName);
        currentMovies.setStudio(studio);
        currentMovies.setUserRating(userRating);
        return currentMovies;
    }

    public Movies getEditMoviesInfo(Movies movies) {
        String title = movies.getTitle();
//        String releaseDate = movies.getReleaseDate();
//        String mpaaRating = movies.getMpaaRating();
//        String directName = movies.getDirectorName();
//        String studio = movies.getStudio();
//        String userRating = movies.getUserRating();

        int menuSelection = 0;
        menuSelection = editMenuSelection();
        Movies currentMovies = new Movies(title);
        currentMovies.setMpaaRating(movies.getMpaaRating());
        currentMovies.setDirectorName(movies.getDirectorName());
        currentMovies.setStudio(movies.getStudio());
        currentMovies.setUserRating(movies.getUserRating());
        currentMovies.setReleaseDate(movies.getReleaseDate());

        switch (menuSelection) {
            case 1:
                String currentRDate = io.readString("Please Enter Release Date");
                currentMovies.setReleaseDate(currentRDate);
                break;
            case 2:
                String currentMpaaRating = io.readString("Please Enter MPAA Rating");
                currentMovies.setMpaaRating(currentMpaaRating);
                break;
            case 3:
                String currentDirectorName = io.readString("Please Enter Director Name");
                currentMovies.setDirectorName(currentDirectorName);
                break;
            case 4:
                String currentStudio = io.readString("Please Enter Studio");
                currentMovies.setStudio(currentStudio);
                break;
            case 5:
                String currentUserRating = io.readString("PLease Enter User Rating");
                currentMovies.setUserRating(currentUserRating);
                break;
            default:
                break;
        }

        return currentMovies;
    }

    public void displayMoviesList(List<Movies> moviestList) {
        for (Movies currentMovies : moviestList) {
            io.print(currentMovies.getTitle() + ": "
                    + currentMovies.getReleaseDate() + " " + currentMovies.getMpaaRating() + " "
                    + currentMovies.getDirectorName() + " " + currentMovies.getStudio() + " "
                    + currentMovies.getUserRating() + " ");
        }
        io.readString("Please hit enter to continue.");
    }

    public String getMoviesTitle() {
        return io.readString("PLease Enter Movies Title");
    }

    public String removeMovies() {
        return io.readString("PLease Enter Movies Title");
    }

    public void displayTitle(Movies movies) {
        if (movies != null) {
            io.print(movies.getTitle() + "  " + movies.getReleaseDate() + "  " + movies.getMpaaRating() + "  " + movies.getDirectorName()
                    + "  " + movies.getStudio() + "  " + movies.getUserRating());

            io.print("");
        } else {
            io.print("No such Title.");
        }
        io.readString("Please hit enter to continue.");
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Movie Title");
        io.print("2. Create New Movie");
        io.print("3. View a Movie");
        io.print("4. Remove a Movie");
        io.print("5. Edit a Movie");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public int editMenuSelection() {
        io.print("=== Update/Edit ===");
        io.print("1. Release Date");
        io.print("2. MPAA Rating");
        io.print("3. Director Name");
        io.print("4. Studio");
        io.print("5. User Rating");
        io.print("6. Exit");

        return io.readInt("Please select one of the above choices.", 1, 6);
    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Movies ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Movies successfully created.  Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Movies ===");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display Movies ===");
    }

    public void displayRemoveStudentBanner() {
        io.print("=== Remove Movies ===");
    }

    public void displayUpdateStudentBanner() {
        io.print("=== Edit Movies ===");
    }

    public void displayUpdateMessage() {
        io.print("=== Please Enter Movie To Update/Edit ===");
    }

    public void displayUpdateSuccessBanner() {
        io.readString("Movies successfully Updated. Please hit enter to continue.");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Movies successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
