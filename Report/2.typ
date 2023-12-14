#set text(font:("Times New Roman","Source Han Serif SC"))
#show raw.where(block: false): box.with(
  fill: luma(240),
  inset: (x: 3pt, y: 0pt),
  outset: (y: 3pt),
  radius: 2pt,
)

// Display block code in a larger block
// with more padding.
#show raw.where(block: true): block.with(
  fill: luma(240),
  inset: 10pt,
  radius: 4pt,
)

#show raw: set text(
    font: ("consolas", "Source Han Serif SC")
  )

#set math.equation(numbering: "(1)")

#set text(
    font:("Times New Roman","Source Han Serif SC"),
    style:"normal",
    weight: "regular",
    size: 13pt,
)

#set page(
  paper:"a4",
  number-align: right,
  margin: (x:2.54cm,y:4cm),
  header: [
    #set text(
      size: 25pt,
      font: "KaiTi",
    )
    #align(
      bottom + center,
      [ #strong[暨南大学本科实验报告专用纸(附页)] ]
    )
    #line(start: (0pt,-5pt),end:(453pt,-5pt))
  ]
)

= 基础IO操作
\
#text("*") 实验项目类型：设计性\
#text("*")此表由学生按顺序填写\

#text(
  font:"KaiTi",
  size: 15pt
)[
课程名称#underline[#text("   面向对象程序设计/JAVA语言   ")]成绩评定#underline[#text("       ")]\
实验项目名称#underline[#text("   基础IO操作   ")]指导老师#underline[#text("   干晓聪   ")]\
实验项目编号#underline[#text(" 1 ")]实验项目类型#underline[#text("  设计性  ")]实验地点#underline[#text(" 数学系机房 ")]\
学生姓名#underline[#text("   郭彦培   ")]学号#underline[#text("   2022101149   ")]\
学院#underline[#text(" 信息科学技术学院 ")]系#underline[#text(" 数学系 ")]专业#underline[#text(" 信息管理与信息系统 ")]\
实验时间#underline[#text(" 2023年10月20日上午 ")]#text("~")#underline[#text("  2023年10月20日中午  ")]\
]
#set heading(
  numbering: "一、"
  )  
#set par( first-line-indent: 1.8em)

= 实验目的
\
#h(1.8em)熟悉输入输出与基础面向过程语法


= 实验环境
\
#h(1.8em)计算机：PC X64

操作系统：Windows

编程语言：Java

IDE：Visual Studio Code


= 程序原理
\
#h(1.8em)使用了Scanner实现了输入一个给定长度的差分数组，求出其原数组，即对数组进行逐项求和。

#pagebreak()
= 程序代码
\
文件:`sis1\basicIO.java`
```java
package sis1;

import java.util.ArrayList;
import java.util.Scanner;

public class basicIO {
    public static void main(String[] args) {
        String initOutputMessage = "Input a div_arr(差分数组),this program will cacu the row arr.\nfirstly, you should input the length of your arr\n";
        System.out.print(initOutputMessage);
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>(n);
        for(int i = 0;i < n;i ++)
        {
            int tmp = sc.nextInt();
            arr.add(tmp);
        }
        System.out.print(arr.get(0) + " ");
        for(int i = 1;i < n;i ++)
        {
            arr.set(i,arr.get(i-1)+arr.get(i));
            System.out.print(arr.get(i) + " ");
        }
        System.out.print("\n");
        sc.close();
    }
}

```

= 出现的问题、原因与解决方法
\
#h(1.8em)未出现问题


#pagebreak()
= 测试数据与运行结果

#figure(
  table(
    align: left + horizon,
    columns: 3,
    [*输入*],[*输出*],[*解释*],
    [],[`Input a div_arr(差分数组),this program will cacu the row arr.
firstly, you should input the length of your arr`],[],
    [`3 1 2 3`],[`1 3 6 `],[输入三个数：\ 1 2 3 \ 答案为1 3 6]
  )
)