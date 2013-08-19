package com.gdbrannon.study.eventbus.calculator;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

//*************************************************************
// Name : EBCalculatorModel.java
// Author: GregBrannon, August 2013
// Based on a tutorial written by : Stuart Davidson
// Original Date : 12/05/2007
// Description : A demonstration of how to create a simple MVC
//         calculator using the EventBus programming structure
//         or design pattern. This is the Model portion of the
//         architecture. Note that the model is only aware of
//         the EventBus, subscribing to those events to which
//         it must respond to.
//
// Original tutorial from the Guidebook MVC Tutorial at:
//http://www.macs.hw.ac.uk/guidebook/?name=Using%20The%20GUI&page=1
//
// EventBus information gleaned from the Google Guava EventBus
// API and the following links:
//http://www.javacodegeeks.com/2013/06/guavas-eventbus-simple-publishersubscriber.html
//http://www.javacodegeeks.com/2012/11/google-guava-eventbus-for-event-programming.html
//*************************************************************

public class EBCalculatorModel
{
    private double answer;

    private EventBus calculatorEB;

    // Constructor sets both numbers.
    public EBCalculatorModel( EventBus calculatorEB )
    {
        this.calculatorEB = calculatorEB;
        this.calculatorEB.register( this );

    } // end constructor

    @Subscribe
    public void handlAddEvent( AddEvent ae )
    {
        answer += ae.value;
        reportResult( answer );
    }

    @Subscribe
    public void handlSubtractEvent( SubtractEvent se )
    {
        answer -= se.value;
        reportResult( answer );
    }

    @Subscribe
    public void handleMultiplyEvent( MultiplyEvent me )
    {
        answer *= me.value;
        reportResult( answer );
    }

    @Subscribe
    public void handleDivideEvent( DivideEvent de )
    {
        answer /= de.value;
        reportResult( answer );
    }

    private void reportResult( double value )
    {
        calculatorEB.post( new ModelResultEvent( value ) );
    }

} // end class CalculatorModel