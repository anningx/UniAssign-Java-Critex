
package critex;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays the review frame to select and author and type to add reviews 
 * @author Anning Xiang
 */
public class ReviewFrame extends JFrame implements ActionListener
{
    private JComboBox typeBox;
    private JLabel authorLabel;
    private JList<String> authorList;
    private JLabel titleTypeLabel;
    private JButton quitButton;
    private Database database;
    private ReviewTitleFrame reviewTitleFrame;
    
    
    /**
     * creates the review frame
     * @param newDatabase
     */
    public ReviewFrame(Database newDatabase)
    {
        database = newDatabase;
        
        authorLabel = new JLabel("Author NO.");
        authorList = new JList<String>(database.getList("Author"));
        JScrollPane authorScr = new JScrollPane(authorList);
        authorScr.setViewportView(authorList);
        
        titleTypeLabel = new JLabel("TitleType:");
        String[] typeList = {"Movie", "Book", "VideoGame"};
        typeBox = new JComboBox(typeList);
        typeBox.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(authorList.getSelectedValue() == null)
                    {
                         JOptionPane.showMessageDialog( null, "You need choose author");
                    }
                    else
                    {
                        JComboBox combo = (JComboBox)e.getSource();
                        String type = (String)combo.getSelectedItem();
                        setVisible(false);
                        reviewTitleFrame = new ReviewTitleFrame(type,authorList.getSelectedValue(),database);
                    }
                }
            }
        );
      
        quitButton = new JButton("Quit");
    
        quitButton.addActionListener(this);
        
        JPanel titleTypePanel = new JPanel();
        titleTypePanel.setLayout(new FlowLayout());
        titleTypePanel.add(titleTypeLabel);
        titleTypePanel.add(typeBox);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(quitButton);
        
        setLayout(new GridLayout(4, 1));
        
        add(authorLabel);
        add(authorScr);
        add(titleTypePanel);
        add(buttonPanel);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-200), (dim.height/2-150));
        setSize(400, 300);
        setVisible(true);
    }
   
    private JButton getQuitButton()
    {
        return quitButton;
    }
    
    private String getAuthorText()
    {
        return authorList.getSelectedValue();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
           setVisible(false);
    }
}
