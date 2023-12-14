package sis10;

import java.util.ArrayList;

public class myLinearSpace extends myLinearEntire {

    private int rank;
    private ArrayList<myLinearEntire> basis;

    //↓覆写超类方法
    @Override
    public boolean equals(Object other){
        if(other instanceof myLinearSpace){
            myLinearSpace mOther = (myLinearSpace)other;
            if(this.rank != mOther.rank) return false;
            for(int i = 0; i < this.rank; i++){
                if(!this.basis.get(i).equals(mOther.basis.get(i))) return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        int hash = 0;
        for(int i = 0; i < this.rank; i++){
            hash += this.basis.get(i).hashCode();
        }
        return hash;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.rank; i++){
            str += this.basis.get(i).toString() + "\n";
        }
        return str;
    }

    //↓构造函数
    public myLinearSpace(int rank) {
        this.rank = rank;
        this.basis = new ArrayList<myLinearEntire>();
    }

    public myLinearSpace(int rank, ArrayList<myLinearEntire> _basis) {
        this.rank = rank;
        this.basis = new ArrayList<myLinearEntire>(_basis);
    }

    public myLinearSpace(myLinearSpace other) {
        this.rank = other.rank;
        this.basis = new ArrayList<myLinearEntire>(other.basis);
    }

    //↓覆写父类方法
    public myLinearSpace add(myLinearEntire other) throws Exception
    {
        if (other instanceof myLinearSpace) {
            myLinearSpace mOther = (myLinearSpace) other;
            if (this.getRank() != mOther.getRank()) {
                throw new IllegalArgumentException("秩不同的线性空间不能相加。");
            }
            ArrayList<myLinearEntire> newBasis = new ArrayList<myLinearEntire>();
            for (int i = 0; i < this.rank; i++) {
                newBasis.add(this.basis.get(i).add(mOther.basis.get(i)));
            }
            return new myLinearSpace(this.rank, newBasis);
        } else {
            throw new Exception("非法的线性空间相加。");
        }
    }
    
    public myLinearSpace multiply(myLinearEntire other) throws Exception
    {
        if (other instanceof myLinearSpace) {
            myLinearSpace mOther = (myLinearSpace) other;
            if (this.getRank() != mOther.getRank()) {
                throw new IllegalArgumentException("秩不同的线性空间不能相乘。");
            }
            ArrayList<myLinearEntire> newBasis = new ArrayList<myLinearEntire>();
            for (int i = 0; i < this.rank; i++) {
                newBasis.add(this.basis.get(i).multiply(mOther.basis.get(i)));
            }
            return new myLinearSpace(this.rank, newBasis);
        } else {
            throw new Exception("非法的线性空间相乘。");
        }
    }

    public <N> myLinearSpace multiply(N other) throws Exception
    {
        throw new Exception("线性空间的数乘无意义");
    }

    public double getValue() throws Exception
    {
        throw new Exception("线性空间的值无意义");
    }

    public myLinearSpace negative() throws Exception
    {
        ArrayList<myLinearEntire> newBasis = new ArrayList<myLinearEntire>();
        for (int i = 0; i < this.rank; i++) {
            newBasis.add(this.basis.get(i).negative());
        }
        return new myLinearSpace(this.rank, newBasis);
    }

    //↓线性空间特有方法
    public int getRank() 
    {
        return this.rank;
    }

    public ArrayList<myLinearEntire> getBasis() 
    {
        return new ArrayList<myLinearEntire>(basis);
    }

    public void print() throws Exception
    {
        System.out.println("线性空间的秩为" + this.rank);
        System.out.println("线性空间的基为");
        for (int i = 0; i < this.rank; i++) {
            System.out.print("第" + i + "个基向量为");
            this.basis.get(i).print();
        }
    }
}
