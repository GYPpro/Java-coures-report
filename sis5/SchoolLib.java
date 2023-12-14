package sis5;
import java.util.ArrayList;
import java.util.HashMap;

import sis2.UIDmanager;


// 测试用例的控制台规则：\
// 中括号内为需要填入字符串\
// 尖括号为可选参数，默认为列表第一个

// #figure(table(
//   columns: 2,
//   align: left+horizon,
//   [#align(left)[```shell-unix-generic
// addStu [student name]
// ```]],[添加学生],
//   [#align(left)[```shell-unix-generic
// addCur [Course name] [teacher name] [credit]
// ```]],[添加课程],
//   [#align(left)[```shell-unix-generic
// celCur [student UID] [Course name] 
// ```]],[选课],
//  [#align(left)[```shell-unix-generic
// showStu <sort with:"UID" | "name" | "GPA">
// ```]],[显示学生列表],
//  [#align(left)[```shell-unix-generic
// showCur <sort with:"UID" | "Course name" | " credit">
// ```]],[显示课程列表],
//   [#align(left)[```shell-unix-generic
// addScore [student UID] [Course name] [Normally score] [Exam score]
// ```]],[添加成绩],
//   [#align(left)[```shell-unix-generic
// addResc [student UID] [Course name] [Exam score] 
//   ```]],[添加补考成绩],
//  [#align(left)[```shell-unix-generic
// cacu
// ```]],[手动刷新总评成绩与绩点]
// ))

public class SchoolLib {
    private HashMap<String,Student> studentList;
    private HashMap<String,course> courseList;

    final UIDmanager uidm = new UIDmanager();

    private enum sortStuWith
    {
        UID,
        name,
        GPA
    }

    private enum sortCurWith
    {
        UID,
        name,
        credit
    }

    public SchoolLib()
    {
        studentList = new HashMap<String,Student>();
        courseList = new HashMap<String,course>();
    }

    public SchoolLib(final SchoolLib _scl) //深拷贝构造
    {
        studentList = new HashMap<String,Student>(_scl.studentList);
        courseList = new HashMap<String,course>(_scl.courseList);
    }
    private void addStudent(final Student student) 
    {
        studentList.put(student.getUID(),new Student(student));
    }

    private void addCourse(final course cs)
    {
        courseList.put(cs.getName(),new course(cs));
    }

    private void celCur(final String stuUID,final String csName) throws Exception
    {
        if(!studentList.containsKey(stuUID)) throw new Exception("未找到对应学生\n");
        if(!courseList.containsKey(csName)) throw new Exception("未找到对应课程\n");
        Student stu = studentList.get(stuUID);
        course cs = courseList.get(csName);
        stu.addCourse(cs);
    }

    private ArrayList<Student> sortLocalStuList(final sortStuWith sw)
    {
        ArrayList<Student> tmp = new ArrayList<Student>();
        for(Student stu : studentList.values())
        {
            tmp.add(new Student(stu));
        }
        if(sw == sortStuWith.UID)
        {
            tmp.sort(new Student.sortWithUID());
        }
        else if(sw == sortStuWith.name)
        {
            tmp.sort(new Student.sortWithName());
        }
        else if(sw == sortStuWith.GPA)
        {
            tmp.sort(new Student.sortWithGPA());
        }
        return tmp;
    }

    private ArrayList<course> sortLocalCurList(final sortCurWith sw)
    {
        ArrayList<course> tmp = new ArrayList<course>();
        for(course cs : courseList.values())
        {
            tmp.add(new course(cs));
        }
        if(sw == sortCurWith.UID)
        {
            tmp.sort(new course.sortWithUID());
        }
        else if(sw == sortCurWith.name)
        {
            tmp.sort(new course.sortWithName());
        }
        else if(sw == sortCurWith.credit)
        {
            tmp.sort(new course.sortWithCredit());
        }
        return tmp;
    }

    public String parseCommand(final String cmd) throws Exception//解析命令
    {
        String[] cmdList = cmd.split(" ");
        if(cmdList[0].equals("addStu"))
        {
            if(cmdList.length != 2) throw new Exception("命令格式错误\n");
            String name = cmdList[1];
            String uid = uidm.nextDate();
            Student stu = new Student(uid,name);
            uidm.bindUID(uid, stu);
            addStudent(stu);
            return "添加学生成功，UID为" + uid + "\n";
        }
        else if(cmdList[0].equals("addCur"))
        {
            if(cmdList.length != 4) throw new Exception("命令格式错误\n");
            String name = cmdList[1];
            String teacher = cmdList[2];
            int credit = Integer.parseInt(cmdList[3]);
            course cs = new course(uidm.nextSeq(),name,teacher,credit);
            uidm.bindUID(cs.getUID(), cs);
            addCourse(cs);
            return "添加课程成功，UID为" + cs.getUID() + "\n";
        }
        else if(cmdList[0].equals("celCur"))
        {
            if(cmdList.length != 3) throw new Exception("命令格式错误\n");
            String stuUID = cmdList[1];
            String csName = cmdList[2];
            celCur(stuUID,csName);
            return "选课成功\n";
        }
        else if(cmdList[0].equals("showStu"))
        {
            if(cmdList.length != 2) throw new Exception("命令格式错误\n");
            String sortWith = cmdList[1];
            ArrayList<Student> tmp = sortLocalStuList(sortStuWith.valueOf(sortWith));
            StringBuilder sb = new StringBuilder();
            for(Student stu : tmp)
            {
                sb.append(stu.toString());
                sb.append("\n");
            }
            return sb.toString();
        }
        else if(cmdList[0].equals("showCur"))
        {
            if(cmdList.length != 2) throw new Exception("命令格式错误\n");
            String sortWith = cmdList[1];
            ArrayList<course> tmp = sortLocalCurList(sortCurWith.valueOf(sortWith));
            StringBuilder sb = new StringBuilder();
            for(course cs : tmp)
            {
                sb.append(cs.toString());
                sb.append("\n");
            }
            return sb.toString();
        }
        else if(cmdList[0].equals("addScore"))
        {
            if(cmdList.length != 5) throw new Exception("命令格式错误\n");
            String stuUID = cmdList[1];
            String csName = cmdList[2];
            int normallyScore = Integer.parseInt(cmdList[3]);
            int examScore = Integer.parseInt(cmdList[4]);
            if(!studentList.containsKey(stuUID)) throw new Exception("未找到对应学生\n");
            if(!courseList.containsKey(csName)) throw new Exception("未找到对应课程\n");
            Student stu = studentList.get(stuUID);
            course cs = courseList.get(csName);
            stu.ranking(cs, new score(normallyScore,examScore));
            return "添加成绩成功\n";
        }
        else if(cmdList[0].equals("addResc"))
        {
            if(cmdList.length != 4) throw new Exception("命令格式错误\n");
            String stuUID = cmdList[1];
            String csName = cmdList[2];
            int examScore = Integer.parseInt(cmdList[3]);
            if(!studentList.containsKey(stuUID)) throw new Exception("未找到对应学生\n");
            if(!courseList.containsKey(csName)) throw new Exception("未找到对应课程\n");
            Student stu = studentList.get(stuUID);
            course cs = courseList.get(csName);
            score tmp = stu.getScore(cs);
            tmp.resetExamScore(examScore);
            stu.reRanking(cs, tmp);
            return "重设补考成绩成功\n";
        }
        else if(cmdList[0].equals("cacu"))
        {
            for(Student stu : studentList.values())
            {
                stu.getAvgGPA();
            }
            return "计算完成\n";
        }
        else throw new Exception("未知命令\n");
    }
}