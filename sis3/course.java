package sis3;

public class course {//课程
    private int UUID;//课程编号
    private String name;//课程名称
    private String teacher;//教师名称
    private int credit;//学分
    
    @Override 
    public boolean equals(Object b)
    {
        if(!(b instanceof course)) return false;
        course tc = (course)b;
        if(tc.getUUID() == this.UUID) return true;
        else return false;
    }

    @Override 
    public int hashCode()
    {
        return UUID;
    }

    course(int _uuid,final String _name,final String _teacher,final int _credit)
    {
        UUID = _uuid;
        name = _name;
        teacher = _teacher;
        credit = _credit;
    }

    public int getUUID()
    {
        return UUID;
    }
    
    public String getName()
    {
        return name;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public int gerCredit()
    {
        return credit;
    }
}
