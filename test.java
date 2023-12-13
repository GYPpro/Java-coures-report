import java.util.ArrayList;
import java.util.Collections;

class Rt{
    final private ArrayList<mt> t;
    public ArrayList<mt> getar()
    {
        ArrayList<mt> rt = new ArrayList<mt>(t);
        return rt;
    }
    public Rt(ArrayList<mt> _t)
    {
        t = _t;
    }
}

class mt implements Comparable<mt>{
    private int n;
    @Override public int compareTo(mt b)
    {
        return -n+b.n;
    }
    mt(final int _n)
    {
        n = _n;
    }
    public void setn(int _n)
    {
        n = _n;
    }

    public int getn()
    {
        return n;
    }
}

public class test {
    public static void main(String[] args) {
        // myIO io = new myIO();
        // ArrayList<Integer> arrayList = new ArrayList<Integer>();
        // arrayList.add(1145);
        // io.printString(new String(arrayList.get(0).toString()));
        // int n = io.readInt();
        // io.readIntList(arrayList, n);
        // io.printIntList(arrayList);
        ArrayList<mt> s = new ArrayList<mt>();
        s.add(new mt(1));
        Rt rt = new Rt(s);

        s.add(new mt(2));

        ArrayList<mt> fs = rt.getar();

        for(int i = 0;i < fs.size();i ++)
            System.out.print(fs.get(i).getn() + " ");
        System.out.print("\n");

        fs.add(new mt(3));

        fs = rt.getar();

        for(int i = 0;i < fs.size();i ++)
            System.out.print(fs.get(i).getn() + " ");
        System.out.print("\n");

        Collections.sort(fs);

        ArrayList<Integer> fs2 = new ArrayList<Integer>();

        Collections.sort(fs2);

        for(int i = 0;i < fs.size();i ++)
            System.out.print(fs.get(i).getn() + " ");
        System.out.print("\n");
    }
}
