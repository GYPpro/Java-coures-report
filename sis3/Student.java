package sis3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Student implements Comparator<Student>{
    private int UUID;//学号
    private String name;//姓名
    private Set<course> curriculumList;//所选课程列表
    private HashMap<course,Integer> rankList;//成绩单
    private double points;

    @Override
    public int compare(Student s1, Student s2) 
    {
        return 1;
    }

    Student(int _uuid,String _name)
    {
        UUID = _uuid;
        name = new String(_name);
        curriculumList = new HashSet<course>();
    }

    Student(Student _student) throws Exception //深拷贝构造
    {
        this.UUID = _student.getUUID();
        this.name = _student.getName();
        
    }

    public void addCourse(course _cs)  //选课
    {
        curriculumList.add(_cs);
    }

    public void ranking(course _cs,Integer rank) throws Exception //首次录入
    {
        if(rankList.containsKey(_cs)) throw new Exception("重复录入成绩\n");
        else rankList.put(_cs,rank);
    }
    
    public void reRanking(course _cs,Integer rank) throws Exception //补考
    {
        if(!rankList.containsKey(_cs)) throw new Exception("未找到对应成绩\n");
        else rankList.replace(_cs, rank);
    }

    public int getUUID()
    {
        return UUID;
    }

    public String getName()
    {
        String rt = new String(name);
        return rt;
    }

}

