
package critex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays the review frame to add title, raing and comments to a review
 * @author Anning Xiang
 */
public class ReviewTitleFrame extends JFrame implements ActionListener {

    private JList<String> titleList;
    private JLabel ratingLabel;
    private JLabel commentLabel;
    private JTextField ratingText;
    private JTextField commentText;
    private JButton saveButton;
    private JButton quitButton;
    private Database database;
    private String authorName;
    private String type;
    private Validate validate;

    /**
     * creates the review title frame
     * @param newType
     * @param newAuthorName
     * @param newDatabase
     */
    public ReviewTitleFrame(String newType, String newAuthorName, Database newDatabase) {
        validate = new Validate();
        type = newType;
        authorName = newAuthorName;
        database = newDatabase;
        titleList = new JList<String>(database.getList(type));
        JScrollPane titleScr = new JScrollPane(titleList);
        titleScr.setViewportView(titleList);
        ratingLabel = new JLabel("Rating (1-5)");
        commentLabel = new JLabel("Comment: ");
        ratingText = new JTextField(25);
        commentText = new JTextField(25);
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");

        saveButton.addActionListener(this);
        quitButton.addActionListener(this);

        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new FlowLayout());
        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingText);

        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(new FlowLayout());
        commentPanel.add(commentLabel);
        commentPanel.add(commentText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        setLayout(new GridLayout(4, 1));
        add(titleScr);
        add(ratingPanel);
        add(commentPanel);
        add(buttonPanel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-200), (dim.height/2-150));
        setSize(400, 300);
        setVisible(true);
    }

    private JButton getQuitButton() {
        return quitButton;
    }

    private JButton getSaveButton() {
        return saveButton;
    }

    private String getTitleText() {
        return titleList.getSelectedValue();
    }

    private String getRatingText() {
        return ratingText.getText();
    }

    private String getCommentText() {
        return commentText.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getSaveButton()) {
            if (getTitleText() == null) {
                JOptionPane.showMessageDialog(null, "You need choose title");
            } else {
                if(validate.checkIntWithinRanges("rating", getRatingText(), 1, 5))
                {
                    JOptionPane.showMessageDialog(null, "Review has been created");
                    setVisible(false);
                    database.insertReview(authorName, type, getTitleText(), validate.getInt(getRatingText()), getCommentText());
                }
            }
        }
        if (e.getSource() == getQuitButton()) {
            setVisible(false);
        }
    }
}
