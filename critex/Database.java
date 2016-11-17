
package critex;

import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * This class communicates with a database that contains an information of Titles, Authors and Reviews.
 * @author Anning Xiang
 */
public class Database implements Serializable 
{
    private Connection c;
    private Statement stmt;
    private PreparedStatement prepStmt;
    
    /**
     * Constructor to create a new database.
     */
    public Database()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * returns a list of authors and their details
     * @return Object[][]
     */
    public Object[][] getAuthorArray()
    {
        Object[][] author = null;
        try 
        {
            int rows = countRows("AUTHOR");
            ResultSet rs = selectTable("AUTHOR");
            author = new Object[rows][8];
            int i = 0;
            while(rs.next())
            {
                author[i][0] = i;
                author[i][1] = rs.getInt(1);
                author[i][2] = rs.getString(2);
                author[i][3] = rs.getString(3);
                author[i][4] = rs.getString(4);
                author[i][5] = rs.getString(5);
                author[i][6] = rs.getFloat(6);
                author[i][7] = rs.getFloat(7);
                i++;
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return author;
    }
    
    /**
     * returns a list of reviews
     * @param table
     * @return String[]
     */
    public String[] getList(String table)
    {
        String[] name = null;
        try 
        {
            if(table.equalsIgnoreCase("Review"))
            {
                int row = countRows(table);
                ResultSet rs = selectTable(table);
                name = new String[row];
                int i = 0;
                while(rs.next())
                {
                    String authorName = rs.getString("AUTHOR_NAME");
                    String type = rs.getString("TYPE");
                    String titleName = rs.getString("TITLE_NAME");
                    name[i] = authorName + "; " + type + "; " + titleName;
                    i++;
                }
            }
            else
            {
                int row = countRows(table);
                ResultSet rs = selectTable(table);
                name = new String[row];
                int i = 0;
                while(rs.next())
                {
                   name[i] = rs.getString("NAME");
                    i++;
                }
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return name;
    }
    
    /**
     * returns a lis of column names for displaying authors
     * @return String[]
     */
    public String[] getAuthorColumnName()
    {
        String[] colunmName = {"NO.", "ID","Name", "Type1", "Type2", "Type3", "Rate per Review", "Not Paid Rate"};
        return colunmName;
    }

     /**
     * inserts an author into the database
     * @param name
     * @param type1
     * @param type2
     * @param type3
     * @param ratePerReview
     */
    public void insertAuthor(String name, String type1, String type2, String type3, float ratePerReview)
    {
        float notPaidRate = 0;
        try {
            int ID = stmt.executeQuery("SELECT MAX(ID) FROM AUTHOR").getInt(1) + 1;
            String sql = "INSERT INTO AUTHOR VALUES  ('" + ID + "' , '" +  name + "' , '" + type1 + "' , '" + type2 + "' ,'"  + type3 + 
                    "' , '" + ratePerReview + "' , '" + notPaidRate + "');"; 
            stmt.executeUpdate(sql);
            c.commit();
                
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
     /**
     * inserts aa movie into the database
     * @param name
     * @param description
     * @param releaseYear
     * @param genre
     * @param director
     * @param cast1
     * @param cast2
     * @param cast3
     * @param studio
     */
    public void insertMovie(String name, String description, int releaseYear, String genre, String director, 
             String cast1, String cast2, String cast3, String studio)
    {
        try 
        {
            float averageRate = 0;
            String sql = "INSERT INTO MOVIE VALUES ('" + name + "','" + description + "','" + releaseYear + "','" + 
                    averageRate + "','" + genre + "','" + director + "','" + cast1 + "','" + cast2 + "','" + cast3 +
                    "','" + studio + "');"; 
            stmt.executeUpdate(sql);
            c.commit();     
        } 
        catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * returns a list of movies and their details
     * @return Object[][]
     */ 
    public Object[][] getMovieArray()
    {
        Object[][] movie = null;
        try 
        {
            int rows = countRows("MOVIE");
            ResultSet rs = selectTable("MOVIE");
            movie = new Object[rows][11];
            int i = 0;
            while(rs.next())
            {
                movie[i][0] = i;
                movie[i][1] = rs.getString(1);
                movie[i][2] = rs.getString(2);
                movie[i][3] = rs.getInt(3);
                movie[i][4] = rs.getFloat(4);
                movie[i][5] = rs.getString(5);
                movie[i][6] = rs.getString(6);
                movie[i][7] = rs.getString(7);
                movie[i][8] = rs.getString(8);
                movie[i][9] = rs.getString(9);
                movie[i][10] = rs.getString(10);
                i++;
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return movie;
    }
     
     /**
     * returns a list of column names for displaying movies
     * @return
     */
    public String[] getMovieColunmName()
     {
         String[] colunmName = {"NO.", "Name", "Description", "Release Year", "Average rate", "Genre", "Director", "Cast1", "Cast2", "Cast3", "Studio"};
         return colunmName;
     }

    /**
     * inserts a book into the database
     * @param name
     * @param description
     * @param releaseYear
     * @param genre
     * @param bookAuthor
     * @param publisher
     * @param pageCount
     */
    public void insertBook(String name, String description, int releaseYear, String genre, String bookAuthor, String publisher, int pageCount)
    {
        try 
        {
            float averageRate = 0;
            String sql = "INSERT INTO BOOK VALUES ('" + name + "','" + description + "','" + releaseYear + "','" + 
                    averageRate + "','" + genre + "','" + bookAuthor + "','" + publisher + "','" + pageCount + "');";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * returns a list of books and their details
     * @return Object[][]
     */
    public Object[][] getBookArray()
    {
        Object[][] Book = null;
        try 
        {
            int rows = countRows("BOOK");
            ResultSet rs = selectTable("BOOK");
            Book = new Object[rows][9];
            int i = 0;
            while(rs.next())
            {
                Book[i][0] = i;
                Book[i][1] = rs.getString(1);
                Book[i][2] = rs.getString(2);
                Book[i][3] = rs.getInt(3);
                Book[i][4] = rs.getFloat(4);
                Book[i][5] = rs.getString(5);
                Book[i][6] = rs.getString(6);
                Book[i][7] = rs.getString(7);
                Book[i][8] = rs.getInt(8);
                i++;
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return Book;
    }
     
     /**
     * returns a list of column names for displaying books
     * @return
     */
    public String[] getBookColunmName()
     {
         String[] colunmName = {"NO.","Name", "Description", "Release Year", "Average Rate", "Genre", "Author", "Publisher", "Page"};
         return colunmName;
     }

        
    /**
     * inserts a video game into the database
     * @param name
     * @param description
     * @param releaseYear
     * @param genre
     * @param developer
     * @param publisher
     */
    public void insertVideoGame(String name, String description, int releaseYear, String genre, String developer, String publisher)
    {
        float averageRate = 0;
        try 
        {
            String sql = "INSERT INTO VIDEOGAME VALUES ('" + name + "','" + description + "','" + releaseYear + "','" + 
                    averageRate + "','" + genre + "','" + developer + "','" + publisher + "');"; 
            stmt.executeUpdate(sql);
            c.commit();
                
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * returns a list of video games and their details
     * @return Object[][]
     */
    public Object[][] getVideoGameArray()
    {
        Object[][] VideoGame = null;
        try 
        {
            int rows = countRows("VIDEOGAME");
            ResultSet rs = selectTable("VIDEOGAME");
            VideoGame = new Object[rows][8];
            int i = 0;
            while(rs.next())
            {
                VideoGame[i][0] = i;
                VideoGame[i][1] = rs.getString(1);
                VideoGame[i][2] = rs.getString(2);
                VideoGame[i][3] = rs.getInt(3);
                VideoGame[i][4] = rs.getFloat(4);
                VideoGame[i][5] = rs.getString(5);
                VideoGame[i][6] = rs.getString(6);
                VideoGame[i][7] = rs.getString(7);
                i++;
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return VideoGame;
    }
     
     /**
     * returns a list of column names for displaying video games
     * @return
     */
    public String[] getVideoGameColunmName()
     {
         String[] colunmName = {"NO.", "Name", "Description", "Release Year", "Average Rate", "Genre", "Developer", "Publisher"};
         return colunmName;
     }
    
     /**
     * inserts a review into the database
     * @param authorName
     * @param type
     * @param titleName
     * @param rating
     * @param comment
     */
    public void insertReview(String authorName, String type, String titleName, int rating, String comment)
    {
        try 
        {
            boolean isPaid = false;        
            String sql = "INSERT INTO REVIEW VALUES ('" + authorName + "','" + type + "','" + titleName +
                            "','" + rating + "','" + comment + "','" + isPaid + "');"; 
            stmt.executeUpdate(sql);
            
            String sql1 = "SELECT RATE_PER_REVIEW, NOT_PAID_RATE FROM AUTHOR WHERE NAME = '" + authorName + "';";
            ResultSet rs = stmt.executeQuery(sql1);
            float ratePerReview = rs.getFloat("RATE_PER_REVIEW");
            float notPaid = rs.getFloat("NOT_PAID_RATE");
            notPaid = notPaid + ratePerReview;
            updateAuthor(authorName, "Not_Paid_Rate", notPaid + "");
            
            String sql2 = "SELECT SUM(RATING) AS S, COUNT(RATING) AS NUMBER FROM REVIEW WHERE TYPE = '" + type + "' AND TITLE_NAME = '" + titleName + "';";
            ResultSet rs1 = stmt.executeQuery(sql2);
            int s = rs.getInt("S");
            int n = rs.getInt("NUMBER");
            float newRate = (float)((s + rating) / (n + 1));
            updateTitle(type,titleName, "AVERAGE_RATE", newRate + "");
            c.commit();
        } 
        catch ( Exception e ) 
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * returns a list of reviews and their details
     * @return Object [][]
     */
    public Object[][] getReviewArray()
    {
        Object[][] review = null;
        try 
        {
            int rows = countRows("REVIEW");
            ResultSet rs = selectTable("REVIEW");
            review = new Object[rows][7];
            int i = 0;
            while(rs.next())
            {
                review[i][0] = i;
                review[i][1] = rs.getString(1);
                review[i][2] = rs.getString(2);
                review[i][3] = rs.getString(3);
                review[i][4] = rs.getInt(4);
                review[i][5] = rs.getString(5);
                review[i][6] = rs.getBoolean(6);
                i++;
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return review;
    }
    
    /**
     * returns a list of column names for displaying reviews
     * @return
     */
    public String[] getReviewColumnName()
    {
        String[] colunmName = {"NO.", "Author Name", "Type", "Title Name", "Rating","Comment", "Paid?"};
        return colunmName;
    }
    
    /**
     * updates the database for paying an author once
     * @param isPaid
     * @param name
     */
    public void payAuthor(boolean isPaid, String name)
    {
        String[] s = name.split(";");
        String authorName = s[0].trim();
        updateReview(authorName, s[1].trim(), s[2].trim(), "PAID", isPaid + "");
        try
        {
             ResultSet rs = stmt.executeQuery("SELECT RATE_PER_REVIEW, NOT_PAID_RATE FROM AUTHOR WHERE NAME = '" + authorName + "';");
             float ratePerReview = rs.getFloat("RATE_PER_REVIEW");
             float notPaid = rs.getFloat("NOT_PAID_RATE");
             notPaid = notPaid - ratePerReview;
             updateAuthor(authorName, "NOT_PAID_RATE", notPaid + "");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
   
    /**
     * updates a value in the author table for a given author
     * @param authorName
     * @param attribute
     * @param newValue
     */
    public void updateAuthor (String authorName, String attribute, String newValue)
    {
        try 
        {
            String sql = "UPDATE AUTHOR " + 
                         "SET " + attribute + " = '" + newValue + 
                         "' WHERE NAME = '" + authorName + "'";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * removes an author from the database
     * @param authorName
     */
    public void removeAuthor(String authorName)
    {
        try {
            String sql = "DELETE FROM AUTHOR WHERE NAME = " + authorName;
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * updates a value in the title table for a given title
     * @param type
     * @param name
     * @param attribute
     * @param newValue
     */
    public void updateTitle (String type, String name, String attribute, String newValue)
    {
        try {
            String sql = "UPDATE " + type  + 
                         " SET " + attribute + " = '" + newValue + 
                         "' WHERE NAME = '" + name + "';";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }    
    
    /**
     * removes one title from the database
     * @param type
     * @param name
     */
    public void removeTitle (String type, String name)
    {
        try {
            if(type.equalsIgnoreCase("Review"))
            {
                String[] s = name.split(";");
                removeReview(s[0].trim(), s[1].trim(), s[2].trim());
            }
            else
            {
                String sql = "DELETE FROM " + type + " WHERE NAME = '" + name + "';";
                stmt.executeUpdate(sql);
                c.commit();
            }
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }    
    
   
    
    /**
     * updates a value in the review table for a given review
     * @param author
     * @param type
     * @param title
     * @param attribute
     * @param newValue
     */
    public void updateReview (String author, String type, String title, String attribute, String newValue)
    {
        try {
            int binary = 0;
            if (Boolean.parseBoolean(newValue))
                binary = 1;
            String sql = "UPDATE REVIEW " + 
                         "SET " + attribute + " = '" + binary + 
                         "' WHERE AUTHOR_NAME = '" + author + "' AND TYPE = '" + type + "' AND TITLE_NAME = '" + title + "';";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * remove a review from the database
     * @param author
     * @param type
     * @param title
     */
    public void removeReview (String author, String type, String title)
    {
        try {
            String sql = "DELETE FROM REVIEW " + 
                         " WHERE AUTHOR_NAME = '" + author + "' AND TYPE = '" + type + "' AND TITLE_NAME = '" + title + "';";
            int i = stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * returns a list of reviews that have not been paid
     * @return
     */
    public String[] getNotPaidReview()
    {
        String[] notPaid = null; 
        try
        {
            ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS NUMBER FROM REVIEW WHERE PAID = 'false'");
            int row = r.getInt("NUMBER");
            String sql = "SELECT AUTHOR_NAME, TYPE, TITLE_NAME, COUNT(*) AS NUMBER FROM REVIEW WHERE PAID = 'false';";
            ResultSet rs = stmt.executeQuery(sql);
            notPaid = new String[row];
            int i = 0;
            while(rs.next())
            {
                String authorName = rs.getString("AUTHOR_NAME");
                String type = rs.getString("TYPE");
                String titleName = rs.getString("TITLE_NAME");
                notPaid[i] = authorName + "; " +  type + "; " + titleName;
                i++;
            }
        }
        catch(Exception e){
        }
        return notPaid;
    }
    
    /**
     * select one specific table entirely
     * @param table
     * @return ResultSet
     */
    public ResultSet selectTable(String table)
    {
        ResultSet rs = null;        
        try {
            String sql = "Select * FROM " + table;
            rs = stmt.executeQuery(sql);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return rs;
    }
    
    /**
     * returns the row count from a table in he database
     * @param table
     * @return int
     */
    public int countRows(String table)
    {
        ResultSet rs = null;   
        int rowCount = 0;
        try {
            String sql = "Select count(*) FROM " + table;
            rs = stmt.executeQuery(sql);
            rowCount = rs.getInt(1);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return rowCount;
    }
    
    /**
     * searches whether a title exists in the database
     * @param name
     * @param type
     * @return boolean
     */
    public boolean searchName(String name, String type)
    {
        boolean b = true;
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS NUMBER FROM " + type + " WHERE NAME = '" + name + "';");
            int s = rs.getInt("NUMBER");
            if(s != 0)
            {
                b = false;
                JOptionPane.showMessageDialog(null, name + " has already existed.");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            b = false;
        }
        return b;
    }
    
    /**
     * returns the connection object of the database
     * @return Connection
     */
    public Connection getConnection()
    {
        return c;
    }
    
    /**
     * returns the statement object from the database
     * @return Statement
     */
    public Statement getStatement()
    {
        return stmt;
    }
    
    /**
     * closes the connection between java and the database
     */
    public void closeConnection()
    {
        try {
            c.close();
            stmt.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
}