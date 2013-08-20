package com.gdbrannon.study.eventbus.calculator;

/**
 * This collection of events makes up the EBCalculator's EventBus. there could
 * be events in this collection that are not used, or not used <B>yet</B>, and
 * the EventBus object doesn't have knowledge of these events until a class
 * post()s to the EventBus object using one of these events as an argument.
 *  
 * @author (c) Greg Brannon Aug 20, 2013
 */

/**
 * the abstract class CalculatorButtonEvent is created to provide a basic event
 * button that can be used to represent all button events that extend it.
 * because the eventbus methods are limited to a single value, the subclasses
 * of a class like this one can be used to add additional variables as
 * demonstrated by class AdditionButtonEvent. other expansions are left to the
 * reader to discover  
 */
abstract class CalculatorButtonEvent
{
    double value;

    public CalculatorButtonEvent( double value )
    {
        this.value = value;
    }

} // end class CalculatorButtonEvent

// an object to handle the addition button
class AdditionButtonEvent extends CalculatorButtonEvent
{
    // this is a contrived value to demonstrate how the subclassed events can
    // be used to add variables beyond the single-variable limitation
    double currentValue;
    
    public AdditionButtonEvent( double value, double currentValue )
    {
        super( value );
        this.currentValue = currentValue;
    }
}

//an object to handle the subtraction button
class SubtractionButtonEvent extends CalculatorButtonEvent
{
    public SubtractionButtonEvent( double value )
    {
        super( value );
    }
}

//an object to handle the multiplication button
class MultiplicationButtonEvent extends CalculatorButtonEvent
{
    public MultiplicationButtonEvent( double value )
    {
        super( value );
    }
}

//an object to handle the division button
class DivisionButtonEvent extends CalculatorButtonEvent
{
    public DivisionButtonEvent( double value )
    {
        super( value );
    }
}

/*
 * the following events are used by the controller to interpret the button
 * events to actions upon the model
 */
class AddEvent
{
    double value;
    
    public AddEvent( double value )
    {
        this.value = value;
    }
}

class SubtractEvent
{
    double value;
    
    public SubtractEvent( double value )
    {
        this.value = value;
        
    } // end default constructor
    
}

class MultiplyEvent
{
    double value;
    
    public MultiplyEvent( double value )
    {
        this.value = value;
        
    } // end default constructor
    
}

class DivideEvent
{
    double value;
    
    public DivideEvent( double value )
    {
        this.value = value;
        
    } // end default constructor
    
}

/*
 * the following event is used by the controller to relay changes to
 * the view caused by state/value changes in the model
 */
class UpdateDisplayEvent
{
    double value;
    
    public UpdateDisplayEvent( double value )
    {
        this.value = value;
        
    } // end default constructor
}

/*
 * the following event is used by the model to broadcast state/value changes
 * to all subscribed MVC elements. in this simple example, only the controller
 * is subscribed to events from the the model
 */
class ModelResultEvent
{
    double value;
    
    public ModelResultEvent( double value )
    {
        this.value = value;
        
    } // end default constructor
}


