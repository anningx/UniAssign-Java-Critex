
package critex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *This class is for user to operate
 * @author Anning Xiang
 */
public class MainDriver implements ActionListener
{
    private Database database;
    private MenuFrame menuFrame;
    private LoginPage login;
    private Validate validate;

    /**
     * Allows user to initialise the program without first creating any object.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        MainDriver critex = new MainDriver();
        critex.checkLoginTable();
    }
    
    /**
     * Create a new driver. 
     */
    public MainDriver() 
    {
        validate = new Validate();
        database = new Database();
        login = new LoginPage(this);
    }
    
    /**
     * This method is for user to display the main menu and choose the option.
     */
    public void displayMainMenu()
    {
       menuFrame = new MenuFrame(database);
    }
    
    /**
     *
     */
    public void exit()
    {
        database.closeConnection();
    }

    private void createLog()
    {
         try {
                String sql = "CREATE TABLE LOGIN " +
                         "(USERNAME TEXT PRIMARY KEY NOT NULL," +
                         " PASSWORD TEXT NOT NULL)"; 
                database.getStatement().executeUpdate(sql);
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    private void insertUser()
    {
        try 
        {
            String sql = "INSERT INTO LOGIN VALUES ('USER001', '123456');"; 
            database.getStatement().executeUpdate(sql);
            
            String sql1 = "INSERT INTO LOGIN VALUES ('a', 'a');";
            database.getStatement().executeUpdate(sql1);
            database.getConnection().commit();
                
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    /**
     * check whether the login table exist in the database and create it if needed
     */
    public void checkLoginTable()
    {
        try
        {
            database.getStatement().executeQuery("SELECT * FROM LOGIN");
        }
        catch (Exception e)
        {
            createLog();
            insertUser();
        }
    }
    
    /**
     * retrieves the password from the database
     * @param name
     * @return
     */
    public String getPassword(String name)
    {
        String password = "";
        try
        {
            String sql = "SELECT PASSWORD FROM LOGIN WHERE USERNAME = '" + name + "';";
            ResultSet rs = database.getStatement().executeQuery(sql);
            password = rs.getString("PASSWORD");
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        return password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login.getLogin())
        {
            if(validate.checkString("User name", login.getNameText()))
            {
                if(validate.checkString("Password", login.getPasswordText()) )
                {
                    if(login.getPasswordText().equalsIgnoreCase(getPassword(login.getNameText())))
                    {
                        JOptionPane.showMessageDialog( null, "Welcome");
                        login.setVisible(false);
                        FileIO io = new FileIO(database);
                        io.checkAndCreateTables();
                        displayMainMenu();
                    }
                    else
                        JOptionPane.showMessageDialog( null, "The name and password are incorrect");
                }
            }
        }
        if(e.getSource() == login.getExit())
        {
            JOptionPane.showMessageDialog( null, "Bye-bye");
            System.exit(0);
        }
    }
}