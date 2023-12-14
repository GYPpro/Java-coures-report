package sis10;

import java.util.ArrayList;  

public class myPolynomial extends myLinearEntire{

    int xValue = 1;

    //↓覆写超类方法
    @Override
    public boolean equals(Object other){
        if(other instanceof myPolynomial){
            myPolynomial mOther = (myPolynomial)other;
            if(this.dim != mOther.dim) return false;
            for(int i = 0; i < this.dim; i++){
                if(!this.coordinate.get(i).equals(mOther.coordinate.get(i))) return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        int hash = 0;
        for(int i = 0; i < this.dim; i++){
            hash += this.coordinate.get(i).hashCode();
        }
        return hash;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.dim; i++){
            str += this.coordinate.get(i).toString() + "·x^ " + i;
            if(i != this.dim -1) str += "+ ";
        }
        return str;
    }

    //↓构造函数
    public myPolynomial() {
        super();
    }
    
    public myPolynomial(int _dim, ArrayList<myLinearEntire> _coordinate) {
        super(_dim, _coordinate);
    }
    
    public myPolynomial(myPolynomial other) {
        super(other);
    }

    public myPolynomial(int _dim)
    {
        super(_dim);
        for(int i = 0;i < _dim;i ++)
            coordinate.add(new myRealNum(0));
    }

    //↓覆写父类方法
    public myPolynomial add(myLinearEntire other) throws Exception {
        if (other instanceof myPolynomial) {
            myPolynomial mOther = (myPolynomial) other;
            ArrayList<myLinearEntire> newCoordinate = new ArrayList<myLinearEntire>();
            for (int i = 0; i < Math.min(this.getDim(),other.getDim()); i++) {
                newCoordinate.add(this.getCoordinate().get(i).add(mOther.getCoordinate().get(i)));
            }
            for (int i = Math.min(this.getDim(),other.getDim()); i < Math.max(this.getDim(),other.getDim()); i++) {
                if (this.getDim() > other.getDim()) {
                    newCoordinate.add(this.getCoordinate().get(i));
                } else {
                    newCoordinate.add(mOther.getCoordinate().get(i));
                }
            }
            return new myPolynomial(this.getDim(), newCoordinate);
        } else {
            throw new Exception("非法的多项式相加。");
        }
    }

    public myPolynomial multiply(myLinearEntire other) throws Exception {
        if (other instanceof myPolynomial) {
            myPolynomial mOther = (myPolynomial) other;
            ArrayList<myLinearEntire> newCoordinate = new ArrayList<myLinearEntire>();
            int aimDim = this.getDim() + mOther.getDim() - 1;
            for (int i = 0; i < aimDim; i++) {
                newCoordinate.add(new myRealNum(0));
            }
            for (int i = 0; i < this.getDim(); i++) {
                for (int j = 0; j < mOther.getDim(); j++) {
                    newCoordinate.set(i + j, newCoordinate.get(i + j).add(this.getCoordinate().get(i).multiply(mOther.getCoordinate().get(j))));
                }
            }
            return new myPolynomial(aimDim, newCoordinate);
        } else {
            throw new Exception("非法的多项式相乘。");
        }
    }

    public <N> myPolynomial multiply(N other) throws Exception {
        ArrayList<myLinearEntire> newCoordinate = new ArrayList<myLinearEntire>();
        for (int i = 0; i < this.getDim(); i++) {
            newCoordinate.add(this.getCoordinate().get(i).multiply(other));
        }
        return new myPolynomial(this.getDim(), newCoordinate);
    }

    public void print() throws Exception {
        for (int i = 0; i < this.getDim(); i++) {
            this.getCoordinate().get(i).print();
        }
    }

    public double getValue()
    {
        try {
            return this.cacu(1);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public myLinearEntire negative() throws Exception {
        ArrayList<myLinearEntire> newCoordinate = new ArrayList<myLinearEntire>();
        for (int i = 0; i < this.getDim(); i++) {
            newCoordinate.add(this.getCoordinate().get(i).negative());
        }
        return new myPolynomial(this.getDim(), newCoordinate);
    }

    //↓多项式特有方法
    public double cacu(double x) throws Exception { //给定x的值计算多项式的值
        double result = 0;
        for (int i = 0; i < this.getDim(); i++) {
            result += this.getCoordinate().get(i).getValue();
        }
        return result;
    }

    public int getDim() {
        return super.dim;
    }

    public ArrayList<myLinearEntire> getCoordinate() {
        return super.coordinate;
    }

    public void set(int index, myLinearEntire value) //设置多项式的某一项
    { 
        super.coordinate.set(index, value);
    }
}
