package com.gdbrannon.study.eventbus.calculator;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EBCalculatorView2 implements  ActionListener
{
    private JButton[] button_array = new JButton[4];
    private JButton[] numbered_array = new JButton[11];
    private String[] button_name = {"+", "-", "*", "/"};
    private String[] numbered_name = {"1", "2", "3", "4", "5", "6", "7", "8",
                                      "9", "0", "."};
    private JTextField input;
    private boolean new_amount = true, decimal = true;

    public EBCalculatorView2()
    {
        // Create and show the GUI.
        createAndShowGUI();
    }

    // Create the content pane which displays the buttons and widgets on-screen.
    private JPanel createContentPane()
    {
        JPanel totalGUI = new JPanel(new BorderLayout(10, 10));

        input = new JTextField("0.0", 8);
        input.setEditable(false);

        // Use GridLayout for equals positioning.
        JPanel action_buttons = new JPanel(new GridLayout(4,1));

        for(int i = 0; i < button_name.length; i++)
        {
            button_array[i] = new JButton(button_name[i]);
            action_buttons.add(button_array[i]);
        }

        // *** NEW ***
        // Added a panel for the numbered buttons on the page.

        JPanel number_buttons = new JPanel(new GridLayout(4,3));

        for(int i = 0; i < numbered_name.length; i++)
        {
            numbered_array[i] = new JButton(numbered_name[i]);
            numbered_array[i].addActionListener(this);
            numbered_array[i].setActionCommand(numbered_name[i]);
            number_buttons.add(numbered_array[i]);
        }

        totalGUI.add(input, BorderLayout.PAGE_START);
        totalGUI.add(number_buttons, BorderLayout.CENTER);
        totalGUI.add(action_buttons, BorderLayout.LINE_END);
        
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // As before, we create the frame and add the created content pane.
    private void createAndShowGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("[=] Calculator [=]");

        // Set the content pane.
        frame.setContentPane(createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // This is the new ActionPerformed that deals with adding the amount to
    // the calculator input correctly.
    public void actionPerformed(ActionEvent ae)
    {
        String temp = ae.getActionCommand();

        // If the calculator is ready for a new amount, clear the TextField
        // and toggle the flag.
        if(new_amount)
        {
            input.setText("");
            new_amount = false;
        }

        // If the button pressed is a decimal point, be sure that there is no
        // exisiting decimal point, then add it to the screen.
        if(temp.equals("."))
        {
            if(decimal)
            {
                decimal = false;
                input.setText(input.getText()+""+temp);
            }
        }
        // Else add the number to the screen.
        else
        {
            input.setText(input.getText()+""+temp);
        }
    }

    // Here we add the ActionListener passed by the Controller to each of the buttons
    public void buttonActionListeners(ActionListener al)
    {
        for(int i = 0; i < button_name.length; i++)
        {
            button_array[i].setActionCommand(button_name[i]);
            button_array[i].addActionListener(al);
        }
    }

    // Gets the text from the Text Box and converts it into a Double.
    public double getFieldText()
    {
        try{
            return Double.parseDouble(input.getText());
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("Error");
            return -1;
        }
    }

    // Sets the text displayed on the Text Box.
    public void setFieldText(String message)
    {
        input.setText(""+message);
        new_amount = true;
        decimal = true;
    }
}

// *************************************************************
// Name : CalculatorView_2.java
// Author : Stuart Davidson
// Date : 13/05/2007
// Description : Version 2, adds numbered buttons to the GUI.
// *************************************************************