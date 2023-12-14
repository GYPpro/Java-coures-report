package sis2;
import java.util.Scanner;

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
