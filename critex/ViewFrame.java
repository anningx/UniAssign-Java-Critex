
package critex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays a frame that lists contents of a table
 * @author Anning Xiang
 */
public class ViewFrame extends JPanel implements ActionListener
{
    private Database database;
    private JTable table;
    private JButton remove;
    private JButton pay;
    private JButton quit;
    private String s;
    private RemoveFrame removeFrame;
    private PayAuthorFrame payAuthorFrame;
    private Object[][] data;
    private JFrame frame;
    
    /**
     * creates the view frame
     * @param newDatabase
     * @param s
     */
    public ViewFrame(Database newDatabase, String s) 
    {
        super(new GridLayout(4,1));
        this.s = s;
        database = newDatabase;
        remove = new JButton("Remove");
        pay = new JButton("Pay");
        quit = new JButton("Exit");
        String[] columnNames = null;
        data = null;
        if(s.equalsIgnoreCase("author"))
        {
            columnNames = database.getAuthorColumnName();
            data = database.getAuthorArray();
        }
        if(s.equalsIgnoreCase("movie"))
        {
            columnNames = database.getMovieColunmName();
            data = database.getMovieArray();
        }
        if(s.equalsIgnoreCase("Book"))
        {
            columnNames = database.getBookColunmName();
            data = database.getBookArray();
        }
        if(s.equalsIgnoreCase("VideoGame"))
        {
            columnNames = database.getVideoGameColunmName();
            data = database.getVideoGameArray();
        }
        if(s.equalsIgnoreCase("Review"))
        {
            columnNames = database.getReviewColumnName();
            data = database.getReviewArray();
        }
        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * @param newDatabase
     * @param s 
     * @param buttonName  
     */
    public void createAndShowGUI(Database newDatabase, String s, String buttonName) 
    {
        this.s = s;
        frame = new JFrame("View" + s);
        ViewFrame view = new ViewFrame(newDatabase,s);
        remove.addActionListener(this);
        pay.addActionListener(this);
        quit.addActionListener(this);
        view.setOpaque(true); 
        frame.setContentPane(view);
        if(buttonName.equalsIgnoreCase("view"))
            frame.add(remove);
        if(buttonName.equalsIgnoreCase("view") && s.equalsIgnoreCase("review"))
        {
            frame.add(remove);
            frame.add(pay);
        }
        frame.add(quit);
        frame.pack();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-150), (dim.height/2-100));
        setSize(300,200);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == remove)
        {
            frame.setVisible(false);
            removeFrame = new RemoveFrame(database, s);
        }
        if(e.getSource() == pay)
        {
            frame.setVisible(false);
            payAuthorFrame = new PayAuthorFrame(database);
        }
        if(e.getSource() == quit)
            frame.setVisible(false);
    }
}