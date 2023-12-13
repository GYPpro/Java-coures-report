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


= sis1:基础IO操作

使用了Scanner实现了输入一个给定长度的差分数组，求出其原数组，即对数组进行逐项求和。

#pagebreak()

= sis2:UUID管理器

提供三种类型的UUID生成、申请与维护。
+ 基于日期的UUID，例如2022101149
+ 完全无序的类激活码，例如AA2CA-STBS3-AED2P-RDGSSP
+ 按顺序发放的序列，可选固定位数，例如00001,00002
可以保证所有UUID不会重复。
类中储存所有UUID对应的引用。
申请复杂度O(1)，引用复杂度O(log(n))

#pagebreak()

= sis3:学生信息管理器

提供了三个类，其中SchoolLib为主类，提供了对学生以下操作：
+ 添加学生并自动创建学号
+ 添加课程并自动生成课程编号
+ 学生选课
+ 记录考试成绩，补考成绩，平时分
+ 计算总评成绩
+ 计算绩点
+ 以绩点、学号、姓名字典序等对学生排序
保证所有操作线程安全，必要的地方法进行了异常处理与保护性拷贝。

测试用例的控制台规则：\
中括号内为需要填入字符串
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
celCur [student UUID] [Course name] 
```]],[选课],
 [#align(left)[```shell-unix-generic
showStu <sort with:"UUID" | "name" | "point">
```]],[显示学生列表],
 [#align(left)[```shell-unix-generic
showCur <sort with:"UUID" | "Course name" | " teacher name">
```]],[显示课程列表],
 [#align(left)[```shell-unix-generic
cacu
```]],[计算总评成绩与绩点]
))
]

#pagebreak()

= sis4 

