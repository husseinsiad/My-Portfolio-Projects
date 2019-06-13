/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Movies;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author siyaa
 */
public class DvdLibraryImpl implements DvdLibrarydao {
// LIBRARY_FILE variable holds all the Movies data 
    //and  the second(LIBRARY_FILE)holds for the delimiter (two colons).

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    private Map<String, Movies> myMovies = new HashMap<>();

    @Override
    public Movies addMovies(String title, Movies movies) throws DvdLibraryDaoException{
        loadLibrary();
        Movies newMovies = myMovies.put(title, movies);
            writeLibrary();
        return newMovies;
    }

    @Override
    public List<Movies> getAllMovies() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<>(myMovies.values());
    }

    @Override
    public Movies getMovies(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return myMovies.get(title);
    }

    @Override
    public Movies removeMovies(String title) throws DvdLibraryDaoException{
        loadLibrary();
        Movies removedMovies=myMovies.remove(title);
        writeLibrary();
        return removedMovies;
    }
      @Override
    public Movies editMovies(String title,Movies movies) throws DvdLibraryDaoException {
         loadLibrary();
         Movies newMovies = myMovies.put(title, movies);
            writeLibrary();
        return newMovies;
    }
    
    
    private Movies unmarshallMovies(String moviesAsText){
    // moviesAsText is expecting a line read in from our file.
    // For example, it might look like this:
    // 1234::Ada::Lovelace::Java-September1842
    //
    // We then split that line on our DELIMITER - which we are using as ::
    // Leaving us with an array of Strings, stored in moviesTokens.
    // Which should look like this:
    // ______________________________________
    // |    |    |  |       |       | |
    // |love|2019|2 |Hussein| Karmel|4|
    // |    |    |  |       |       | |
    // --------------------------------------
    //  [0]  [1]  [2]  [3]     [4]  [5]
    String[] moviesTokens = moviesAsText.split(DELIMITER);

    // Given the pattern above, the title movies is in index 0 of the array.
    String title = moviesTokens[0];

    // Which we can then use to create a new Movies object to satisfy
    // the requirements of the Movies constructor.
    Movies moviesFromFile = new Movies(title);

    // However, there are 3 remaining tokens that need to be set into the
    // new movies object. Do this manually by using the appropriate setters.

    // Index 1 - ReleaseDate
    moviesFromFile.setReleaseDate(moviesTokens[1]);

    // Index 2 - mpaaRating
    moviesFromFile.setMpaaRating(moviesTokens[2]);

    // Index 3 - directorName
    moviesFromFile.setDirectorName(moviesTokens[3]);
     // Index 3 - Studio
    moviesFromFile.setStudio(moviesTokens[4]);
     // Index 3 - userRating
    moviesFromFile.setUserRating(moviesTokens[5]);

    // We have now created a movies! Return it!
    return moviesFromFile;
}
    private void loadLibrary() throws DvdLibraryDaoException {
    Scanner scanner;

    try {
        // Create Scanner for reading the file
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(LIBRARY_FILE)));
    } catch (FileNotFoundException e) {
        throw new DvdLibraryDaoException(
                "-_- Could not load Library data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentMovies holds the most recent movies unmarshalled
    Movies currentMovies;
    // Go through LIBRARY_FILE line by line, decoding each line into a 
    // Movies object by calling the unmarshallStudent method.
    // Process while we have more lines in the file
    while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a Student
        currentMovies = unmarshallMovies(currentLine);

        // We are going to use the title as the map key for our movies object.
        // Put currentMovies into the map using movies as the key
        myMovies.put(currentMovies.getTitle(), currentMovies);
    }
    // close scanner
    scanner.close();
}
    
    private String marshallMovies(Movies aMovies){
    // We need to turn a Student object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // 4321::Charles::Babbage::Java-September1842

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the title, since that's supposed to be first.
    String moviesAsText = aMovies.getTitle() + DELIMITER;

    // add the rest of the properties in the correct order:

    // releaseDate
    moviesAsText += aMovies.getReleaseDate() + DELIMITER;

    // mpaaRating
    moviesAsText += aMovies.getMpaaRating()+ DELIMITER;
     // directorName
    moviesAsText += aMovies.getDirectorName()+ DELIMITER;
     // Studio
    moviesAsText += aMovies.getStudio()+ DELIMITER;

    // userRating - don't forget to skip the DELIMITER here.
    moviesAsText += aMovies.getUserRating();

    // We have now turned a student to text! Return it!
    return moviesAsText;
}
    
    private void writeLibrary() throws DvdLibraryDaoException {
    // NOTE FOR APPRENTICES: We are not handling the IOException - but
    // we are translating it to an application specific exception and 
    // then simple throwing it (i.e. 'reporting' it) to the code that
    // called us.  It is the responsibility of the calling code to 
    // handle any errors that occur.
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(LIBRARY_FILE));
    } catch (IOException e) {
        throw new DvdLibraryDaoException(
                "Could not save moives data.", e);
    }

    // Write out the movies objects to the roster file.
    // NOTE TO THE APPRENTICES: We could just grab the movies map,
    // get the Collection of Movies and iterate over them but we've
    // already created a method that gets a List of Movies so
    // we'll reuse it.
    String moviesAsText;
    List<Movies> moviesList = this.getAllMovies();
    for (Movies currentMovies : moviesList) {
        // turn a Student into a String
        moviesAsText = marshallMovies(currentMovies);
        // write the Student object to the file
        out.println(moviesAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}

  

}
