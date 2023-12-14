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

= 实现一个trie(字典树)模板
\
#text("*") 实验项目类型：设计性\
#text("*")此表由学生按顺序填写\

#text(
  font:"KaiTi",
  size: 15pt
)[
课程名称#underline[#text("   面向对象程序设计/JAVA语言   ")]成绩评定#underline[#text("       ")]\
实验项目名称#underline[#text("   实现一个trie(字典树)模板   ")]指导老师#underline[#text("   干晓聪   ")]\
实验项目编号#underline[#text(" 1 ")]实验项目类型#underline[#text("  设计性  ")]实验地点#underline[#text(" 数学系机房 ")]\
学生姓名#underline[#text("   郭彦培   ")]学号#underline[#text("   2022101149   ")]\
学院#underline[#text(" 信息科学技术学院 ")]系#underline[#text(" 数学系 ")]专业#underline[#text(" 信息管理与信息系统 ")]\
实验时间#underline[#text(" 2023年10月27日上午 ")]#text("~")#underline[#text("  2023年10月27日中午  ")]\
]
#set heading(
  numbering: "一、"
  )  
#set par( first-line-indent: 1.8em)

= 实验目的
\
#h(1.8em)练习字符串相关操作


= 实验环境
\
#h(1.8em)计算机：PC X64

操作系统：Windows

编程语言：Java

IDE：Visual Studio Code


= 程序原理

\
#h(1.8em)字典树，英文名Trie，顾名思义为类似字典的树状结构。

字典树的每条边代表一个字母，从根节点到树上某个节点的路径则代表了一个字符串。我们用$theta (u,c)$表示节点$u$的$c$字符指向的下一个节点，或者说节点$u$代表的字符串后添加一个字符$c$形成的字符串的节点。

字典树最基础的应用为：查找一个字符串是否在字典中出现过，即$exists$字符串$s$和一个索引$i$，$s.t. forall c < s".size()" ,$有$"tire"(s+c) = s(c)$

具体实现的过程使用了`String`类、`ArrayList`类、`HashMap`类

= 程序代码

文件`sis3\TrieTree`实现了字典树类
```java
package sis3;

import java.util.ArrayList;
import java.util.HashMap;

public class TrieTree {
    public ArrayList<HashMap<Character, Integer>> t;
    public int root = 0;

    public TrieTree()
    {
        t = new ArrayList<HashMap<Character, Integer>>();                //
        t.add(new HashMap<Character, Integer>());
    }

    public void addedge(String _s)
    {
        int pvidx = root;
        _s += '-';
        for (int i = 0; i < _s.length(); i++) {
            if (t.get(pvidx).containsKey(_s.charAt(i))) {
                pvidx = t.get(pvidx).get(_s.charAt(i));
            } else {
                t.get(pvidx).put(_s.charAt(i), t.size());
                t.add(new HashMap<Character, Integer>());
                pvidx = t.get(pvidx).get(_s.charAt(i));
            }
        }
    }

    public boolean ifcmp(String s) 
    {
        int pvidx = root;
        for (int i = 0; i < s.length(); i++) {
            if (t.get(pvidx).containsKey(s.charAt(i)))
                pvidx = t.get(pvidx).get(s.charAt(i));
            else
                return false;
        }
        return t.get(pvidx).containsKey('-');
    }
}
```

文件`sis\Text.java`实现了输入输出与测试数据处理
```java
package sis3;

import java.util.Scanner;

// 输入一行一个整数T，表示数据组数T 对于每组数据，格式如下
// 第一行是两个整数，分别表示模式串的个数n和询问的个数q
// 接下来n行，每行一个模式串，表示模式串集合 接下来q行，每行一个询问，表示询问集合
public class Test {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int T = sc.nextInt();
            TrieTree t = new TrieTree();
            while (T --> 0) {
                int n = sc.nextInt();
                int q = sc.nextInt();
                sc.nextLine();
                while (n-- > 0) {
                    String s = sc.nextLine();
                    t.addedge(s);
                }
                while (q-- > 0) {
                    String s = sc.nextLine();
                    if (t.ifcmp(s))
                        System.out.println("YES");
                    else
                        System.out.println("NO");
                }
            }
        }
    }

}


```

= 出现的问题、原因与解决方法

\
#h(1.8em)编码过程十分顺利


= 测试数据与运行结果


#figure(
  table(
    align: left + horizon,
    columns: 3,
    [*输入*],[*输出*],[*解释*],
    [`3`],[],[输入测试样例数量],
    [`3 3
fusufusu
fusu
anguei`],[` `],[记录测试用字典与测试字符串数量],
    [`fusu`],[`2`],[字典中有两个字符串满足前缀为fusu],
    [`anguei`],[`1`],[字典中有一个字符串满足前缀为anguei],
    [`kkksc`],[`0`],[字典中有零个字符串满足前缀为kkksc],
    [`5 2
fusu
Fusu
AFakeFusu
afakefusu
fusuisnotfake`],[],[记录第二个测试样例\ 测试用字典与测试字符串数量],
    [`Fusu`],[`1`],[字典中有一个字符串满足前缀为Fusu],
    [`fusu`],[`2`],[字典中有两个字符串满足前缀为fusu],
    [`1 1
998244353`],[],[记录第三个测试样例\ 测试用字典与测试字符串数量],
    [`9`],[`1`],[字典中有一个字符串满足前缀为9],
  )
)