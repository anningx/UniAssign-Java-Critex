
package critex;

import javax.swing.JOptionPane;

/**
 * Performs various validations to input data
 * @author Anning Xiang
 */
public class Validate 
{
    
    /**
     * creates the Validate class
     */
    public void Validate()
    {
        
    }
    
    /**
     * check input is not empty
     * @param name
     * @param input
     * @return boolean
     */
    public boolean checkString(String name, String input)
    {
        boolean b = true;
        if ("".equals(input.trim()))
        {
            JOptionPane.showMessageDialog( null, "Sorry, the " + name + " should not be empty");
            b = false;
        }
        return b;
    }
    
    /**
     * transforms an string input into an integer
     * @param input
     * @return int
     */
    public int getInt(String input)
    {
        return Integer.parseInt(input);
    }
    
    /**
     * check input is an integer
     * @param name
     * @param input
     * @return boolean
     */
    public boolean checkInt(String name, String input)
    {
        boolean b = true;
        try
        {
            int i = Integer.parseInt(input);
             }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog( null, "Sorry, the " + name + " should be numbers");
                    b = false;
                }
        return b;
    }
    
    /**
     * check integer is within certain ranges
     * @param name
     * @param input
     * @param minimum
     * @param maximum
     * @return boolean
     */
    public boolean checkIntWithinRanges(String name, String input, int minimum, int maximum)
    {
        boolean b = true;
        try
        {
            int i = Integer.parseInt(input);
            if (i > maximum || i < minimum)
            {
                JOptionPane.showMessageDialog( null, "Sorry, the " + name + " should not be within " + minimum + " and " + maximum );
                b = false;
            }
        }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog( null, "Sorry, the " + name + " should be numbers");
                    b = false;
                }
        return b;
    }
    
    /**
     * check input is a floating point number
     * @param name
     * @param input
     * @return boolean
     */
    public boolean checkFloat(String name, String input)
    {
        boolean b = true;
        try
        {
            float i = Float.parseFloat(input);
             }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog( null, "Sorry, the " + name + " should be numbers");
                    b = false;
                }
        return b;
    }
    
    /**
     * transform a string input into a floating point number
     * @param input
     * @return
     */
    public float getFloat(String input)
    {
        return Float.parseFloat(input);
    }
}
