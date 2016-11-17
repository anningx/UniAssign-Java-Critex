
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
 * Displays a frame for choosing the type of title
 * @author Anning Xiang
 */
public class TitleTypeFrame extends JFrame implements ActionListener {

    private JButton movieButton;
    private JButton bookButton;
    private JButton videoGameButton;
    private Database database;
    private MovieFrame movieFrame;
    private BookFrame bookFrame;
    private VideoGameFrame videoGameFrame;
    private ViewFrame viewFrame;
    private String s;

    /**
     * displays the title type frame
     * @param newDatabase
     * @param s1
     */
    public TitleTypeFrame(Database newDatabase, String s1) {
        s = s1;
        database = newDatabase;
        setTitle("Choose the title type");
        movieButton = new JButton("Movie");
        bookButton = new JButton("Book");
        videoGameButton = new JButton("Video Game");

        movieButton.addActionListener(this);
        bookButton.addActionListener(this);
        videoGameButton.addActionListener(this);

        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new FlowLayout());
        moviePanel.add(movieButton);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new FlowLayout());
        bookPanel.add(bookButton);

        JPanel videoGamePanel = new JPanel();
        videoGamePanel.setLayout(new FlowLayout());
        videoGamePanel.add(videoGameButton);

        setLayout(new GridLayout(3, 1));

        add(moviePanel);
        add(bookPanel);
        add(videoGamePanel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-150), (dim.height/2-100));
        setSize(300, 200);
        setVisible(true);
    }

    private JButton getMovieButton() {
        return movieButton;
    }

    private JButton getBookButton() {
        return bookButton;
    }

    private JButton getVideoGameButton() {
        return videoGameButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (s.equalsIgnoreCase("add")) {
            if (e.getSource() == getMovieButton()) {
                setVisible(false);
                movieFrame = new MovieFrame(database);
            }
            if (e.getSource() == getBookButton()) {
                setVisible(false);
                bookFrame = new BookFrame(database);
            }
            if (e.getSource() == getVideoGameButton()) {
                setVisible(false);;
                videoGameFrame = new VideoGameFrame(database);
            }
        }
        if (s.equalsIgnoreCase("view")) {
            try {
                if (e.getSource() == getMovieButton()) {
                    if (database.countRows("movie") == 0) {
                        JOptionPane.showMessageDialog(null, "Sorry, no movie here, please add");
                    } else {
                        setVisible(false);
                        viewFrame = new ViewFrame(database, "movie");
                        viewFrame.createAndShowGUI(database, "movie", "view");
                    }
                }
                if (e.getSource() == getBookButton()) {
                    if (database.countRows("book") == 0) {
                        JOptionPane.showMessageDialog(null, "Sorry, no book here, please add");
                    } else {
                        setVisible(false);
                        viewFrame = new ViewFrame(database, "book");
                        viewFrame.createAndShowGUI(database, "book", "view");
                    }
                }
                if (e.getSource() == getVideoGameButton()) {
                    if (database.countRows("videogame") == 0) {
                        JOptionPane.showMessageDialog(null, "Sorry, no video game here, please add");
                    } else {
                        setVisible(false);
                        viewFrame = new ViewFrame(database, "videogame");
                        viewFrame.createAndShowGUI(database, "videogame", "view");
                    }
                }
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
        }
    }
}
