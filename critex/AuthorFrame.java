
package critex;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Displays the author frame to add authors
 * @author Anning Xiang
 */
public class AuthorFrame extends JFrame implements ActionListener
{
    private JLabel nameLabel;
    private JLabel typePrefer;
    private JLabel type1;
    private JLabel type2;
    private JLabel type3;
    private JLabel ratePerReview;
    private JTextField nameText;
    private JTextField type1Text;
    private JTextField type2Text;
    private JTextField type3Text;
    private JTextField rateText;
    private JButton saveButton;
    private JButton quitButton;
    private Database database;
    private Validate validate;
    
    /**
     * Creates the author frame
     * @param newDatabase
     */
    public AuthorFrame(Database newDatabase)
    {
        database = newDatabase;
        validate = new Validate();
        setTitle("Add author");
        nameLabel = new JLabel("* Author name");
        typePrefer = new JLabel("Please enter the type you prefer to review");
        type1 = new JLabel("Type one: ");
        type2 = new JLabel("Type two: ");
        type3 = new JLabel("Type three: ");
        ratePerReview = new JLabel ("Rate Per Review");
        nameText = new JTextField(25);
        type1Text = new JTextField(25);
        type2Text = new JTextField(25);
        type3Text = new JTextField(25);
        rateText = new JTextField(25);
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");
        
        saveButton.addActionListener(this);
        quitButton.addActionListener(this);
        
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(nameLabel);
        namePanel.add(nameText);
        
        JPanel typePreferPanel = new JPanel();
        typePreferPanel.setLayout(new FlowLayout());
        typePreferPanel.add(typePrefer);
        
        JPanel type1Panel = new JPanel();
        type1Panel.setLayout(new FlowLayout());
        type1Panel.add(type1);
        type1Panel.add(type1Text);
        
        JPanel type2Panel = new JPanel();
        type2Panel.setLayout(new FlowLayout());
        type2Panel.add(type2);
        type2Panel.add(type2Text);
        
        JPanel type3Panel = new JPanel();
        type3Panel.setLayout(new FlowLayout());
        type3Panel.add(type3);
        type3Panel.add(type3Text);
       
        JPanel ratePanel = new JPanel();
        ratePanel.setLayout(new FlowLayout());
        ratePanel.add(ratePerReview);
        ratePanel.add(rateText);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);
        
        setLayout(new GridLayout(7, 1));
        
        add(namePanel);
        add(typePreferPanel);
        add(type1Panel);
        add(type2Panel);
        add(type3Panel);
        add(ratePanel);
        add(buttonPanel);
        
        setSize(700, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-350), (dim.height/2-200));
        setVisible(true);
    }
    
    private JButton getQuitButton()
    {
        return quitButton;
    }
    
    private JButton getSaveButton()
    {
        return saveButton;
    }
    
    private String getNameText()
    {
        return nameText.getText();
    }
    
    private ArrayList<String> getListOfTypes()
    {
        ArrayList<String> listOfTypes =  new ArrayList();
        listOfTypes.add(type1Text.getText());
        listOfTypes.add(type2Text.getText());
        listOfTypes.add(type3Text.getText());
        return listOfTypes;
    }
    
    private String getRate()
    {
        return rateText.getText();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == getQuitButton())
        {
            this.setVisible(false);
        }
        if(e.getSource() == getSaveButton())
        {
            if(validate.checkString("name", getNameText()))
            {
                if(database.searchName(getNameText(), "AUTHOR"))
                {
                    if(validate.checkFloat("rate", getRate()))
                    {
                        JOptionPane.showMessageDialog( null, "Author has been created successfully");
                        setVisible(false);
                        database.insertAuthor(getNameText(), type1Text.getText(), type2Text.getText(), type3Text.getText(), validate.getFloat(getRate()));
                    }
                }
            }
        }   
    }
}
