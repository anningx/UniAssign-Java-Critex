
package critex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays the main menu
 * @author Anning Xiang
 */
public class MenuFrame extends JFrame implements ActionListener
{
   private JButton authorButton;
   private JButton titleButton;
   private JButton reviewButton;
   private JButton exitButton;
   private String option;
   private ChooseFrame chooseFrame;
   private Database database;
 
    /**
     * creates the menu frame
     * @param newDatabase
     */
    public MenuFrame(Database newDatabase)
    {
        option = "";
        database = newDatabase;
        setTitle("Welcome");
        authorButton = new JButton("Author");
        titleButton = new JButton("Title");
        reviewButton = new JButton("Review");
        exitButton = new JButton("Exit");
        
        authorButton.addActionListener(this);
        titleButton.addActionListener(this);
        reviewButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        JPanel authorPanel = new JPanel();
        authorPanel.setLayout(new FlowLayout());
        authorPanel.add(authorButton);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(titleButton);
        
        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new FlowLayout());
        reviewPanel.add(reviewButton);
        
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout());
        exitPanel.add(exitButton);
        
        setLayout(new GridLayout(4, 1));
        
        add(authorPanel);
        add(titlePanel);
        add(reviewPanel);
        add(exitPanel);
        
        setSize(350, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
     private JButton getAuthorButton()
    {
        return authorButton;
    }
    
    private JButton getTitleButton()
    {
        return titleButton;
    }
    
    private JButton getReviewButton()
    {
        return reviewButton;
    }
    
    private JButton getExitButton()
    {
        return exitButton;
    }

    private String getOption()
    {
        return option;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         try
               {
                if(e.getSource() == getAuthorButton())
                {
                    option = "author";
                    chooseFrame = new ChooseFrame(option,database);
                }
                if(e.getSource() == getTitleButton())
                {
                    option = "title";
                    chooseFrame = new ChooseFrame(option, database);
                }
                if(e.getSource() == getReviewButton())
                {
                    option = "review";
                    chooseFrame = new ChooseFrame(option, database);
                }
                if(e.getSource() == getExitButton())
                    System.exit(0);
               }
               catch(Exception exception)
               {
                    JOptionPane.showMessageDialog( null, "No action" );
               }
    }
    
    /**
     * sets the option
     * @param newOption
     */
    public void setOption(String newOption)
    {
        option = newOption;
    }
 }
