package sis2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class UIDmanager {
    private HashMap<Integer,Object> dateHM = new HashMap<Integer, Object>();
    private HashMap<String,Object> codeHM = new HashMap<String, Object>();
    private HashMap<Integer,Object> seqHM = new HashMap<Integer, Object>();

    final Random rd = new Random();

    final Integer DATENUM_LENGTH;
    final Integer SEQ_LENGTH;
    final Integer CODE_LENGTH;
    private Integer nextDataNum = 0;
    private Integer nextSeq = 0;

    private char getRandChar()
    {
        int flg = rd.nextInt(26+26+10);
        if(flg < 10) return (char)(48+flg);
        else if(flg < 10+26) return (char)(55+flg);
        else return (char)(97-10-26+flg);
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

    public Integer nextDate(Object c)
    {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yymmdd");
        String s = sf.format(date);
        nextDataNum++;
        Integer rt = (int) (Integer.parseInt(s) * (Double.valueOf(Math.pow(10, DATENUM_LENGTH))) + nextDataNum);
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
            rt.append('-');
        }
        codeHM.put(rt.toString(), c);
        return rt.toString();
    }

    public Integer nextSeq(Object c)
    {
        nextSeq++;
        seqHM.put(nextSeq, c);
        return nextSeq;
    }

    public Object secDate(Integer uid)
    {
        return dateHM.get(uid);
    }

    public Object secCode(String uid)
    {
        return codeHM.get(uid);
    }

    public Object secSeq(Integer uid)
    {
        return seqHM.get(uid);
    }

}
