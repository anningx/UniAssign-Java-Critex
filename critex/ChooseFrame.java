
package critex;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Displays the choose frame to select author, title or review
 * @author Anning Xiang
 */
public class ChooseFrame extends JFrame implements ActionListener 
{
   private JButton addButton;
   private JButton viewButton;
   private JButton exitButton;
   private String option;
   private AuthorFrame authorFrame;
   private TitleTypeFrame titleType;
   private ViewFrame viewFrame;
   private ReviewFrame reviewFrame;
   private String choice;
   private Database database;
    
    /**
     * creates the choose frame
     * @param newChoice
     * @param newDatabase
     */
    public ChooseFrame(String newChoice, Database newDatabase)
    {
        choice = newChoice;
        database = newDatabase;
        option = "";
        setTitle("Welcome");
        addButton = new JButton("Add a "+ choice );
        viewButton = new JButton("View and Remove " + choice + "s");
        exitButton = new JButton("Exit");
        
        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new FlowLayout());
        addPanel.add(addButton);

        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new FlowLayout());
        viewPanel.add(viewButton);
        
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout());
        exitPanel.add(exitButton);
 
        setLayout(new GridLayout(4, 1)); 
        
        add(addPanel);
        add(viewPanel);
        add(exitPanel);
        
        setSize(350, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-175), (dim.height/2-100));
        setVisible(true);
    }
    
     private JButton getAddButton()
    {
        return addButton;
    }
    
    private JButton getViewButton()
    {
        return viewButton;
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
            if(e.getSource() == getAddButton())
            {
                if(choice.equalsIgnoreCase("author"))
                {
                    setVisible(false);
                    authorFrame = new AuthorFrame(database);
                }
                if(choice.equalsIgnoreCase("title"))
                {
                    setVisible(false);
                    titleType= new TitleTypeFrame(database, "add");
                }
                if(choice.equalsIgnoreCase("review"))
                {
                    setVisible(false);
                    reviewFrame= new ReviewFrame(database);
                }
            }
            if(e.getSource() == getViewButton())
            {
                if(choice.equalsIgnoreCase("author"))
                {
                    if(database.countRows(choice) == 0)
                    {
                        JOptionPane.showMessageDialog( null, "Sorry, no author here, please add");
                    }
                    else
                    {
                        setVisible(false);
                       viewFrame = new ViewFrame(database, "author");
                       viewFrame.createAndShowGUI(database, "author", "view");
                    }
                }
                 if(choice.equalsIgnoreCase("title"))
                 {
                     setVisible(false);
                    titleType= new TitleTypeFrame(database, "view"); 
                 }
                    if(choice.equalsIgnoreCase("review"))
                 {
                     if(database.countRows(choice) == 0)
                    {
                        JOptionPane.showMessageDialog( null, "Sorry, no review here, please add");
                    }
                     else
                     {
                        setVisible(false);
                        viewFrame = new ViewFrame(database, "review");
                        viewFrame.createAndShowGUI(database, "review", "view");
                     }
                 }
             }
            if(e.getSource() == getExitButton())
                setVisible(false);

           }
           catch(Exception exception)
           {
                JOptionPane.showMessageDialog( null, exception.getMessage() );
           }
    }
}
