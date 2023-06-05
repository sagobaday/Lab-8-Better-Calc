import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on
 * screen.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class UserInterface
        implements ActionListener
{
    //    private final CalcEngine calc;
    private boolean showingAuthor;
    private boolean hexMode;
    private boolean operatorMode;
    private boolean resultExists;
    private int parenthesisCount;
    private double result;
    private String inputStr;

    private JFrame frame;
    private JTextField display;
    private JLabel status;

    /**
     * Create a user interface.
     */
    public UserInterface()
    {
//        calc = engine;
        inputStr = "";
        showingAuthor = true;
        hexMode = false;
        operatorMode = false;
        resultExists = false;
        parenthesisCount = 0;
        result = 0;

        makeFrame();
        frame.setVisible(true);
        toggleButtons();
    }

    private void makeFrame()
    {
        frame = new JFrame("Java Calculator");

        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setLayout(new BorderLayout(8, 8));
        contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

        display = new JTextField();
        contentPane.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(7, 4));
        String[] buttonLabels = {
                "7", "8", "9", "Clear",
                "4", "5", "6", "?",
                "1", "2", "3", "",
                "%", "^", "(", ")",
                "+", "-", "/", "*",
                "A", "B", "C", "=",
                "D", "E", "F", "HEX"
        };

        for (String label : buttonLabels) {
            addButton(buttonPanel, label);
        }

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel("David J. Barnes and Michael Kolling");
        contentPane.add(status, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * Add a button to the button panel.
     * @param panel The panel to receive the button.
     * @param buttonText The text for the button.
     */
    private void addButton(Container panel, String buttonText)
    {
        JButton button = new JButton(buttonText);
        button.addActionListener(this);
        panel.add(button);
    }

    /**
     * An interface action has been performed.
     * Find out what it was and handle it.
     * @param event The event that has occurred.
     */
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();

        switch(command) {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" -> {
                inputStr += command;
                operatorMode = true;
                toggleButtons();
            }
            case ")" -> {
                if (parenthesisCount > 0) {
                    parenthesisCount--;
                    inputStr += command;
                    operatorMode = true;
                    toggleButtons();
                }
            }
            case "HEX" -> {
                hexMode = !hexMode;
                toggleButtons();
            }
            case "+", "-", "/", "*", "%", "^" -> {
                inputStr += command;
                operatorMode = false;
                toggleButtons();
            }
            case "(" -> {
                if (operatorMode)
                    inputStr += "*";

                parenthesisCount++;
                inputStr += command;
                operatorMode = false;
                toggleButtons();
            }
            case "=" -> calculate();
            case "Clear" -> {
                inputStr = "";
                operatorMode = false;
                toggleButtons();

                resultExists = false;
            }
            case "?" -> showInfo();
        }

        // else unknown command.
        redisplay();
    }

    private void toggleButtons() {
        JPanel buttonPanel = (JPanel) frame.getContentPane().getComponent(1);

        for(int i = 0; i <= buttonPanel.getComponents().length - 1; i++) {
            JButton button = (JButton) buttonPanel.getComponent(i);

            switch(i) {
                case 0, 1, 2, 4, 5, 6, 8, 9, 10, 11 -> button.setEnabled(!operatorMode);
                case 12, 13, 15, 16, 17, 18, 19, 23 -> button.setEnabled(operatorMode);
                case 20, 21, 22, 24, 25, 26 -> button.setEnabled(!operatorMode && hexMode);
            }
        }
    }

    private void calculate() {
        //add missing closing parenthesis
        while (parenthesisCount > 0) {
            inputStr += ")";
            parenthesisCount--;
        }

        // Create an instance of the Postfix class
        Postfix postfix = new Postfix();

        try {
            // Convert infix expression to postfix
            String postfixExpression = postfix.infixToPostfix(inputStr);

            // Evaluate the postfix expression
            if (resultExists)
                result = postfix.evaluate(postfixExpression, result);
            else
                result = postfix.evaluate(postfixExpression);

        } catch (IllegalArgumentException | StackUnderflowException e) {
            System.out.println("Error: " + e.getMessage());
        }

        inputStr = "";
        resultExists = true;
        operatorMode = true;
        toggleButtons();
    }

    public double testInput(String input) {
        inputStr = input;
        calculate();
        return result;
    }

    /**
     * Update the interface display to show the current value of the
     * calculator.
     */
    private void redisplay()
    {
        if (resultExists) {
            if (hexMode)
                display.setText(Long.toHexString(Math.round(result)) + inputStr);
            else
                display.setText(result + inputStr);

        } else
            display.setText(inputStr);
    }

    /**
     * Toggle the info display in the calculator's status area between the
     * author and version information.
     */
    private void showInfo()
    {
        if(showingAuthor)
            status.setText("Version 2.0");
        else
            status.setText("Tizian & Santiago");

        showingAuthor = !showingAuthor;
    }
}