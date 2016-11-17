
package critex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays the book frame to add books
 * @author Anning Xiang
 */
public class BookFrame extends JFrame implements ActionListener {

    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel releaseYearLabel;
    private JLabel genreLabel;
    private JLabel bookAuthorLabel;
    private JLabel publisherLabel;
    private JLabel pageCountLabel;
    private JTextField nameText;
    private JTextField descriptionText;
    private JTextField releaseYearText;
    private JTextField genreText;
    private JTextField bookAuthorText;
    private JTextField publisherText;
    private JTextField pageCountText;
    private JButton saveButton;
    private JButton quitButton;
    private Database database;
    private Validate validate;

    /**
     * creates the book frame
     * @param newDatabase
     */
    public BookFrame(Database newDatabase) {
        database = newDatabase;
        validate = new Validate();
        nameLabel = new JLabel("* name");
        descriptionLabel = new JLabel("Description: ");
        releaseYearLabel = new JLabel("release year: ");
        genreLabel = new JLabel("genre: ");
        bookAuthorLabel = new JLabel("Author: ");
        publisherLabel = new JLabel("publisher: ");
        pageCountLabel = new JLabel("Page Count: ");
        nameText = new JTextField(25);
        descriptionText = new JTextField(25);
        releaseYearText = new JTextField(25);
        genreText = new JTextField(25);
        bookAuthorText = new JTextField(25);
        publisherText = new JTextField(25);
        pageCountText = new JTextField(25);
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

        JPanel bookAuthorPanel = new JPanel();
        bookAuthorPanel.setLayout(new FlowLayout());
        bookAuthorPanel.add(bookAuthorLabel);
        bookAuthorPanel.add(bookAuthorText);

        JPanel publisherPanel = new JPanel();
        publisherPanel.setLayout(new FlowLayout());
        publisherPanel.add(publisherLabel);
        publisherPanel.add(publisherText);

        JPanel pageCountPanel = new JPanel();
        pageCountPanel.setLayout(new FlowLayout());
        pageCountPanel.add(pageCountLabel);
        pageCountPanel.add(pageCountText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        setLayout(new GridLayout(8, 1));

        add(namePanel);
        add(descriptionPanel);
        add(releaseYearPanel);
        add(genrePanel);
        add(bookAuthorPanel);
        add(publisherPanel);
        add(pageCountPanel);
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

    private String getBookAuthorText() {
        return bookAuthorText.getText();
    }

    private String getPublisherText() {
        return publisherText.getText();
    }

    private String getPageCountText() {
        return pageCountText.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getQuitButton()) {
            System.exit(0);
        }
        if (e.getSource() == getSaveButton()) {
            if(validate.checkString("name", getNameText()))
            {
                if(database.searchName(getNameText(), "Book"))
                {
                    if(validate.checkIntWithinRanges("release year", getReleaseYearText(), 1, 2013))
                    {
                        if(validate.checkInt("page count", getPageCountText()))
                        {
                            JOptionPane.showMessageDialog(null, "Book has been created successfully");
                            setVisible(false);
                            database.insertBook(getNameText(), getDescriptionText(), validate.getInt(getReleaseYearText()), getGenreText(), getBookAuthorText(), getPublisherText(), validate.getInt(getPageCountText()));
                        }
                    }
                }
            }
        }
    }
}
