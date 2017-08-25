package bj.ats.devteam.afin.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HwQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private String statement;
    private String answer;
    private String proposition1;
    private String proposition2;
    private String proposition3;
    private String proposition4;
    private double qmark;

    public HwQuestion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public double getQmark() {
        return qmark;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQmark(double qmark) {
        this.qmark = qmark;
    }

    public String getProposition1() {
        return proposition1;
    }

    public void setProposition1(String proposition1) {
        this.proposition1 = proposition1;
    }

    public String getProposition2() {
        return proposition2;
    }

    public void setProposition2(String proposition2) {
        this.proposition2 = proposition2;
    }

    public String getProposition3() {
        return proposition3;
    }

    public void setProposition3(String proposition3) {
        this.proposition3 = proposition3;
    }

    public String getProposition4() {
        return proposition4;
    }

    public void setProposition4(String proposition4) {
        this.proposition4 = proposition4;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
