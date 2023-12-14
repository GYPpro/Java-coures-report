// Display inline code in a small box
// that retains the correct baseline.
#set text(font:("Times New Roman","Source Han Serif SC"))
#show raw.where(block: false): box.with(
  fill: luma(240),
  inset: (x: 3pt, y: 0pt),
  outset: (y: 3pt),
  radius: 2pt,
)

#pagebreak()
// Display block code in a larger block
// with more padding.
#show raw.where(block: true): block.with(
  fill: luma(240),
  inset: 10pt,
  radius: 4pt,
)

#set math.equation(numbering: "(1)")

#[
    #set text(
      font:("Times New Roman","Source Han Serif SC"),
      style:"normal",
      weight:"regular",
      size: 22pt,
  )


  #[
    #set align(
      left+horizon
    )
    = 写在前面
    #smallcaps[Overall]
    #line(start: (0pt,11pt),end:(300pt,11pt))
    #set text(
      font:("Times New Roman","Source Han Serif SC"),
      style:"normal",
      weight:"regular",
      size: 15pt,
    )
    老师辛苦了，感谢您一学期的付出！

    本实验报告所有代码已经在#smallcaps[Github]上同步了所有相关资料
    
    如果您感兴趣，欢迎来我主页#smallcaps[GYPpro]看看！


    #[
      #set align(bottom)
      #line(start: (0pt,11pt),end:(270pt,11pt))
      #set text(15pt)
      信息管理与信息系统 郭彦培\
      #set text(19pt)
      暨南大学
    ]
  ]
]
#pagebreak()