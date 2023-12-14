package rubbish;

import java.util.ArrayList;

public class myIO {
    private int MAX_INPUT_LENGTH;

    ArrayList<ArrayList<Boolean>> m;

    public myIO()
    {
        MAX_INPUT_LENGTH = 114;

        int i = 3,j= 3;
        m.get(i).set(j,Boolean.valueOf((boolean)m.get(i-1).get(j) || (boolean)m.get(i).get(j-2)));
    }

    private boolean isDigit(char c)
    {
        if((c > '0') && (c < '9')) return true;
        else return false;
    }
    
    public void setMaxInputLength(int _MaxInputLength)
    {
        MAX_INPUT_LENGTH = _MaxInputLength;
    }


    public String readString()
    {
        byte[] buf = new byte[MAX_INPUT_LENGTH];
        try {
            System.in.read(buf);
        } catch (Exception e) {
            System.err.println("IO expection\n");
        };
        String s = new String(buf);
        return s;
    }

    public void readIntList(ArrayList<Integer> _arr,int _n)
    {
        _arr.clear();
        for(int i = 0;i < _n;i ++)
        {
            byte[] buf = new byte[MAX_INPUT_LENGTH];
            try {
                System.in.read(buf);
            } catch (Exception e) {
                System.err.println("IO expection\n");
            };
            String s = new String(buf);  
            _arr.add(Integer.parseInt(s));
        }
    }

    public Integer readInt()
    {
        byte[] buf = new byte[MAX_INPUT_LENGTH];
        try {
            System.in.read(buf);
        } catch (Exception e) {
            System.err.println("IO expection\n");
        };
        String s = new String(buf);
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < s.length();i ++)
        {
            if(isDigit(s.charAt(i))) sb.append(s.charAt(i));
        }
        return Integer.parseInt(sb.toString());
    } 


    public void printString(String s)
    {
        System.out.println(s);
    }

    public void printInt(Integer _n)
    {
        printString(_n.toString());
    }

    public void printIntList(final ArrayList<Integer> _arr)
    {
        for(int i = 0;i < _arr.size();i ++)
            printInt(_arr.get(i));
    }

}
