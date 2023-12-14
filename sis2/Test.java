package sis2;
// 测试用例的控制台规则：
// 中括号内为需要填入字符串
// 尖括号为可选参数，默认为列表第一个

import java.util.Scanner;

// #figure(
//   table(
//     columns: 2,
//     align: left+horizon,
//     [```shell-unix-generic
//     getUID <type: "date" | "code" | "seq"> [name]
//     ```],[申请对应类型的新UID，并与一个引用名称name绑定],
//     [```shell-unix-generic
//     secUID <type: "date" | "code" | "seq"> [UID]
//     ```],[查找UID对应的引用名称]
//   )
// )
public class Test {
    public static void main(String[] args) {
        UIDmanager uidm = new UIDmanager();
        try (Scanner sc = new Scanner(System.in)) {
            for(;;)
            {
                String cmd = sc.next();
                if(cmd.equals("getUID"))
                {
                    String type = sc.next();
                    if(type.equals("date"))
                    {
                        String name = sc.next();
                        System.out.println(uidm.nextDate(name));
                    }
                    else if(type.equals("code"))
                    {
                        String name = sc.next();
                        System.out.println(uidm.nextCode(name));
                    }
                    else if(type.equals("seq"))
                    {
                        String name = sc.next();
                        System.out.println(uidm.nextSeq(name));
                    }
                }
                else if(cmd.equals("secUID"))
                {
                    String type = sc.next();
                    if(type.equals("date"))
                    {
                        String uid = sc.next();
                        System.out.println(uidm.secDate(uid));
                    }
                    else if(type.equals("code"))
                    {
                        String uid = sc.next();
                        System.out.println(uidm.secCode(uid));
                    }
                    else if(type.equals("seq"))
                    {
                        String uid = sc.next();
                        System.out.println(uidm.secSeq(uid));
                    }
                } else break;
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
