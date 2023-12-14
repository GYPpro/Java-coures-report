package sis5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Student{
    private String UID;//学号
    private String name;//姓名
    private Set<course> curriculumList;//所选课程列表
    private HashMap<course,score> scoreList;//成绩单
    private double avgGPA = 0;//平均绩点
    private int credit = 0;//已修学分

    private void cacuGPA() //计算平均绩点
    {
        double sum = 0;
        for(course cs : curriculumList)
        {
            sum += scoreList.get(cs).getGPA();
        }
        avgGPA = sum / curriculumList.size();
    }

    @Override public boolean equals(Object b)
    {
        if(!(b instanceof Student)) return false;
        Student ts = (Student)b;
        if(ts.getUID().equals(this.UID)) return true;
        else return false;
    }

    @Override public int hashCode()
    {
        return UID.hashCode();
    }

    @Override public String toString()
    {
        return UID + " " + name + " " + avgGPA + " " + credit;
    }


    public static class sortWithGPA implements Comparator<Student>
    {
        @Override public int compare(Student o1, Student o2)
        {
            if(o1.avgGPA < o2.avgGPA) return 1;
            else if(o1.avgGPA > o2.avgGPA) return -1;
            else return 0;
        }
    }

    public static class sortWithUID implements Comparator<Student>
    {
        @Override public int compare(Student o1, Student o2)
        {
            return o1.UID.compareTo(o2.UID);
        }
    }

    public static class sortWithName implements Comparator<Student>
    {
        @Override public int compare(Student o1, Student o2)
        {
            return o1.name.compareTo(o2.name);
        }
    }

    
    Student(String _uid,String _name)
    {
        UID = new String(_uid);
        name = new String(_name);
        curriculumList = new HashSet<course>();
        scoreList = new HashMap<course,score>();
    }

    Student(Student _student) //深拷贝构造
    {
        this.UID = _student.getUID();
        this.name = _student.getName();
        this.scoreList = new HashMap<course,score>(_student.scoreList);
        this.avgGPA = _student.avgGPA;
        this.credit = _student.credit;
        curriculumList = new HashSet<course>();
        for(course cs : _student.curriculumList)
        {
            this.curriculumList.add(new course(cs));
        }
    }


    public void addCourse(course _cs)  //选课
    {
        curriculumList.add(_cs);
        credit += _cs.getCredit();
    }

    public void ranking(course _cs,score rank) throws Exception //首次录入成绩
    {
        if(scoreList.containsKey(_cs)) throw new Exception("重复录入成绩\n");
        else scoreList.put(_cs,rank);
    }
    
    public void reRanking(course _cs,score rank) throws Exception //补考
    {
        if(!scoreList.containsKey(_cs)) throw new Exception("未找到对应成绩\n");
        else scoreList.replace(_cs, rank);
    }


    public String getUID()
    {
        return new String(UID);
    }

    public String getName()
    {
        String rt = new String(name);
        return rt;
    }

    public double getAvgGPA()
    {
        cacuGPA();
        return avgGPA;
    }

    public score getScore(course _cs) throws Exception
    {
        if(!scoreList.containsKey(_cs)) throw new Exception("未找到对应成绩\n");
        else return new score(scoreList.get(_cs));
    }
}

