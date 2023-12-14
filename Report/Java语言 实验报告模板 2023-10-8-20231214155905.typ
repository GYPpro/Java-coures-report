// Some definitions presupposed by pandoc's typst output.
#let blockquote(body) = [
  #set text( size: 0.92em )
  #block(inset: (left: 1.5em, top: 0.2em, bottom: 0.2em))[#body]
]

#let horizontalrule = [
  #line(start: (25%,0%), end: (75%,0%))
]

#let endnote(num, contents) = [
  #stack(dir: ltr, spacing: 3pt, super[#num], contents)
]
#show terms: it => {
  it.children
    .map(child => [
      #strong[#child.term]
      #block(inset: (left: 1.5em, top: -0.4em))[#child.description]
      ])
    .join()
}

#let conf(
  title: none,
  authors: none,
  date: none,
  abstract: none,
  cols: 1,
  margin: (x: 1.25in, y: 1.25in),
  paper: "us-letter",
  lang: "en",
  region: "US",
  font: (),
  fontsize: 11pt,
  sectionnumbering: none,
  doc,
) = {
  set page(
    paper: paper,
    margin: margin,
    numbering: "1",
  )
  set par(justify: true)
  set text(lang: lang,
           region: region,
           font: font,
           size: fontsize)
  set heading(numbering: sectionnumbering)

  if title != none {
    align(center)[#block(inset: 2em)[
      #text(weight: "bold", size: 1.5em)[#title]
    ]]
  }

  if authors != none {
    let count = authors.len()
    let ncols = calc.min(count, 3)
    grid(
      columns: (1fr,) * ncols,
      row-gutter: 1.5em,
      ..authors.map(author =>
          align(center)[
            #author.name \
            #author.affiliation \
            #author.email
          ]
      )
    )
  }

  if date != none {
    align(center)[#block(inset: 1em)[
      #date
    ]]
  }

  if abstract != none {
    block(inset: 2em)[
    #text(weight: "semibold")[Abstract] #h(1em) #abstract
    ]
  }

  if cols == 1 {
    doc
  } else {
    columns(cols, doc)
  }
}
#show: doc => conf(
  cols: 1,
  doc,
)


#underline[#strong[面向对象程序设计/Java语言]] #strong[课程实验项目目录]

学生姓名：xx 学号：yy 专业：zz

#align(center)[#table(
  columns: 6,
  align: (col, row) => (auto,auto,auto,auto,auto,auto,).at(col),
  inset: 6pt,
  [序号], [实验项目编号], [实验项目名称], [\*实验项目类型], [成绩],
  [指导教师],
  [],
  [],
  [命令行编译运行],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [IDE],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis01学校信息系统C版],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis01学校信息系统Java版],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis02封装数据C版],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis02封装数据Java版],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis03a用文件封装函数C版],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis03封装函数Java版],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis03b用结构体封装函数C版],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis04封装成员方法],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [sis05构造函数],
  [设计性],
  [],
  [干晓聪],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
  [],
)
]

= 命令行编译运行
<命令行编译运行>
\*实验项目类型：演示性、验证性、综合性、设计性实验。

\*此表由学生按顺序填写。

课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 命令行编译运行 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

学习安装、手工使用编译器

#strong[二、实验环境]

计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

下载安装OpenJDK的Oracle版本，最好把JDK\\bin目录加入系统PATH。通过命令javac
p/A.java && java p.A来编译运行

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码如下]

package p;

class A{

public static void main\( String\[\] args ) throws Exception {

System.out.printf\( \"Hello World!\\n\" );

}

}

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= IDE
<ide>
\*实验项目类型：演示性、验证性、综合性、设计性实验。

\*此表由学生按顺序填写。

课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 IDE 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

学习使用IDE

#strong[二、实验环境]

计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

可下载自动安装工具，或手工选择download
package的java版本。目前的Eclipse自带自家的Open
JDK。调整好自己习惯的Perspective。新建Java
Project，新建一个类，菜单方式运行

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码如下]

package p;

class A{

public static void main\( String\[\] args ) throws Exception {

System.out.printf\( \"Hello World!\\n\" );

}

}

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis01学校信息系统C版
<sis01学校信息系统c版>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis01学校信息系统C版 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

复习面向过程编程，实现一个简单的SIS学校信息系统

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

记录一张表，含ID、姓名

输入add xxx yyy添加一条记录，ID为xxx，姓名为yyy

输入show显示所有记录

输入exit退出

用字符数组实现，不用字符串

只用给出的输入、输出单个字符的函数getchar\()和putchar\()

假设输入很规范，英文全小写，每行最多100字符，首尾无空格，词语间用1个空格隔开，命令不会输错，学号最多10字符，姓名最多10字符，最多100条记录

用一个长度101的字符数组接收输入的每一行命令。根据命令开头若干个字符分情况处理，信息记录在两个长度为100的数组中，数组的每个元素是长11的字符数组

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis01，C版]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis01学校信息系统Java版
<sis01学校信息系统java版>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis01学校信息系统Java版 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

复习面向过程编程，实现一个简单的SIS学校信息系统

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

记录一张表，含ID、姓名

输入add xxx yyy添加一条记录，ID为xxx，姓名为yyy

输入show显示所有记录

输入exit退出

用字符数组实现，不用字符串

只用给出的输入、输出单个字符的函数getchar\()和putchar\()

假设输入很规范，英文全小写，每行最多100字符，首尾无空格，词语间用1个空格隔开，命令不会输错，学号最多10字符，姓名最多10字符，最多100条记录

用一个长度101的字符数组接收输入的每一行命令。根据命令开头若干个字符分情况处理，信息记录在两个长度为100的数组中，数组的每个元素是长11的字符数组

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis01，Java版]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis02封装数据C版
<sis02封装数据c版>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis02封装数据C版 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

将数据封装到结构体里面

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

建立一个Person结构体，含两个成员：ID、姓名。数据存放在指向Person结构体的指针数组中，注意内存的分配、释放

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis02，C版]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis02封装数据Java版
<sis02封装数据java版>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis02封装数据Java版 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

将数据封装到类里面

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

建立一个Person类，含两个成员：ID、姓名。数据存放在Person对象数组中

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis02，Java版]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis03a用文件封装函数C版
<sis03a用文件封装函数c版>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis03a用文件封装函数C版 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

将函数封装到文件里面

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

把Person相关的处理函数create, destroy, setIdName, show放入Person文件里面

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis03a]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis03封装函数Java版
<sis03封装函数java版>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis03封装函数Java版 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

将函数封装到类里面

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

把Person相关的处理函数create, setIdName,
show以static的形式放入Person类里面

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis03，Java版]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis03b用结构体封装函数C版
<sis03b用结构体封装函数c版>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis03b用结构体封装函数C版 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

将函数封装到结构体里面

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

把Person相关的处理函数destroy, setIdName,
show，通过函数指针，放入Person结构体里面

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis03b，C版]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis04封装成员方法
<sis04封装成员方法>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis04封装成员方法 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

学习成员方法

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

将类Person的两个static函数setIdName, show改为成员方法

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis04]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]

= sis05构造函数
<sis05构造函数>
课程名称 面向对象程序设计/Java语言 成绩评定

实验项目名称 sis05构造函数 指导教师 #underline[干晓聪]

实验项目编号 实验项目类型 #underline[设计性] 实验地点
#underline[数学系机房]

学生姓名 #underline[xx] 学号 #underline[yy]

学院 #underline[信息学院] 系 #underline[数学系] 专业 zz

实验时间 年 月 日 午～ 月 日 午 温度 ℃湿度

#strong[一、实验目的]

学习构造函数

#strong[二、实验环境]

计算机：计算机：PC X64 / PC X86

操作系统：Windows / Linux / MacOS

编程语言：Java / Python / C++

IDE：Eclipse / Visual Studio Code / IntelliJ IDEA

#strong[三、程序原理]

将类Person的第一个static的create函数改为构造函数。充分利用创建对象时的流程，甚至可省略构造函数

#emph[可在此补充详细原理与代码结构的简要说明]

#strong[四、程序代码]

#emph[\/\/ 参考代码sis05]

#strong[五、出现的问题、原因与解决方法]

#strong[六、测试数据与运行结果]
