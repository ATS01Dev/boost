package bj.ats.devteam.afin.Entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;
@Entity
public class Student extends Users implements Serializable{

    @OneToMany
    private Set<Marks> marks;
    @OneToMany
    private Set<Course> courses;
    public Student() {
    }

    public Student(String name, String surnames) {
        super(name, surnames);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Marks> getMarks() {
        return marks;
    }

    public void setMarks(Set<Marks> marks) {
        this.marks = marks;
    }
}
