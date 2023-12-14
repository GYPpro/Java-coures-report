package sis10;

import java.util.ArrayList;

public abstract class myLinearEntire {
    protected int dim;
    protected ArrayList<myLinearEntire> coordinate;

    public myLinearEntire() {
        this.dim = 0;
        this.coordinate = new ArrayList<myLinearEntire>();
    }

    public myLinearEntire(int dim, ArrayList<myLinearEntire> coordinate) {
        this.dim = dim;
        this.coordinate = new ArrayList<myLinearEntire>(coordinate);
    }

    public myLinearEntire(myLinearEntire other) {
        this.dim = other.dim;
        this.coordinate = new ArrayList<myLinearEntire>(other.coordinate);
    }

    public myLinearEntire(int _dim)
    {
        this.dim = _dim;
        this.coordinate = new ArrayList<myLinearEntire>();
    }

    public int getDim() {
        return this.dim;
    }

    public ArrayList<myLinearEntire> getCoordinate() {
        return this.coordinate;
    }

    abstract public myLinearEntire add(myLinearEntire other)  throws Exception;

    abstract public myLinearEntire multiply(myLinearEntire other) throws Exception;

    abstract public <N> myLinearEntire multiply(N other) throws Exception;

    abstract public myLinearEntire negative() throws Exception;

    abstract public void print() throws Exception;
    
    @Override
    abstract public boolean equals(Object other);

    @Override
    abstract public int hashCode();

    @Override
    abstract public String toString();                    

    abstract public double getValue() throws Exception;  
}
