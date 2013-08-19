package com.gdbrannon.study.eventbus.calculator;

//*************************************************************
//Name : EBCalculatorView.java
//Author: (c) GregBrannon, August 2013
//Based on a tutorial written by : Stuart Davidson
//Original Date : 12/05/2007
//Description : A demonstration of how to create a simple MVC
//      calculator using the EventBus programming structure
//      or design pattern. This is the View portion of the
//      architecture. Note that the view is only aware of
//      the EventBus, subscribing to those events to which
//      it must respond.
//
//Original tutorial from the Guidebook MVC Tutorial at:
//http://www.macs.hw.ac.uk/guidebook/?name=Using%20The%20GUI&page=1
//
//EventBus information gleaned from the Google Guava EventBus
//API and the following links:
//http://www.javacodegeeks.com/2013/06/guavas-eventbus-simple-publishersubscriber.html
//http://www.javacodegeeks.com/2012/11/google-guava-eventbus-for-event-programming.html
//*************************************************************

import javax.swing.*;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EBCalculatorView
{
    private JButton[] buttonArray;
    private String[] buttonNames = { "+", "-", "*", "/" };
    private JTextField input;
    private JFrame frame;
    
    private EventBus calculatorEB;

    // constructor CalculatorView()
    public EBCalculatorView( EventBus calculatorEB )
    {
        this.calculatorEB = calculatorEB;
        this.calculatorEB.register( this );
        
        // create and show the GUI.
        createGUI();
        
    } // constructor CalculatorView()

    // create the JPanel that will be added to the JFrame's content
    // pane to display the user interface
    private JPanel createGUIContent()
    {
        JPanel totalGUI = new JPanel();
        buttonArray = new JButton[4];

        input = new JTextField( 8 );
        input.setText( "0.0" );

        // GridLayout is used to simplify button positioning
        JPanel actionButtons = new JPanel( new GridLayout( 2, 2 ) );

        for ( int i = 0 ; i < buttonNames.length ; i++ )
        {
            buttonArray[i] = new JButton( buttonNames[i] );
            actionButtons.add( buttonArray[i] );
        }

        // add a listener to each button to post the appropriate event
        // to the EventBus
        buttonArray[0].addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent ae )
            {
                calculatorEB.post( new AdditionButtonEvent( getFieldText() ) );
            }
            
        } );
        buttonArray[1].addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent ae )
            {
                calculatorEB.post( new SubtractionButtonEvent( getFieldText() ) );
            }
            
        } );
        buttonArray[2].addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent ae )
            {
                calculatorEB.post( new MultiplicationButtonEvent( getFieldText() ) );
            }
            
        } );
        buttonArray[3].addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent ae )
            {
                calculatorEB.post( new DivisionButtonEvent( getFieldText() ) );
            }
            
        } );
        
        // construct and return the GUI content
        totalGUI.add( input );
        totalGUI.add(actionButtons);
        totalGUI.setOpaque(true);
        return totalGUI;
        
    } // end method createGUIContent()

    // create the frame and add the GUI content
    private void createGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated( true );
        frame = new JFrame( "[=] Calculator [=]" );

        // Set the content pane.
        frame.setContentPane( createGUIContent() );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setLocationRelativeTo( null );
        frame.pack();
        
    } // end method createAndShowGUI()
    
    public void show()
    {
        frame.setVisible( true );
        
    } // end method show()

/* The old way
    // add the ActionListener passed by the Controller to each of the buttons
    public void buttonActionListeners( ActionListener al )
    {
        for ( int i = 0 ; i < button_name.length ; i++ )
        {
            button_array[i].setActionCommand( button_name[i] );
            button_array[i].addActionListener( al );
        }
        
    } // end method buttonActionListeners()
*/ // End of the old way
    
    // returns the text from the text field converted to a double value
    public double getFieldText()
    {
        try
        {
            return Double.parseDouble( input.getText() );
        }
        catch( NumberFormatException nfe )
        {
            System.out.println( "Error converting text field to double." );
            return -1;
        }
        
    } // end method getFieldText()

    // sets the text displayed in the text field to the parameter message
    public void setFieldText( String message )
    {
        input.setText( "" + message );
    }
    
    @Subscribe
    public void handleUpdateDisplayEvents( UpdateDisplayEvent ude )
    {
        setFieldText( "" + ( ude.value ) );
    }
    
} // end class CalculatorView