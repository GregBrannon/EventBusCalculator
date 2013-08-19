package com.gdbrannon.study.eventbus.calculator;

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

class UpdateDisplayEvent
{
    double value;
    
    public UpdateDisplayEvent( double value )
    {
        this.value = value;
        
    } // end default constructor
}

class ModelResultEvent
{
    double value;
    
    public ModelResultEvent( double value )
    {
        this.value = value;
        
    } // end default constructor
}


