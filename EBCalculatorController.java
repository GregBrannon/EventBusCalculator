package com.gdbrannon.study.eventbus.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

//*************************************************************
// Name : EBCalculatorController.java
// Author: GregBrannon, August 2013
// Based on a tutorial written by : Stuart Davidson
// Original Date : 12/05/2007
// Description : A demonstration of how to create a simple MVC
//            calculator using the EventBus programming structure
//            or design pattern. Rather than accepting instances 
//            of the model and view from the main() method as in
//            traditional MVC, the controller subscribes to events
//            and is notified of those events by the EventBus.
//
//            As in traditional MVC, the controller then uses those
//            event notifications to provide the necessary responses
//            to the view in order to respond
//            appropriately to user input, and coordinates the
//            application's response to user input using the
//            model, displaying the results in the view.
// Original tutorial from the Guidebook MVC Tutorial at:
//http://www.macs.hw.ac.uk/guidebook/?name=Using%20The%20GUI&page=1
//
// EventBus information gleaned from the Google Guava EventBus
// API and the following links:
// http://www.javacodegeeks.com/2013/06/guavas-eventbus-simple-publishersubscriber.html
// http://www.javacodegeeks.com/2012/11/google-guava-eventbus-for-event-programming.html
//*************************************************************

public class EBCalculatorController implements Runnable
{
    EBCalculatorModel model;
    EBCalculatorView view;
    private EventBus calculatorEB;

    // constructor CalculatorController()
    public EBCalculatorController( EventBus calculatorEB )
    {
        this.calculatorEB = calculatorEB;

        /*  The old way - being replaced by Event Subscriptions
  // method actionPerformed() matches actions from the view to the
  // appropriate responses by first determining the action's source
  // and then using a string of if/else statements. the purpose of
  // this study is to explore whether there are more economical and
  // efficient options to this particular feature of the traditional
  // MVC pattern
  public void actionPerformed( ActionEvent ae )
  {
      String action_com = ae.getActionCommand();

      if(action_com.equals( "+" ))
      {
          model.doAddition( view.getFieldText() );
      }
      else if ( action_com.equals( "-" ) )
      {
          model.doSubtraction( view.getFieldText() );
      }
      else if ( action_com.equals( "*" ) )
      {
          model.doMultiply( view.getFieldText() );
      }
      else if ( action_com.equals( "/" ) )
      {
          model.doDivision( view.getFieldText() );
      }

      view.setFieldText( "" + model.getAnswer() );

  } // end method actionPerformed()
         */ // End of the old way

    } // end constructor CalculatorController()

    // method run() is used to complete any initialization needed
    @Override
    public void run()
    {
        calculatorEB.register( this );
        
    } // end method run()

    @Subscribe
    public void handleAdditionButtonEvent( AdditionButtonEvent abe )
    {
        calculatorEB.post( new AddEvent( abe.value ) );
    }

    @Subscribe
    public void handleSubtractionButtonEvent( SubtractionButtonEvent sbe )
    {
        calculatorEB.post( new SubtractEvent( sbe.value ) );
    }

    @Subscribe
    public void handleMultiplicationButtonEvent( MultiplicationButtonEvent mbe )
    {
        calculatorEB.post( new MultiplyEvent( mbe.value ) );
    }

    @Subscribe
    public void handleDivisionButtonEvent( DivisionButtonEvent dbe )
    {
        calculatorEB.post( new DivideEvent( dbe.value ) );
    }
    
    @Subscribe
    public void handleModelResultEvent( ModelResultEvent mre )
    {
        calculatorEB.post( new UpdateDisplayEvent( mre.value ) );
    }

} // end class CalculatorController
