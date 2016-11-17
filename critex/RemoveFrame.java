
package critex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays the remove frame to remove something in the database
 * @author Anning Xiang
 */
public class RemoveFrame extends JFrame implements ActionListener {

    private JLabel description;
    private JButton saveButton;
    private JButton quitButton;
    private Database database;
    private String s;
    private JList<String> list;

    /**
     * creates the remove frame
     * @param newDatabase
     * @param s
     */
    public RemoveFrame(Database newDatabase, String s) {
        database = newDatabase;
        this.s = s;
        setTitle("Remove" + s);
        list = new JList<String>(database.getList(s));
        JScrollPane scroller = new JScrollPane(list);
        scroller.setViewportView(list);
        description = new JLabel("Please choose the " + s + " you want to remove");
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");

        saveButton.addActionListener(this);
        quitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        setLayout(new GridLayout(3, 1));

        add(description);
        add(scroller);
        add(buttonPanel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-150), (dim.height/2-100));
        setSize(300, 200);
        setVisible(true);
    }

    private JButton getQuitButton() {
        return quitButton;
    }

    private JButton getSaveButton() {
        return saveButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getQuitButton()) {
            this.setVisible(false);
        }
        if (e.getSource() == getSaveButton()) {
            if (list.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "Sorry, you need to choose first");
            } else {
                JOptionPane.showMessageDialog(null, list.getSelectedValue() + "has been removed successfully");
                setVisible(false);
                database.removeTitle(s, list.getSelectedValue());
            }
        }
    }
}