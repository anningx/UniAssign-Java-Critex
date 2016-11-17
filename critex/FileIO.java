
package critex;

import javax.swing.JOptionPane;

/**
 * to set up tables in the database
 * @author Anning Xiang
 */
public class FileIO 
{
    private Database aDatabase;

    /**
     * Create a new object of FileIO.
     * @param newDatabase 
     */
    public FileIO(Database newDatabase)
    {
        aDatabase = newDatabase;   
    }
    
    /**
     * check whether tables exist in the database and create them if they don't
     */
    public void checkAndCreateTables()
    {
        try
        {
            aDatabase.getStatement().executeQuery("SELECT * FROM AUTHOR");
        }
        catch (Exception e)
        {
            createAuthorTable();
        }
        try
        {
            aDatabase.getStatement().executeQuery("SELECT * FROM MOVIE");
        }
        catch (Exception e)
        {
            createMovieTable();
        }
        try
        {
            aDatabase.getStatement().executeQuery("SELECT * FROM BOOK");
        }
        catch (Exception e)
        {
            createBookTable();
        }
        try
        {
            aDatabase.getStatement().executeQuery("SELECT * FROM VIDEOGAME");
        }
        catch (Exception e)
        {
            createVideoGameTable();
        }
        try
        {
            aDatabase.getStatement().executeQuery("SELECT * FROM REVIEW");
        }
        catch (Exception e)
        {
            createReviewTable();
        }
    }

    private void createAuthorTable()
    {
        try {
            String sql = "CREATE TABLE AUTHOR " +
                         "(ID INT PRIMARY KEY     NOT NULL," +
                         " NAME TEXT NOT NULL, " + 
                         " TYPE1           TEXT, " + 
                         " TYPE2        TEXT, " + 
                         " TYPE3         TEXT, " + "RATE_PER_REVIEW  FLOAT NOT NULL, " + "NOT_PAID_RATE  FLOAT NOT NULL)"; 
            aDatabase.getStatement().executeUpdate(sql);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    private void createMovieTable()
    {
        try {
            String sql = "CREATE TABLE MOVIE " +
                         "(NAME TEXT PRIMARY KEY NOT NULL," +
                         " DESCRIPTION TEXT, " + 
                         " RELEASE_YEAR INT NOT NULL, " +
                         "AVERAGE_RATE FLOAT NOT NULL," +
                         " GENRE TEXT NOT NULL, " + 
                         " DIRECTOR TEXT NOT NULL, " + "CAST1  TEXT, " + "CAST2 TEXT, " + "CAST3 TEXT, " + "STUDIO  TEXT NOT NULL)"; 
            aDatabase.getStatement().executeUpdate(sql);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    private void createBookTable()
    {
        try {
            
            String sql = "CREATE TABLE BOOK " +
                         "(NAME TEXT PRIMARY KEY NOT NULL," +
                         " DESCRIPTION TEXT, " + 
                         " RELEASE_YEAR INT NOT NULL, " + 
                         "AVERAGE_RATE FLOAT NOT NULL," +
                         " GENRE TEXT NOT NULL, " + 
                         " BOOK_AUTHOR TEXT NOT NULL, " + "PUBLISHER  TEXT NOT NULL, " + "PAGE_COUNT INT NOT NULL)"; 
            aDatabase.getStatement().executeUpdate(sql);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    private void createVideoGameTable()
    {
        try {
            String sql = "CREATE TABLE VIDEOGAME " +
                         "(NAME TEXT PRIMARY KEY NOT NULL," +
                         " DESCRIPTION TEXT, " + 
                         " RELEASE_YEAR INT NOT NULL, " + 
                         "AVERAGE_RATE FLOAT NOT NULL," +
                         " GENRE TEXT NOT NULL, " + 
                         " DEVELOPER TEXT NOT NULL, " + "PUBLISHER  TEXT NOT NULL)"; 
            aDatabase.getStatement().executeUpdate(sql);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    private void createReviewTable()
    {
        try {
            
            String sql = "CREATE TABLE REVIEW " +
                         "(AUTHOR_NAME TEXT NOT NULL," +
                         " TYPE TEXT NOT NULL, " + 
                         " TITLE_NAME TEXT NOT NULL, " + 
                         " RATING INT NOT NULL, " + 
                         " COMMENT TEXT, " + "PAID  BOOLEAN NOT NULL)"; 
            aDatabase.getStatement().executeUpdate(sql);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
