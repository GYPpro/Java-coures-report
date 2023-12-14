package sis5;

public class score {

    private Integer normallyScore;//平时成绩
    private Integer examScore;//期末成绩
    private Integer finalScore;//总评成绩
    private double GPA;//绩点
    private boolean ifReExam;//是否补考

    Integer NORMALLY_WEIGHT;//平时成绩权重
    Integer EXAM_WEIGHT;//期末成绩权重
    Integer TOTAL_WEIGHT;//总评成绩权重

    @Override public boolean equals(Object b)
    {
        if(!(b instanceof score)) return false;
        score ts = (score)b;
        if(ts.getFinalScore() == this.finalScore) return true;
        else return false;
    }

    @Override public int hashCode()
    {
        return finalScore;
    }


    private void cacuGPA() //根据暨大标准计算GPA
    {
        if(finalScore > 90) GPA = 4+(finalScore-90)/10.0;
        else if(finalScore > 80) GPA = 3+(finalScore-80)/10.0;
        else if(finalScore > 70) GPA = 2+(finalScore-70)/10.0;
        else if(finalScore > 60) GPA = 1+(finalScore-60)/10.0;
        else GPA = 0;

        if(ifReExam) GPA = Math.min(GPA, 1.6);
    }

    private void cacuFinalScore() //计算分配权重后的总评成绩
    {
        finalScore = (int)(normallyScore.intValue() * ((double)NORMALLY_WEIGHT/(double)TOTAL_WEIGHT) + examScore.intValue() * ((double)EXAM_WEIGHT/(double)TOTAL_WEIGHT));
    }

    public score(Integer _normallyScore,Integer _examScore)
    {
        ifReExam = false;
        NORMALLY_WEIGHT = 3;
        EXAM_WEIGHT = 7;
        TOTAL_WEIGHT = NORMALLY_WEIGHT + EXAM_WEIGHT; 
        normallyScore = _normallyScore;
        examScore = _examScore;
        cacuFinalScore();
        cacuGPA();
    }

    public score(Integer _normallyScore,Integer _examScore,Integer _normallyWeight,Integer _examWeight)
    {
        ifReExam = false;
        NORMALLY_WEIGHT = _normallyWeight;
        EXAM_WEIGHT = _examWeight;
        TOTAL_WEIGHT = NORMALLY_WEIGHT + EXAM_WEIGHT; 
        normallyScore = _normallyScore;
        examScore = _examScore;
        cacuFinalScore();
        cacuGPA();
    }

    public score(score sc) throws Exception //深拷贝构造
    {
        this.normallyScore = sc.getNormallyScore();
        this.examScore = sc.getExamScore();
        this.finalScore = sc.getFinalScore();
        this.GPA = sc.getGPA();
        this.ifReExam = sc.ifReExam;
        this.NORMALLY_WEIGHT = sc.NORMALLY_WEIGHT;
        this.EXAM_WEIGHT = sc.EXAM_WEIGHT;
        this.TOTAL_WEIGHT = sc.TOTAL_WEIGHT;
    }
    

    public void resetWeight(Integer _normallyWeight,Integer _examWeight) //重设分数分配权重
    {
        NORMALLY_WEIGHT = _normallyWeight;
        EXAM_WEIGHT = _examWeight;
        TOTAL_WEIGHT = NORMALLY_WEIGHT + EXAM_WEIGHT; 
        cacuFinalScore();
        cacuGPA();
    }

    public void resetNormallyScore(Integer _normallyScore) //重设平时成绩
    {
        normallyScore = _normallyScore;
        cacuFinalScore();
        cacuGPA();
    }

    public void resetExamScore(Integer _examScore) throws Exception //补考
    {
        if(ifReExam) throw new Exception("重复补考\n");
        if(finalScore >= 60) throw new Exception("非法补考\n");
        ifReExam = true;
        examScore = _examScore;
        cacuFinalScore();
        cacuGPA();
    }


    public Integer getNormallyScore()
    {
        return Integer.valueOf(normallyScore);
    }

    public Integer getExamScore()
    {
        return Integer.valueOf(examScore);
    }

    public Integer getFinalScore()
    {
        return Integer.valueOf(finalScore);
    }

    public double getGPA()
    {
        return GPA;
    }

}