package sis2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class UIDmanager {
    private HashMap<String,Object> dateHM = new HashMap<String, Object>();
    private HashMap<String,Object> codeHM = new HashMap<String, Object>();
    private HashMap<String,Object> seqHM = new HashMap<String, Object>();

    final Random rd = new Random();

    final Integer DATENUM_LENGTH;
    final Integer SEQ_LENGTH;
    final Integer CODE_LENGTH;
    private Integer nextDataNum = 0;
    private Integer nextSeq = 0;

    private char getRandChar()
    {
        int flg = rd.nextInt(26+10);
        if(flg < 10) return (char)(48+flg);
        else return (char)(55+flg);
    }

    public UIDmanager()
    {
        DATENUM_LENGTH = 4;
        SEQ_LENGTH = 10;
        CODE_LENGTH = 5;
    }

    public UIDmanager(int _SEQ_LENGTH,int _DATENUM_LENGTH,int _CODE_LENGTH)
    {
        DATENUM_LENGTH = _DATENUM_LENGTH;
        SEQ_LENGTH = _SEQ_LENGTH;
        CODE_LENGTH = _CODE_LENGTH;
    }

    public String nextDate(Object c)
    {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
        String s = sf.format(date);
        nextDataNum++;
        Integer tmp = nextDataNum;
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < DATENUM_LENGTH;i ++)
        {
            sb.append(tmp % 10);
            tmp /= 10;
        }
        String rt = s + sb.reverse().toString();
        dateHM.put(rt, c);
        return rt;
    }
    
    public String nextCode(Object c)
    {
        StringBuffer rt = new StringBuffer();
        for(int k = 0;k < 4;k ++)
        {
            for(int i = 0;i < CODE_LENGTH;i ++)
            {
                rt.append(getRandChar());
            }
            if(k != 3)rt.append('-');
        }
        codeHM.put(rt.toString(), c);
        return rt.toString();
    }

    public String nextSeq(Object c)
    {
        StringBuffer rt = new StringBuffer();
        nextSeq ++;
        Integer tmp = nextSeq;
        for(int i = 0;i < SEQ_LENGTH;i ++)
        {
            rt.append(tmp % 10);
            tmp /= 10;
        }
        seqHM.put(rt.reverse().toString(), c);
        return rt.toString();
    }

    public Object secDate(String uid)
    {
        return dateHM.get(uid);
    }

    public Object secCode(String uid)
    {
        return codeHM.get(uid);
    }

    public Object secSeq(String uid)
    {
        return seqHM.get(uid);
    }

}
