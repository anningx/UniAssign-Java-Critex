
package critex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Displays the pay author frame for paying an author
 * @author Anning Xiang
 */
public class PayAuthorFrame extends JFrame implements ActionListener
{
    private JLabel reviewLabel;
    private JList<String> list;
    private JButton payButton;
    private JButton notPayButton;
    private Database database;
    
    /**
     *
     * @param newDatabase
     */
    public PayAuthorFrame(Database newDatabase)
    {
        setTitle("Pay an author");
        database = newDatabase;
        reviewLabel = new JLabel("Choose one review to pay");
        list = new JList<String>(database.getNotPaidReview());
        JScrollPane scroller = new JScrollPane(list);
        scroller.setViewportView(list);
        payButton = new JButton("Pay");
        notPayButton = new JButton("Not Pay");
        
        payButton.addActionListener(this);
        notPayButton.addActionListener(this);
        
        JPanel payPanel = new JPanel();
        payPanel.setLayout(new FlowLayout());
        payPanel.add(payButton);
        payPanel.add(notPayButton);
        
        setLayout(new GridLayout(3,1));
        
        add(reviewLabel);
        add(scroller);
        add(payPanel);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width/2-175), (dim.height/2-100));
        setSize(350, 200);
        setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == payButton)
        {
            if(list.getSelectedValue() != null)
            {
                boolean p = true;
                JOptionPane.showMessageDialog(null, "The author is paid successfully");
                setVisible(false);
                database.payAuthor(p, list.getSelectedValue());
            }
            else
                JOptionPane.showMessageDialog(null, "Sorry, you need select first"); 
        }
        if(e.getSource() == notPayButton)
        {
            setVisible(false);
        }
    }
}