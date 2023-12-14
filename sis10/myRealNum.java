package sis10;

public class myRealNum extends myLinearEntire{
    private double value;

    //↓覆写超类方法
    @Override
    public boolean equals(Object other){
        if(other instanceof myRealNum){
            myRealNum mOther = (myRealNum)other;
            if(this.value != mOther.value) return false;
            else return true;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        return (int)this.value;
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

    //↓构造函数
    public myRealNum(double value) {
        this.value = value;
    }

    public myRealNum(myRealNum other) {
        this.value = other.value;
    }

    //↓覆写父类方法
    public myLinearEntire add(myLinearEntire other) throws Exception {
        if (other instanceof myRealNum) {
            myRealNum mOther = (myRealNum) other;
            return new myRealNum(this.value + mOther.value);
        } else {
            throw new Exception("非法的实数相加。");
        }
    }

    public <N> myLinearEntire multiply(N other) throws Exception  {
        return new myRealNum(value * (double) other);
    }

    public myLinearEntire multiply(myLinearEntire other) throws Exception {
        if (other instanceof myRealNum) {
            myRealNum mOther = (myRealNum) other;
            return new myRealNum(this.value * mOther.value);
        } else {
            throw new Exception("非法的实数相乘。");
        }
    } 

    public void print() throws Exception {
        System.out.println(this.value);
    }

    public double getValue() {
        return this.value;
    }

    public double getNum() {
        return this.value;
    }

    public myLinearEntire negative() throws Exception {
        return new myRealNum(-this.value);
    }
    
}
