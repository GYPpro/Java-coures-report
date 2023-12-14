package sis10;

import java.util.ArrayList;

public class myMatrix extends myLinearEntire{
    private int row;
    private int col;
    private ArrayList<ArrayList<myLinearEntire>> matrix;

    //↓覆写超类方法
    @Override
    public boolean equals(Object other){
        if(other instanceof myMatrix){
            myMatrix mOther = (myMatrix)other;
            if(this.row != mOther.row || this.col != mOther.col) return false;
            for(int i = 0; i < this.row; i++){
                for(int j = 0; j < this.col; j++){
                    if(!this.matrix.get(i).get(j).equals(mOther.matrix.get(i).get(j))) return false;
                }
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode(){
        int hash = 0;
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.col; j++){
                hash += this.matrix.get(i).get(j).hashCode();
            }
        }
        return hash;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.col; j++){
                str += this.matrix.get(i).get(j).toString() + " ";
            }
            if(i != this.row-1)str += "\n";
        }
        return str;
    }

    //↓构造函数
    public myMatrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.matrix = new ArrayList<ArrayList<myLinearEntire>>();
        for (int i = 0; i < row; i++) {
            ArrayList<myLinearEntire> newRow = new ArrayList<myLinearEntire>();
            for (int j = 0; j < col; j++) {
                newRow.add(new myRealNum(0));
            }
            this.matrix.add(newRow);
        }
    }

    public myMatrix(int row, int col, ArrayList<ArrayList<myLinearEntire>> matrix) {
        this.row = row;
        this.col = col;
        this.matrix = new ArrayList<ArrayList<myLinearEntire>>(matrix);
    }

    public myMatrix(myMatrix other) {
        this.row = other.row;
        this.col = other.col;
        this.matrix = new ArrayList<ArrayList<myLinearEntire>>(other.matrix);
    }

    //↓ 覆写父类方法
    public myMatrix add(myLinearEntire other) throws Exception {
        if (other instanceof myMatrix) {
            myMatrix mOther = (myMatrix) other;
            if (this.getRow() != mOther.getRow() || this.getCol() != mOther.getCol()) {
                throw new IllegalArgumentException("行列不同的矩阵不能相加。");
            }
            ArrayList<ArrayList<myLinearEntire>> newMatrix = new ArrayList<ArrayList<myLinearEntire>>();
            for (int i = 0; i < this.row; i++) {
                ArrayList<myLinearEntire> newRow = new ArrayList<myLinearEntire>();
                for (int j = 0; j < this.col; j++) {
                    newRow.add(this.matrix.get(i).get(j).add(mOther.matrix.get(i).get(j)));
                }
                newMatrix.add(newRow);
            }
            return new myMatrix(this.row, this.col, newMatrix);
        } else {
            throw new Exception("非法的矩阵相加。");
        }
    }

    public myMatrix multiply(myLinearEntire other) throws Exception {
        if (other instanceof myMatrix) {
            myMatrix mOther = (myMatrix) other;
            if (this.getCol() != mOther.getRow()) {
                throw new IllegalArgumentException("行列不匹配的矩阵不能相乘。");
            }
            ArrayList<ArrayList<myLinearEntire>> newMatrix = new ArrayList<ArrayList<myLinearEntire>>();
            for (int i = 0; i < this.row; i++) {
                ArrayList<myLinearEntire> newRow = new ArrayList<myLinearEntire>();
                for (int j = 0; j < mOther.col; j++) {
                    myLinearEntire sum = new myRealNum(0);
                    for (int k = 0; k < this.col; k++) {
                        sum = sum.add(this.matrix.get(i).get(k).multiply(mOther.matrix.get(k).get(j)));
                    }
                    newRow.add(sum);
                }
                newMatrix.add(newRow);
            }
            return new myMatrix(this.row, mOther.col, newMatrix);
        } else {
            throw new Exception("非法的矩阵相乘。");
        }
    }

    public <N> myMatrix multiply(N other) throws Exception {
        if (other instanceof myRealNum) {
            myRealNum rOther = (myRealNum) other;
            ArrayList<ArrayList<myLinearEntire>> newMatrix = new ArrayList<ArrayList<myLinearEntire>>();
            for (int i = 0; i < this.row; i++) {
                ArrayList<myLinearEntire> newRow = new ArrayList<myLinearEntire>();
                for (int j = 0; j < this.col; j++) {
                    newRow.add(this.matrix.get(i).get(j).multiply(rOther));
                }
                newMatrix.add(newRow);
            }
            return new myMatrix(this.row, this.col, newMatrix);
        } else {
            throw new Exception("非法的矩阵数乘。");
        }
    }

    public double getValue()
    {
        try {
            return this.det();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void print() throws Exception {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col - 1; j++) {
                this.matrix.get(i).get(j).print();
                System.out.print(" ");
            }
            this.matrix.get(i).get(this.col - 1).print();
            System.out.println();
        }
    }

    public myLinearEntire negative() throws Exception {
        ArrayList<ArrayList<myLinearEntire>> newMatrix = new ArrayList<ArrayList<myLinearEntire>>();
        for (int i = 0; i < this.row; i++) {
            ArrayList<myLinearEntire> newRow = new ArrayList<myLinearEntire>();
            for (int j = 0; j < this.col; j++) {
                newRow.add(this.matrix.get(i).get(j).negative());
            }
            newMatrix.add(newRow);
        }
        return new myMatrix(this.row, this.col, newMatrix);
    }

    //↓矩阵特有方法
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public double det() throws Exception //计算行列式
    {
        if (this.row != this.col) {
            throw new Exception("非方阵无法求行列式。");
        }
        if (this.row == 1) {
            return ((myRealNum) this.matrix.get(0).get(0)).getNum();
        }
        double ans = 0;
        for (int i = 0; i < this.row; i++) {
            myMatrix subMatrix = new myMatrix(this.row - 1, this.col - 1);
            for (int j = 1; j < this.row; j++) {
                for (int k = 0; k < this.col; k++) {
                    if (k < i) {
                        subMatrix.matrix.get(j - 1).add(this.matrix.get(j).get(k));
                    } else if (k > i) {
                        subMatrix.matrix.get(j - 1).add(this.matrix.get(j).get(k));
                    }
                }
            }
            ans += ((myRealNum) this.matrix.get(0).get(i)).getNum() * subMatrix.det() * (int) Math.pow(-1, i);
        }
        return ans;
    }

    public int getRank() throws Exception //计算秩
    {
        if (this.row == 1) {
            for (int i = 0; i < this.col; i++) {
                if (((myRealNum) this.matrix.get(0).get(i)).getNum() != 0) {
                    return 1;
                }
            }
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < this.col; i++) {
            myMatrix subMatrix = new myMatrix(this.row - 1, this.col - 1);
            for (int j = 1; j < this.row; j++) {
                for (int k = 0; k < this.col; k++) {
                    if (k < i) {
                        subMatrix.matrix.get(j - 1).add(this.matrix.get(j).get(k));
                    } else if (k > i) {
                        subMatrix.matrix.get(j - 1).add(this.matrix.get(j).get(k));
                    }
                }
            }
            if (((myRealNum) this.matrix.get(0).get(i)).getNum() != 0) {
                ans += subMatrix.getRank();
            }
        }
        return ans;
    }

    public myMatrix inverse() throws Exception //计算逆矩阵
    {
        if (this.row != this.col) {
            throw new Exception("非方阵无法求逆。");
        }
        if (this.det() == 0) {
            throw new Exception("行列式为0，无法求逆。");
        }
        myMatrix ans = new myMatrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                myMatrix subMatrix = new myMatrix(this.row - 1, this.col - 1);
                for (int k = 0; k < this.row; k++) {
                    for (int l = 0; l < this.col; l++) {
                        if (k < i && l < j) {
                            subMatrix.matrix.get(k).add(this.matrix.get(k).get(l));
                        } else if (k < i && l > j) {
                            subMatrix.matrix.get(k).add(this.matrix.get(k).get(l));
                        } else if (k > i && l < j) {
                            subMatrix.matrix.get(k - 1).add(this.matrix.get(k).get(l));
                        } else if (k > i && l > j) {
                            subMatrix.matrix.get(k - 1).add(this.matrix.get(k).get(l));
                        }
                    }
                }
                ans.matrix.get(i).add(new myRealNum(subMatrix.det() * (int) Math.pow(-1, i + j)));
            }
        }
        ans = ans.transpose();
        ans = ans.multiply(new myRealNum(1 / this.det()));
        return ans;
    }

    public myMatrix transpose() throws Exception //计算转置矩阵
    {
        myMatrix ans = new myMatrix(this.col, this.row);
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                ans.matrix.get(i).add(this.matrix.get(j).get(i));
            }
        }
        return ans;
    }

    public myMatrix Eig() throws Exception//进行对角化
    {
        if (this.row != this.col) {
            throw new Exception("非方阵无法求特征值。");
        }
        myMatrix ans = new myMatrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
            ans.matrix.get(i).add(this.matrix.get(i).get(i));
        }
        return ans;
    }

    public void set(int i, int j, myRealNum myRealNum) //重设矩阵某个元素
    {
        this.matrix.get(i).set(j, myRealNum);
    }

}
