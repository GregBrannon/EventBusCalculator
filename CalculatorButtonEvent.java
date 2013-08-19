package com.gdbrannon.study.eventbus.calculator;

public abstract class CalculatorButtonEvent
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
    public AdditionButtonEvent( double value )
    {
        super( value );
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

