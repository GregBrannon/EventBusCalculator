package com.gdbrannon.study.eventbus.calculator;

import javax.swing.SwingUtilities;

import com.google.common.eventbus.EventBus;

//*************************************************************
//Name : EventBusCalculator.java
//Author: GregBrannon, August 2013
//Based on a tutorial written by : Stuart Davidson
//Original Date : 12/05/2007
//Description : A demonstration of how to create a simple MVC
//         calculator using the EventBus programming structure
//         or design pattern. Rather than creating instances 
//         of the model and view and passing them to the controller
//         as in traditional MVC, the main() method creates the
//         EventBus and each element of the MVC architecture, passing
//         each an instance of the EventBus.  The main() method
//         then passes control of the application to the controller.
//
//Original tutorial from the Guidebook MVC Tutorial at:
//http://www.macs.hw.ac.uk/guidebook/?name=Using%20The%20GUI&page=1
//
//EventBus information gleaned from the Google Guava EventBus
//API and the following links:
//http://www.javacodegeeks.com/2013/06/guavas-eventbus-simple-publishersubscriber.html
//http://www.javacodegeeks.com/2012/11/google-guava-eventbus-for-event-programming.html
//*************************************************************

public class EventBusCalculator
{
  public static void main( String[] args )
  {
/*      This would be the traditional MVC construction approach. Preserve
 *      it to compare to the resulting EventBus construction.
 *      
      // Creates a model that contains the application's business logic.
      EBCalculatorModel model = new EBCalculatorModel();

      // Creates a view to accept user input to the application's logic
      // and presents the results to the user.
      EBCalculatorView view = new EBCalculatorView();

      // Creates a controller that links the two.
      new EBCalculatorController(model, view);
*/ // End of old-style construction approach
      
      // begin EventBus construction here
      final EventBus calculatorEB = new EventBus();
      
      // create an instance of the model, view, and controller,
      // Note: references to model, view, and controller instances are only
      // created if needed
      new EBCalculatorModel( calculatorEB );
      
      
      // Note: the view has been modified so that it does not make itself
      // visible. this allows the main() method to start the view on the EDT.
      Thread controller = new Thread( new EBCalculatorController( calculatorEB ) );
      
      // Note: the view is started on the EDT
      SwingUtilities.invokeLater( new Runnable()
      {
          public void run()
          {
              EBCalculatorView view = new EBCalculatorView( calculatorEB );
              view.show();
          }
      });
      
      controller.start();
      
  } // end method main()
  
} // end class Calculator
