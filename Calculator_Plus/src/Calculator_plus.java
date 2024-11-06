import java.awt.*;
import java.awt.event.*;

class Calculator_plus extends WindowAdapter implements ActionListener {
    Frame f;
    Label l1, historyLabel;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, custom;
    Button badd, bsub, bmult, bdiv, bmod, bcalc, bclr, bpts, bneg, bback,
            functionButtons1,functionButtons2,functionButtons3,functionButtons4,functionButtons5;
    double num1, num2, result;
    String operator;
    boolean isOperatorPressed;
    Panel sidePanel;
    Panel mainPanel;

    Calculator_plus() {
        f = new Frame("Calculator");

        // Main display
        l1 = new Label("0");
        l1.setBackground(Color.lightGray);
        l1.setFont(new Font("Arial", Font.PLAIN, 24));
        l1.setAlignment(Label.RIGHT);
        l1.setBounds(50, 50, 260, 60);

        // History section
        historyLabel = new Label("History: ");
        historyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        historyLabel.setBounds(50, 350, 260, 30);

        // Initialize buttons
        b1 = new Button("1");
        b2 = new Button("2");
        b3 = new Button("3");
        b4 = new Button("4");
        b5 = new Button("5");
        b6 = new Button("6");
        b7 = new Button("7");
        b8 = new Button("8");
        b9 = new Button("9");
        b0 = new Button("0");
        custom = new Button("FN");
        badd = new Button("+");
        bsub = new Button("-");
        bmult = new Button("*");
        bdiv = new Button("/");
        bmod = new Button("%");
        bcalc = new Button("=");
        bclr = new Button("CE");
        bpts = new Button(".");
        bneg = new Button("+/-");
        bback = new Button("Back");

        // Create main panel for calculator
        mainPanel = new Panel();
        mainPanel.setLayout(new GridLayout(5, 4, 5, 5)); // Grid for buttons with gaps

        // Add buttons to the main panel
        mainPanel.add(b7); mainPanel.add(b8); mainPanel.add(b9); mainPanel.add(custom);
        mainPanel.add(b4); mainPanel.add(b5); mainPanel.add(b6); mainPanel.add(bmult);
        mainPanel.add(b1); mainPanel.add(b2); mainPanel.add(b3); mainPanel.add(bsub);
        mainPanel.add(bpts);mainPanel.add(b0);  mainPanel.add(bcalc); mainPanel.add(badd);
        mainPanel.add(bclr); mainPanel.add(bneg);mainPanel.add(bback); mainPanel.add(bdiv);


        // Create side panel for function buttons
        createSidePanel();

        // Add action listeners
        addListeners();

        // Add components to frame
        f.add(l1);
        f.add(historyLabel);
        f.add(mainPanel);
        f.add(sidePanel);

        // Set bounds
        mainPanel.setBounds(50, 130, 260, 200);
        bclr.setBounds(50, 340, 65, 50);
        bneg.setBounds(120, 340, 65, 50);
        bback.setBounds(190, 340, 65, 50);
        custom.setBounds(5, 50, 65, 50);

        // Set frame size and layout
        f.setSize(450, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.addWindowListener(this);
    }

    private void createSidePanel() {
        sidePanel = new Panel();
        sidePanel.setLayout(new GridLayout(8, 1, 5, 5));
        sidePanel.setBounds(350, 50, 80, 350);
        sidePanel.setVisible(false); // hidden to start

        // Initialize function buttons
        functionButtons1 = new Button("Sin(X)"); // Customize as needed
        sidePanel.add(functionButtons1);

        functionButtons2 = new Button("Cos(X)"); // Customize as needed
        sidePanel.add(functionButtons2);

        functionButtons3 = new Button("X ^ Y "); // Customize as needed
        sidePanel.add(functionButtons3);

        functionButtons4 = new Button("√(X)"); // Customize as needed
        sidePanel.add(functionButtons4);

        functionButtons5 = new Button("%"); // Customize as needed
        sidePanel.add(functionButtons5);
    }



    private void addListeners() {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        custom.addActionListener(this);
        badd.addActionListener(this);
        bsub.addActionListener(this);
        bmult.addActionListener(this);
        bdiv.addActionListener(this);
        bmod.addActionListener(this);
        bcalc.addActionListener(this);
        bclr.addActionListener(this);
        bpts.addActionListener(this);
        bneg.addActionListener(this);
        bback.addActionListener(this);
        functionButtons1.addActionListener(this);
        functionButtons2.addActionListener(this);
        functionButtons3.addActionListener(this);
        functionButtons4.addActionListener(this);
        functionButtons5.addActionListener(this);
    }

    // Handle actions
    public void actionPerformed(ActionEvent e) {
        String buttonLabel = e.getActionCommand();

        // Input handling
        if ("0123456789.".contains(buttonLabel)) {
            if (buttonLabel.equals(".") && l1.getText().contains(".")) {
                return; // Avoid duplicate decimals
            }
            // If the display shows "0", replace it
            if (l1.getText().equals("0")) {
                l1.setText(buttonLabel);
            } else {
                l1.setText(l1.getText() + buttonLabel);
            }
            isOperatorPressed = false; // Reset the operator flag
        }
        // Operator handling
        else if ("+-*/%".contains(buttonLabel)) {
            if (!isOperatorPressed) {
                num1 = Double.parseDouble(l1.getText());
                operator = buttonLabel;
                l1.setText("");
                isOperatorPressed = true;
            }
        }
        // Equals to calculate
        else if (buttonLabel.equals("=")) {
            num2 = Double.parseDouble(l1.getText());
            result = performOperation(num1, num2, operator);
            l1.setText(String.valueOf(result)); // Display only the result
            historyLabel.setText("History: " + num1 + " " + operator + " " + num2 + " = " + result);
        }
        // Clear button
        else if (buttonLabel.equals("CE")) {
            l1.setText("0");
            historyLabel.setText("History: ");
            isOperatorPressed = false;
        }
        // Back button
        else if (buttonLabel.equals("Back")) {
            String text = l1.getText();
            if (text.length() > 1) {
                l1.setText(text.substring(0, text.length() - 1));
            } else {
                l1.setText("0");
            }
        }
        // Toggle +/- button
        else if (buttonLabel.equals("+/-")) {
            double currentValue = Double.parseDouble(l1.getText());
            currentValue *= -1;
            l1.setText(String.valueOf(currentValue));
        }
        // Button to show/hide function buttons
        else if (buttonLabel.equals("FN")) {
            sidePanel.setVisible(!sidePanel.isVisible());
        }
        // Sin(X) button
        else if (buttonLabel.equals("Sin(X)")) {
            double value = Math.toRadians(Double.parseDouble(l1.getText())); // Convert to radians
            result = Math.sin(value);
            l1.setText(String.valueOf(result));
        }
        // Cos(X) button
        else if (buttonLabel.equals("Cos(X)")) {
            double value = Math.toRadians(Double.parseDouble(l1.getText())); // Convert to radians
            result = Math.cos(value);
            l1.setText(String.valueOf(result));
        }
        // X ^ Y button
        else if (buttonLabel.equals("X ^ Y")) {
            num1 = Double.parseDouble(l1.getText());
            operator = "^";
            l1.setText(""); // Prepare for second number
        }
        // √(X) button
        else if (buttonLabel.equals("√(X)")) {
            double value = Double.parseDouble(l1.getText());
            result = Math.sqrt(value);
            l1.setText(String.valueOf(result));
        }
    }

    private double performOperation(double n1, double n2, String op) {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/": return n2 != 0 ? n1 / n2 : Double.NaN;
            case "%": return n1 % n2;
            case "^": return Math.pow(n1, n2);
            default: return 0;
        }
    }


    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    public static void main(String args[]) {
        new Calculator_plus();
    }
}
