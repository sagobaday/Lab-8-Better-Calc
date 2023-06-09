/**
 * The main class of a simple calculator. Create one of these, and you'll
 * get the calculator on screen.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class Calculator
{
    public static void main(String[] args) {
        new Calculator();
    }

    /**
     * Create a new calculator and show it.
     */
    private Calculator()
    {
//        CalcEngine engine = new CalcEngine();
        new UserInterface();
    }
}