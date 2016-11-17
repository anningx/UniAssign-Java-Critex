
package critex;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Displays a login page when the program starts
 * @author Anning Xiang
 */
public class LoginPage extends JFrame 
{
    private JLabel nameLabel;
    private JLabel passwordLabel;
    private JTextField nameText;
    private JTextField passwordText;
    private JButton login;
    private JButton exit;
    
    /**
     * creates the log in page
     * @param l
     */
    public LoginPage (ActionListener l)
    {
        setTitle("Welcome");
        nameLabel = new JLabel("User name: ");
        passwordLabel = new JLabel("Password: ");
        nameText = new JTextField(25);
        passwordText = new JTextField(25);
        login = new JButton("Log in");
        exit = new JButton("Exit");
        
        login.addActionListener(l);
        exit.addActionListener(l);
        
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(nameLabel);
        namePanel.add(nameText);
        
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout());
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(login);
        buttonPanel.add(exit);
        
        setLayout(new GridLayout(3, 1));
        
        add(namePanel);
        add(passwordPanel);
        add(buttonPanel);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-175), (dim.height/2-100));
        setSize(350, 200);
        setVisible(true);     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * returns the inputted user name
     * @return String
     */
    public String getNameText()
    {
        return nameText.getText();
    }
    
    /**
     * returns the inputted password
     * @return String
     */
    public String getPasswordText()
    {
        return passwordText.getText();
    }
    
    /**
     * returns the log in button
     * @return JButton
     */
    public JButton getLogin()
    {
        return login;
    }
    
    /**
     * returns the exit button
     * @return JButton
     */
    public JButton getExit()
    {
        return exit;
    }
}
