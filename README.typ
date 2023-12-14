#set page(
  paper:"a4",
)

#set text(
    font:("Times New Roman","Source Han Serif SC"),
    style:"normal",
    weight: "regular",
    size: 13pt,
)

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

#outline(
  title: "Catelog"
);

#pagebreak()

= sis0:命令行编译

#pagebreak()

= sis1:基础IO操作

使用了Scanner实现了输入一个给定长度的差分数组，求出其原数组，即对数组进行逐项求和。

#pagebreak()

= sis2:UID管理器

提供三种类型的UID生成、申请与维护。
+ 基于日期的UID，例如2022101149
+ 完全无序的类激活码，例如AA2CA-STBS3-AED2P-RDGSSP
+ 按顺序发放的序列，可选固定位数，例如00001,00002
可以保证所有UID不会重复。
类中储存所有UID对应的引用。
申请复杂度O(1)，引用复杂度O(log(n))

测试用例的控制台规则：\
中括号内为需要填入字符串\
尖括号为可选参数，默认为列表第一个

#figure(
  table(
    columns: 2,
    align: left+horizon,
    [```shell-unix-generic
    getUID <type: "date" | "code" | "seq"> [name]
    ```],[申请对应类型的新UID，并与一个引用名称name绑定],
    [```shell-unix-generic
    secUID <type: "date" | "code" | "seq"> [UID]
    ```],[查找UID对应的引用名称]
  )
)

#pagebreak()
= sis3:实现一个trie(字典树)模板

#pagebreak()

= sis4:实现一个高性能的基础正则表达式匹配solution类

正则表达式

#pagebreak()

= sis5:学生信息管理器

提供了三个类，其中SchoolLib为主类，提供了对学生以下操作：
+ 添加学生并自动创建学号
+ 添加课程并自动生成课程编号
+ 学生选课
+ 记录考试成绩，补考成绩
+ 与平时分加权，计算总评成绩
+ 计算绩点
+ 以绩点、学号、姓名字典序等对学生排序
保证所有操作线程安全，必要的地方法进行了异常处理与保护性拷贝。

测试用例的控制台规则：\
中括号内为需要填入字符串\
尖括号为可选参数，默认为列表第一个

#set align(left)

#align(left+horizon)[

#figure(table(
  columns: 2,
  align: left+horizon,
  [#align(left)[```shell-unix-generic
addStu [student name]
```]],[添加学生],
  [#align(left)[```shell-unix-generic
addCur [Course name] [teacher name] [credit]
```]],[添加课程],
  [#align(left)[```shell-unix-generic
celCur [student UID] [Course name] 
```]],[选课],
 [#align(left)[```shell-unix-generic
showStu <sort with:"UID" | "name" | "GPA">
```]],[显示学生列表],
 [#align(left)[```shell-unix-generic
showCur <sort with:"UID" | "Course name" | " teacher name">
```]],[显示课程列表],
  [#align(left)[```shell-unix-generic
addScore [student UID] [Course name] [Normally score] [Exam score]
```]],[添加成绩],
  [#align(left)[```shell-unix-generic
addResc [student UID] [Course name] [Exam score] 
  ```]],[添加补考成绩],
 [#align(left)[```shell-unix-generic
cacu
```]],[计算总评成绩与绩点]
))
]

#pagebreak()

= sis6:实现一个线段树泛型模板
线段树是一种较为复杂的数据结构，旨在快速解决区间数据批量修改与特征统计。\
本类实现了一个可以批量地对数据进行线性空间内加和运算的线段树，统计内容为区间内的最大值，对于每个操作：
+ 修改单点：时间复杂度为$O(log n)$
+ 修改区间：均匀修改与查询后最坏时间复杂度为每点渐进$O(log (n m))$，n为内容总数，m为修改区间长度
+ 查询区间：$O(log n)$

#pagebreak()

= sis7:实现对网络最大流与最小费用流问题的solution类

最大流问题叙述略


#pagebreak()

= sis8:实现一个瘟疫传播的可视化模拟

#pagebreak()

= sis9:实现一个较为复杂的线性代数计算器

#figure(
  table(
    align: left+horizon,
    columns: 3,
    [myLinearLib],[线性计算主类],
    [myLinearEntire],[线性空间元素],
    [
      抽象类：具有线性性质的基础运算
    ],
    [myLinearSpace],[线性空间],[
      + 基础运算
      + 基
      + 计算秩
      + 对应矩阵
    ],
    [myMatrix],[矩阵],[
      + 基础运算
      + 求秩
      + 行列式
      + 逆
      + 转置
      + 对角化
    ],
    [myPolynomial],[多项式],[
      + 基础运算
      + 求值
    ],
    [myRealNum],[实数],[
      + 基础运算
    ]
  )
)

实现的功能：控制台指令如下：
中括号内为需要填入字符串\
尖括号为可选参数，默认为列表第一个

#figure(
  table(
    align: left+horizon,
    columns: 2,
    [```shell-unix-generic
    addMat [columns] [rows] [digit(1,1)] ... [digit(c,r)]
    ```],[添加一个列数为columns，行数为rows的矩阵],
    [```shell-unix-generic
    addPol [dim] [digit 1] ... [digit r]
    ```],[添加一个阶数为r的多项式],
    [```shell-unix-generic
    addLS [rank] [LE name 1] ... [LE name r]
    ```],[添加一个以`LE 1~r`为基的线性空间],
    [```shell-unix-generic
    show <scope :"all" | "Mat" | "Pol" | "LS">
    ```],[列出对应的所有元素],
    [```shell-unix-generic
    cacuMat [Mat name] <type :"Det" | "Rank" | "inverse" | "transpose" | "Eig">
    ```],[计算矩阵的行列式、秩、逆、转置和对角化],
    [```shell-unix-generic
    cacuPol [Pol name] [digit]
    ```],[计算多项式的值],
    [```shell-unix-generic
    op [LE name1] <operator :"+" | "-" | "*"> [LE name2]
    ```],[对线性空间元素进行基础运算]
  )
)
