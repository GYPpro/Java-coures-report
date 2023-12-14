package sis10;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * 实现的功能：控制台指令如下：
中括号内为需要填入字符串\
尖括号为可选参数，默认为列表第一个

#figure(
  table(
    align: left+horizon,
    columns: 2,
    [```shell-unix-generic
    addMat [columns] [rows] [digit(1,1)] ... [digit(c,r)]
    ```],[添加一个列数为columns，行数为rows的矩阵],
    [```shell-unix-generic
    addPol [rank] [digit 1] ... [digit r]
    ```],[添加一个阶数为r的多项式],
    [```shell-unix-generic
    addLS [rank] [LE name 1] ... [LE name r]
    ```],[添加一个以`LE 1~r`为基的线性空间],
    [```shell-unix-generic
    show <scope :"all" | "Mat" | "Pol" | "LS">
    ```],[列出对应的所有元素],
    [```shell-unix-generic
    cacuMat [Mat name] <type :"Det" | "Rank" | "inverse" | "transpose" | "Eig">
    ```],[计算矩阵的行列式、秩、逆、转置和对角化],
    [```shell-unix-generic
    cacuPol [Pol name] [digit]
    ```],[计算多项式的值],
    [```shell-unix-generic
    op [LE name1] <operator :"+" | "-" | "*"> [LE name2]
    ```],[对线性空间元素进行基础运算]
  )
)

 */

public class myLinearLib {
    private HashMap<String, myLinearEntire> lib;
    private StringBuffer nowName;

    private void nextName()
    {
        int i = nowName.length() - 1;
        for(; i >= 0; i--)
        {
            if(nowName.charAt(i) != 'z')
            {
                nowName.setCharAt(i, (char)(nowName.charAt(i) + 1));
                break;
            }
            else nowName.setCharAt(i, 'a');
        }
        if(i < 0) nowName.append('a');
    }

    public myLinearLib()
    {
        lib = new HashMap<String, myLinearEntire>();
        nowName = new StringBuffer("a");
    }

    public myLinearLib(myLinearLib other)
    {
        lib = new HashMap<String, myLinearEntire>(other.lib);
        nowName = new StringBuffer(other.nowName);
    }

    public StringBuffer add(myLinearEntire obj)
    {
        StringBuffer rt = new StringBuffer(nowName);
        lib.put(rt.toString(), obj);
        nextName();
        return rt;
    }

    public myLinearEntire get(String name)
    {
        return lib.get(name);
    }

    public void remove(String name)
    {
        lib.remove(name);
    }

    public void clear()
    {
        lib.clear();
    }

    public int size()
    {
        return lib.size();
    }

    public String parseCommand(String cmd) throws Exception
    {
        String[] cmdList = cmd.split(" ");
        if(cmdList[0].equals("addMat"))
        {
            int col = Integer.parseInt(cmdList[1]);
            int row = Integer.parseInt(cmdList[2]);
            myMatrix newMat = new myMatrix(row, col);
            for(int i = 0; i < row; i++)
            {
                for(int j = 0; j < col; j++)
                {
                    newMat.set(i, j, new myRealNum(Double.parseDouble(cmdList[3 + i * col + j])));
                }
            }
            return "成功添加矩阵：" + add(newMat).toString();
        }
        else if(cmdList[0].equals("addPol"))
        {
            int dim = Integer.parseInt(cmdList[1]);
            ArrayList<myLinearEntire> newCoordinate = new ArrayList<myLinearEntire>();
            for(int i = 0; i < dim; i++)
            {
                newCoordinate.add(new myRealNum(Double.parseDouble(cmdList[2 + i])));
            }
            myPolynomial newPol = new myPolynomial(dim, newCoordinate);
            return "成功添加多项式：" + add(newPol).toString();
        }
        else if(cmdList[0].equals("addLS"))
        {
            int rank = Integer.parseInt(cmdList[1]);
            ArrayList<myLinearEntire> newBasis = new ArrayList<myLinearEntire>();
            for(int i = 0; i < rank; i++)
            {
                newBasis.add(get(cmdList[2 + i]));
            }
            myLinearSpace newLS = new myLinearSpace(rank, newBasis);
            add(newLS);
            return nowName.toString();
        }
        else if(cmdList[0].equals("show"))
        {
            if(cmdList[1].equals("all"))
            {
                return "Mats\n" + parseCommand("show Mat") +
                       "Pols\n" + parseCommand("show Pol") +
                       "LSs\n"+ parseCommand("show LS");
            }
            else if(cmdList[1].equals("Mat"))
            {
                String str = "";
                for(String key : lib.keySet())
                {
                    if(lib.get(key) instanceof myMatrix)
                    {
                        str += key + " : " + lib.get(key).toString() + "\n";
                    }
                }
                return str;
            }
            else if(cmdList[1].equals("Pol"))
            {
                String str = "";
                for(String key : lib.keySet())
                {
                    if(lib.get(key) instanceof myPolynomial)
                    {
                        str += key + " : " + lib.get(key).toString() + "\n";
                    }
                }
                return str;
            }
            else if(cmdList[1].equals("LS"))
            {
                String str = "";
                for(String key : lib.keySet())
                {
                    if(lib.get(key) instanceof myLinearSpace)
                    {
                        str += key + " : " + lib.get(key).toString() + "\n";
                    }
                }
                return str;
            }
            else throw new Exception("非法的show指令。");
        }
        else if(cmdList[0].equals("cacuMat"))
        {
            if(get(cmdList[1]) instanceof myMatrix == false) throw new Exception("目标不是矩阵");
            myMatrix cacuMatrix = (myMatrix)get(cmdList[1]);
            if(cmdList[2].equals("Det"))
            {
                return String.valueOf(cacuMatrix.det());
            }
            else if(cmdList[2].equals("Rank"))
            {
                return String.valueOf(cacuMatrix.getRank());
            }
            else if(cmdList[2].equals("inverse"))
            {
                return cacuMatrix.inverse().toString();
            }
            else if(cmdList[2].equals("transpose"))
            {
                return cacuMatrix.transpose().toString();
            }
            else if(cmdList[2].equals("Eig"))
            {
                return cacuMatrix.Eig().toString();
            }
            else throw new Exception("非法的cacuMat指令。");
        }
        else if(cmdList[0].equals("cacuPol"))
        {
            if(get(cmdList[1]) instanceof myPolynomial == false) throw new Exception("目标不是多项式。");
            myPolynomial cacuPolynomial = (myPolynomial)get(cmdList[1]);
            return String.valueOf(cacuPolynomial.cacu(Double.parseDouble(cmdList[2])));
        }
        else if(cmdList[0].equals("op"))
        {
            if(cmdList[2].equals("+"))
            {
                if(get(cmdList[1]).getClass() != get(cmdList[3]).getClass()) throw new Exception("两个元素类型不同。");
                myLinearEntire ans = get(cmdList[1]).add(get(cmdList[3]));
                return add(ans).toString() + ":\n" + ans.toString();
            }
            else if(cmdList[2].equals("-"))
            {
                if(get(cmdList[1]).getClass() != get(cmdList[3]).getClass()) throw new Exception("两个元素类型不同。");
                myLinearEntire ans = get(cmdList[1]).add(get(cmdList[3]).multiply(-1));
                return add(ans).toString() + ":\n" + ans.toString();
            }
            else if(cmdList[2].equals("*"))
            {
                if(get(cmdList[1]).getClass() != get(cmdList[3]).getClass()) throw new Exception("两个元素类型不同。");
                myLinearEntire ans = get(cmdList[1]).multiply(get(cmdList[3]));
                return add(ans).toString() + ":\n" + ans.toString();
            }
            else throw new Exception("非法的op指令。");
        }
        else throw new Exception("非法的指令。");
    }
}
