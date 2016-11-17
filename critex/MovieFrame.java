
package critex;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Displays the movie frame to add movies
 * @author Anning Xiang
 */
public class MovieFrame extends JFrame implements ActionListener {

    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel releaseYearLabel;
    private JLabel genreLabel;
    private JLabel directorLabel;
    private JLabel castDescription;
    private JLabel cast1Label;
    private JLabel cast2Label;
    private JLabel cast3Label;
    private JLabel studioLabel;
    private JTextField nameText;
    private JTextField descriptionText;
    private JTextField releaseYearText;
    private JTextField genreText;
    private JTextField directorText;
    private JTextField cast1Text;
    private JTextField cast2Text;
    private JTextField cast3Text;
    private JTextField studioText;
    private JButton saveButton;
    private JButton quitButton;
    private ArrayList<String> listOfCasts;
    private Database database;
    private Validate validate;

    /**
     * creates the movie frame
     * @param newDatabase
     */
    public MovieFrame(Database newDatabase) {
        database = newDatabase;
        validate = new Validate();
        listOfCasts = new ArrayList();
        nameLabel = new JLabel("* name");
        descriptionLabel = new JLabel("Description: ");
        releaseYearLabel = new JLabel("release year: ");
        genreLabel = new JLabel("genre: ");
        directorLabel = new JLabel("Director: ");
        castDescription = new JLabel("Please input three main casts.");
        cast1Label = new JLabel("Cast 1: ");
        cast2Label = new JLabel("Cast 2: ");
        cast3Label = new JLabel("Cast 3: ");
        studioLabel = new JLabel("Studio: ");
        nameText = new JTextField(25);
        descriptionText = new JTextField(25);
        releaseYearText = new JTextField(25);
        genreText = new JTextField(25);
        directorText = new JTextField(25);
        cast1Text = new JTextField(25);
        cast2Text = new JTextField(25);
        cast3Text = new JTextField(25);
        studioText = new JTextField(25);
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");

        saveButton.addActionListener(this);
        quitButton.addActionListener(this);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(nameLabel);
        namePanel.add(nameText);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new FlowLayout());
        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionText);

        JPanel releaseYearPanel = new JPanel();
        releaseYearPanel.setLayout(new FlowLayout());
        releaseYearPanel.add(releaseYearLabel);
        releaseYearPanel.add(releaseYearText);

        JPanel genrePanel = new JPanel();
        genrePanel.setLayout(new FlowLayout());
        genrePanel.add(genreLabel);
        genrePanel.add(genreText);

        JPanel directorPanel = new JPanel();
        directorPanel.setLayout(new FlowLayout());
        directorPanel.add(directorLabel);
        directorPanel.add(directorText);

        JPanel castDescriptionPanel = new JPanel();
        castDescriptionPanel.setLayout(new FlowLayout());
        castDescriptionPanel.add(castDescription);

        JPanel cast1Panel = new JPanel();
        cast1Panel.setLayout(new FlowLayout());
        cast1Panel.add(cast1Label);
        cast1Panel.add(cast1Text);

        JPanel cast2Panel = new JPanel();
        cast2Panel.setLayout(new FlowLayout());
        cast2Panel.add(cast2Label);
        cast2Panel.add(cast2Text);

        JPanel cast3Panel = new JPanel();
        cast3Panel.setLayout(new FlowLayout());
        cast3Panel.add(cast3Label);
        cast3Panel.add(cast3Text);

        JPanel studioPanel = new JPanel();
        studioPanel.setLayout(new FlowLayout());
        studioPanel.add(studioLabel);
        studioPanel.add(studioText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        setLayout(new GridLayout(11, 1));

        add(namePanel);
        add(descriptionPanel);
        add(releaseYearPanel);
        add(genrePanel);
        add(directorPanel);
        add(castDescriptionPanel);
        add(cast1Panel);
        add(cast2Panel);
        add(cast3Panel);
        add(studioPanel);
        add(buttonPanel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-350), (dim.height/2-200));
        setSize(700, 400);
        setVisible(true);
    }

    private JButton getQuitButton() {
        return quitButton;
    }

    private JButton getSaveButton() {
        return saveButton;
    }

    private String getNameText() {
        return nameText.getText();
    }

    private String getDescriptionText() {
        return descriptionText.getText();
    }

    private String getReleaseYearText() {
        return releaseYearText.getText();
    }

    private String getGenreText() {
        return genreText.getText();
    }

    private String getDirectorText() {
        return directorText.getText();
    }

    private ArrayList getCasts() {
        listOfCasts.add(cast1Text.getText());
        listOfCasts.add(cast2Text.getText());
        listOfCasts.add(cast3Text.getText());
        return listOfCasts;
    }

    private String getStudioText() {
        return studioText.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getQuitButton()) {
            System.exit(0);
        }
        if (e.getSource() == getSaveButton()) {
            if(validate.checkString("name", getNameText()))
            {
                if(database.searchName(getNameText(), "Movie"))
                {
                    if(validate.checkIntWithinRanges("release year", getReleaseYearText(), 1, 2013))
                    {
                        JOptionPane.showMessageDialog(null, "Movie has been created successfully");
                        setVisible(false);
                        database.insertMovie(getNameText(), getDescriptionText(), validate.getInt(getReleaseYearText()), getGenreText(), getDirectorText(), cast2Text.getText(), cast2Text.getText(), cast2Text.getText(), getStudioText());
                    }
                }
            }
        }
    }
}