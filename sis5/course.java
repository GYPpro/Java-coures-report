package sis5;
import java.util.Comparator;

public class course {//课程
    private String UID;//课程编号
    private String name;//课程名称
    private String teacher;//教师名称
    private int credit;//学分
    
    @Override public boolean equals(Object b)
    {
        if(!(b instanceof course)) return false;
        course ts = (course)b;
        if(ts.getUID().equals(this.UID)) return true;
        else return false;
    }

    @Override public int hashCode()
    {
        return UID.hashCode();
    }

    @Override public String toString()
    {
        return UID + " " + name + " " + teacher + " " + credit;
    }


    public static class sortWithUID implements Comparator<course>
    {
        @Override public int compare(course o1, course o2)
        {
            return o1.UID.compareTo(o2.UID);
        }
    }

    public static class sortWithName implements Comparator<course>
    {
        @Override public int compare(course o1, course o2)
        {
            return o1.name.compareTo(o2.name);
        }
    }

    public static class sortWithCredit implements Comparator<course>
    {
        @Override public int compare(course o1, course o2)
        {
            if(o1.credit > o2.credit) return 1;
            else if(o1.credit < o2.credit) return -1;
            else return 0;
        }
    }


    course(String _uuid,final String _name,final String _teacher,final int _credit)
    {
        UID = _uuid;
        name = _name;
        teacher = _teacher;
        credit = _credit;
    }

    course(course _course)//深拷贝构造
    {
        this.UID = _course.getUID();
        this.name = _course.getName();
        this.teacher = _course.getTeacher();
        this.credit = _course.getCredit();
    }


    public String getUID()
    {
        return UID;
    }
    
    public String getName()
    {
        return name;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public int getCredit()
    {
        return credit;
    }
}
