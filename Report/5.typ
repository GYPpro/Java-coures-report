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

#show raw: set text(
    font: ("consolas", "Source Han Serif SC")
  )

= 实现一个高性能的基础正则表达式匹配方法
\
#text("*") 实验项目类型：设计性\
#text("*")此表由学生按顺序填写\

#text(
  font:"KaiTi",
  size: 15pt
)[
课程名称#underline[#text("   面向对象程序设计/JAVA语言   ")]成绩评定#underline[#text("       ")]\
实验项目名称#underline[#text(" 实现一个高性能的基础正则表达式匹配方法  ")]\ 指导老师#underline[#text("  干晓聪  ")]\
实验项目编号#underline[#text(" 1 ")]实验项目类型#underline[#text("  设计性  ")]实验地点#underline[#text(" 数学系机房 ")]\
学生姓名#underline[#text("   郭彦培   ")]学号#underline[#text("   2022101149   ")]\
学院#underline[#text(" 信息科学技术学院 ")]系#underline[#text(" 数学系 ")]专业#underline[#text(" 信息管理与信息系统 ")]\
实验时间#underline[#text(" 2023年11月1日上午 ")]#text("~")#underline[#text("  2023年11月1日中午  ")]\
]
#set heading(
  numbering: "一、"
  )  
#set par( first-line-indent: 1.8em)

= 实验目的
\
#h(1.8em)练习字符串相关操作与基础数组的使用


= 实验环境
\
#h(1.8em)计算机：PC X64

操作系统：Windows

编程语言：Java

IDE：Visual Studio Code

在线测试平台：leetcode


= 程序原理

\
#h(1.8em)实现的正则规则为`.`与`*`

`.` 匹配任意单个字符,`*` 匹配零个或多个前面的那一个元素

算法使用动态规划，复杂度为严格线性，即$O(N)$

具体实现的过程使用了`String`类、`boolean`数组

代码正确性已通过`leetcode`平台测试

= 程序代码

文件`sis4\regularExp`实现了一个`boolean`函数，用于字符串匹配 
```java
package sis4;

public class regularExp {

    public static boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] m = new boolean[lenS+1][lenP+1];
        m[0][0] = true;
        for(int i = 1; i <= lenS; i++){
            m[i][0] = false;
        }
        for(int j = 1; j <= lenP; j++){
            m[0][j] = false;
            if(j>=2 && p.charAt(j-1)=='*'){
                 m[0][j] = m[0][j-2];
            }
        }
        for(int i = 1; i <= lenS; i++){
            for(int j = 1; j <= lenP; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    m[i][j] = m[i-1][j-1];
                }
                else if(p.charAt(j-1) == '*'){
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        m[i][j] = m[i-1][j] || m[i][j-2];
                    }
                    else{
                        m[i][j] = m[i][j-2];
                    }
                }
            }
        }
        return m[lenS][lenP];
    }
}

```


= 出现的问题、原因与解决方法

\
#h(1.8em)一开始二维数组`m`使用`ArrayList`实现，随后在后续的编码过程中发现操作过于复杂。

例如对于`boolean`数组，`m[0][j] = m[i-1][j] || m[i][j-2]`这段代码，若使用`ArrayList`则需要写成

```java
m.get(i).set(j,Boolean.valueOf((boolean)m.get(i-1).get(j) || (boolean)m.get(i).get(j-2)));
```

#h(1.8em)研究过后发现，在不太需要访问控制的场合，适当使用数组而非对象会提高编码效率。

于是本段代码没有使用`ArrayList`。


= 测试数据与运行结果


#figure(
  table(
    align: left + horizon,
    columns: 3,
    [*输入*],[*输出*],[*解释*],
    [`s = "ab", p = ".*"`],[`true`],["`.*`" 表示可匹配零个或多个（'`*`'）任意字符（'`.`'）。],
    [`s = "aa", p = "a"`],[`false`],["`a`" 无法匹配 "`aa`" 整个字符串。],
    [`s = "accomplish", p = "ac*m.l.*"`],[`true`],[`*`匹配为上一个`c`，`.`匹配为`m`\ 之后`.*`匹配为`ish`]
    
  )
)

注：测试平台`leetcode`的特性为直接向函数传参，因此不需要实现输入输出。