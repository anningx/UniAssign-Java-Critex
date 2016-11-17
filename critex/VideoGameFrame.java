
package critex;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays a video game frame for adding video games
 * @author Anning Xiang
 */
public class VideoGameFrame extends JFrame implements ActionListener {

    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel releaseYearLabel;
    private JLabel genreLabel;
    private JLabel developerLabel;
    private JLabel publisherLabel;
    private JTextField nameText;
    private JTextField descriptionText;
    private JTextField releaseYearText;
    private JTextField genreText;
    private JTextField developerText;
    private JTextField publisherText;
    private JButton saveButton;
    private JButton quitButton;
    private Database database;
    private Validate validate;

    /**
     * creates the video game frame
     * @param newDatabase
     */
    public VideoGameFrame(Database newDatabase) {
        database = newDatabase;
        validate = new Validate();
        nameLabel = new JLabel("* name");
        descriptionLabel = new JLabel("Description: ");
        releaseYearLabel = new JLabel("release year: ");
        genreLabel = new JLabel("genre: ");
        developerLabel = new JLabel("Developer: ");
        publisherLabel = new JLabel("publisher: ");
        nameText = new JTextField(25);
        descriptionText = new JTextField(25);
        releaseYearText = new JTextField(25);
        genreText = new JTextField(25);
        developerText = new JTextField(25);
        publisherText = new JTextField(25);
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

        JPanel developerPanel = new JPanel();
        developerPanel.setLayout(new FlowLayout());
        developerPanel.add(developerLabel);
        developerPanel.add(developerText);

        JPanel publisherPanel = new JPanel();
        publisherPanel.setLayout(new FlowLayout());
        publisherPanel.add(publisherLabel);
        publisherPanel.add(publisherText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        setLayout(new GridLayout(7, 1));

        add(namePanel);
        add(descriptionPanel);
        add(releaseYearPanel);
        add(genrePanel);
        add(developerPanel);
        add(publisherPanel);
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

    private String getDeveloperText() {
        return developerText.getText();
    }

    private String getPublisherText() {
        return publisherText.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getQuitButton()) {
            System.exit(0);
        }
        if (e.getSource() == getSaveButton()) {
            if(validate.checkString("name", getNameText()))
            {
                if(database.searchName(getNameText(), "VIDEOGAME"))
                {
                    if(validate.checkIntWithinRanges("release year", getReleaseYearText(), 1, 2013))
                    {
                        JOptionPane.showMessageDialog(null, "Video Game has been created successfully");
                        setVisible(false);
                        database.insertVideoGame(getNameText(), getDescriptionText(), validate.getInt(getReleaseYearText()), getGenreText(), getDeveloperText(), getPublisherText());
                    }
                }
            }
        }
    }
}
