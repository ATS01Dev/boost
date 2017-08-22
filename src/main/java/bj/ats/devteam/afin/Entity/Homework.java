package bj.ats.devteam.afin.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Homework implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Course course;
    private CourseModule courseModule;
    @OneToMany
    private Set<HwQuestion> hwQuestions;
    private double score;

    public Homework() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseModule getCourseModule() {
        return courseModule;
    }

    public void setCourseModule(CourseModule courseModule) {
        this.courseModule = courseModule;
    }

    public Set<HwQuestion> getHwQuestions() {
        return hwQuestions;
    }

    public void setHwQuestions(Set<HwQuestion> hwQuestions) {
        this.hwQuestions = hwQuestions;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
