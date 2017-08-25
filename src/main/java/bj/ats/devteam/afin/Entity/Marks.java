package bj.ats.devteam.afin.Entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Marks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private double score;
    @OneToOne
    private HwQuestion hwQuestion;

    public Marks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public HwQuestion getHwQuestion() {
        return hwQuestion;
    }

    public void setHwQuestion(HwQuestion hwQuestion) {
        this.hwQuestion = hwQuestion;
    }
}
